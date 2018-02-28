#include <cstdlib>
#include <iostream>
#include <stdlib.h>

using namespace std;

int main(int argc, char *argv[])
{
    char buf[1024] = "[CMD]Ready;"
                     + "Fd=" + 4 + ";"
                     + "Name=" + "QQ;";
    std::string strBuf = buf;
    if (strBuf.find("[CMD]") != -1){
       if(strBuf.find("[CMD]Ready") != -1){
           string cmds[] = strBuf.c_str().split(";");
           for (string str : cmds){
               printf(str);
           }
       }
    }
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
