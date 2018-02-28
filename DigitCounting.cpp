#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <ctype.h>
#define maxn 50000
int main(int argc, char const *argv[])
{
	char s[maxn];
	int n;
	scanf("%d", &n);
	int count = 0;
	char p[4];
	for (int i = 1; i <= n; i++)
	{
		sprintf(p, "%d", i); // int to char
		strcat(s, p);
	}
    printf("%s\n", s);
    
	for (int i = 0; i <= 9; i++)
	{
		for (int j = 0; j < strlen(s); j++)
		{
			if ((s[j]-'0') == i){
				count++;
			}
		}
		printf("%d\n", count);
		count = 0;
	}
	return 0;
}
