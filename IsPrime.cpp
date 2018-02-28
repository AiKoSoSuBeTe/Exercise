#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <ctype.h>

bool isPrime(int n){
	if (n <= 1){
		return false;
	}
	if (n == 2){
		return true;
	}

	for (int i = 2; floor(sqrt(n) + 0.5) <= n; ++i)
	{
		if (!n%i)
			return false;
	}
	return true;
}

