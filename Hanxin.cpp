#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <ctype.h>

int main(int argc, char const *argv[])
{
	int a, b, c;
	scanf("%d%d%d", &a, &b, &c);
	for (int i = 10; i < 100; i++)
	{
		if ((i-a)%3 == 0){
			if ((i-b)%5 == 0){
				if ((i-c)%7 == 0){
					printf("%d\n", i);
					break;
				}
			}
		}
		if (i == 99){
			printf("No Answer\n");
		}

	}
	return 0;
}
