#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <ctype.h>
#define maxn  100+10

int main()
{
	float mass = 0.00;
	float mol = 0.00;
	char sr[100+10];
	scanf("%s", sr);
	for (int i = 0; i < strlen(sr); i++)
	{
		if (sr[i] == 'C')
			mol = 12.01;
		if (sr[i] == 'H')
			mol = 1.008;
		if (sr[i] == 'O')
			mol = 16.00;
		if (sr[i] == 'N')
			mol = 14.01;

		if (isalpha(sr[i+1])){
			if (isdigit(sr[i])){
				mass += atoi(&sr[i]) * mol;
			}else{
				mass += mol;
			}
		}

		if (!sr[i+1]){
			mass += mol;
		}
        printf("%c\n", sr[i]);
	}
	printf("%.4f\n", mass);
	return 0;
}
