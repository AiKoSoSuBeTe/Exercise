#include <stdio.h>
#include <string>
#define maxn 100+10
int main()
{
	char s[maxn];
	int count = 0;
	int score = 0;
	scanf("%s", s);
	for (int i = 0; i < strlen(s); i++)
	{
		if (s[i] == 'X'){
			count = 0;
		}
		if (s[i] == 'O'){
			count++;
		}
		score += count;
	}
	printf("%d\n", score);
}

