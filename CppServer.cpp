#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <iostream>
#include <thread>
#include <list>
#include <algorithm> 
#include <sstream>
#include <cstring>
// algorithm for max_element()

#define PORT 6000
#define IP "198.13.42.223"

int s; //FD of the Listening Server Socket.
struct sockaddr_in servaddr;
socklen_t len;
std::list<int> li;
std::list<int> readyList;

void broadcast(char msg[1024]);

// Connect method.
void getConn(){
    while (true){
        int conn = accept(s, (struct sockaddr*)&servaddr, &len);
        li.push_back(conn);
        //printf("%d\n", conn);
    }
}
/*
---------------------------->TODO:unpocket[CMD], Dont broadcast is msg has [CMD]
                             and  dont broadcast
 */
void getData(){
    struct timeval tv;
    tv.tv_sec = 2;
    tv.tv_usec = 0; // 2s Timeval.
    while (true){
        std::list<int>::iterator it; // dim it
        int disconnectedSocketFD = -1; // To Remove ele in the set. Never Remove ele when iterating.
        // TO CHANGE
        
        // TODO: MAke Share that li is not empty.
        if (!li.empty()){
            for (it = li.begin(); it != li.end(); ++it){
                fd_set rfds; // dim FD_SET read_file_description
                FD_ZERO(&rfds);
                int retval = 0; // Retrun Value 
                FD_SET(*it, &rfds);
                // TO FIND max FileDescription
                // only 2 ele in Set, *it and 0;
                //printf("Is 4 in Set?: %d\n", FD_ISSET(4, &rfds));
                //printf("for loop begin\n");
                //printf("BEFORE Is 4 in Set?: %d\n", FD_ISSET(4, &rfds));
                //printf("BEFORE Is 0 In Set?: %d\n", FD_ISSET(4, &rfds));
                retval = select(*it+1, &rfds, NULL, NULL, &tv);
                //printf("selected\n");
                //printf("AFTER Is 4 in Set?: %d\n", FD_ISSET(4, &rfds));
                //printf("ATTER Is 0 In Set?: %d\n", FD_ISSET(4, &rfds));
                if (retval == -1){
                    printf("select erro\n");
                }else if(retval == 0){
                    printf("No Msg, Waiting\n");
                }else{
                    // ----------------------------> Recved MSG
                    char buf[1024];
                    memset(buf, 0, sizeof(buf));
                    int len = recv(*it, buf, sizeof(buf), 0);
                    if (strlen(buf) == 0){
                        printf("Empty, Maybe Disconnected.\n"); // Never Send Empty package.
                        disconnectedSocketFD = *it;
                    }else{
                        printf("Recved From FD(%d): %s\n", *it, buf);
                        std::string strBuf = buf;
                        // String Opration
                        if (strBuf.find("[CMD]") != -1){
                            // ASK  SOCKETFD
                            if (strBuf.find("whatsmysocketfd") != -1){
                               // std::string strSfd = ;
                                std::stringstream ssSfd;
                                ssSfd << "[CMD]yoursocketfd:" << *it;
                                std::string strSfd = ssSfd.str();
                                //char sfd[25] = strSfd;
                                send(*it, strSfd.c_str(), 1024, 0);
                                printf("Send %s\n", strSfd.c_str());
                            }

                            // Client Ready
                            if (strBuf.find("[CMD]Ready") != -1){
                                readyList.push_back(*it);
                                /*
                                for (std::list<int>::iterator toPrint = li.begin(); toPrint != li.end(); ++toPrint){
                                    printf("Printing List --->%d\n", *toPrint);
                                }
                                */

                            }
                            if (strBuf.find("[CMD]CancelReady") != -1){
                                std::list<int>::iterator toRemove = find(readyList.begin(), readyList.end(), *it);
                                if (toRemove != readyList.end()){
                                    readyList.erase(toRemove);
                                }
                            }

                            // if [CMD]GameOver
                            
                            if (strBuf.find("[CMD]ShowHand") != -1){
                                // Format /[CMD]ShowHand1,2/
                                // int before , means handType
                                // 1 = Rock, 2 = JZ, 3 = Fu
                                // int after , means peerFd, send to *it and peerFd
                                std::size_t pos = strBuf.find(",");
                                //printf("%s\n", strBuf.substr(13, 1).c_str());
                                //printf("%s\n", strBuf.substr(pos+1).c_str());
                                int handType = stoi(strBuf.substr(13, 1));
                                int peerFd = stoi(strBuf.substr(pos+1));

                                std::stringstream ssShowHand;
                                ssShowHand << "[CMD]ReShowHand" << handType << "," << *it;
                                std::string strShowHand = ssShowHand.str();

                                char *showHand = new char[1024];
                                strcpy(showHand, strShowHand.c_str());
                                send(*it, showHand, 1024, 0);
                                send(peerFd, showHand, 1024, 0);
                            }
                            
                        }else{
                            broadcast(buf);
                        }
                    }
                }
                // Out of Iterating. Remove disconnected SocketFD.
                if (disconnectedSocketFD != -1){
                    //li.remove_if(li.begin(), li.end(), disconnectedSocketFD);
                    //int idxOfDFD = li.find(disconnectedSocketFD);
                    std::list<int>::iterator idxOfDFD = find(li.begin(), li.end(),disconnectedSocketFD);
                    if (idxOfDFD != li.end()){
                        li.erase(idxOfDFD);
                        // Ready list also Remove
                        std::list<int>::iterator toRemove = find(readyList.begin(), readyList.end(), *it);
                        if (toRemove != readyList.end()){
                            readyList.erase(toRemove);
                        }
                        printf("Removed.\n");
                        printf("Is li Empty%d\n", li.empty());
                        for (it = li.begin(); it != li.end(); ++it){
                            printf("Printing List --->%d\n", *it);
                        }
                    }
                    disconnectedSocketFD = -1;
                }
            }
        }else{
            printf("No Client Connect...\n");
        }
        sleep(1);
        //printf("Sleeped\n");
    }
}

void broadcast(char msg[1024]){
    /*
    while (true){
        char buf[1024];
        fgets(buf, sizeof(buf), stdin);
        std::list<int>::iterator it;
        // send to every Client 
        for (it = li.begin(); it != li.end(); ++it){
            send(*it, buf, sizeof(buf), 0);
        }
    }
    
   char buf[1024];
   
   // String 2 char[]
   for (int i = 0; i < msg.length(); i++){
        buf[i] = msg[i];
        buf[i] = '\0';
   }
    */
   std::list<int>::iterator it;
   for (it = li.begin(); it != li.end(); ++it){
        send(*it, msg, 1024, 0);
   }
   printf("MSg: %s, SizeofMsg: %d\n", msg, sizeof(msg));
}

int main(){
    s = socket(AF_INET, SOCK_STREAM, 0);
    memset(&servaddr, 0, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(PORT);
    servaddr.sin_addr.s_addr = inet_addr(IP);
    if (bind(s, (struct sockaddr*)&servaddr, sizeof(servaddr)) == -1){
        perror("bind");
        exit(1);
    }else{
        printf("Bind OK.--->\n");
    }

    if (listen(s, 5) == -1){
        perror("listen");
        exit(1);
    }else{
        printf("Listening.--->\n");
    }
    len = sizeof(servaddr);

    std::thread t1(getConn);
    t1.detach();
    printf("T1 OK\n");
    //std::thread t2(sendMess);
    //t2.detach();
    //printf("T2 OK\n");
    std::thread t3(getData);
    t3.detach();
    printf("T3 OK\n");

    // to keep on.
    while(true){
        // Matching......
        if (readyList.size() >= 2){
            //printf("End: %d\n", *std::prev(readyList.end()));
            //printf("begin: %d\n", *readyList.begin());
            std::stringstream ssMatched;
            ssMatched << "[CMD]Matched" << *std::prev(readyList.end());
            std::string strMatched = ssMatched.str();
            char *matched = new char[1024];
            strcpy(matched, strMatched.c_str());
            printf("%s\n", matched);
            /*
            for (std::list<int>::iterator toPrint = li.begin(); toPrint != li.end(); ++toPrint){
                printf("Printing List --->%d\n", *toPrint);
            }
            */
            std::stringstream ssMatched2;
            ssMatched2 << "[CMD]Matched" << *readyList.begin();
            std::string strMatched2 = ssMatched2.str();
            char *matched2 = new char[1024];
            strcpy(matched2, strMatched2.c_str());
            printf("%s\n", matched2);

            send(*readyList.begin(), matched, 1024, 0);
            send(*std::prev(readyList.end()), matched2, 1024, 0);

            readyList.erase(readyList.begin());
            readyList.erase(std::prev(readyList.end()));
            sleep(1);
        }

    }
    close(s);

    return 0;
}
















