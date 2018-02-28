#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <ctype.h>

long jc(int n){
	long result = 1;
	while(n>0){
		result *= n;
		n--;
	}
	return result;
}

long cmn(int m, int n){
	return jc(n)/(jc(m)*jc(n-m));
}


int main(int argc, char const *argv[])
{
	int m, n;
	scanf("%d%d", &m, &n);
	printf("%d\n", cmn(m ,n));
	return 0;
	system("pause"); 
}
