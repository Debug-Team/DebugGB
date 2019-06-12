#include<iostream>
#include<string>
#include "BankSystem.h"

#pragma comment(lib, "BankSystem_vc10_mtd.lib")
using namespace std;

int main(int args,char** argv) {

	cout << transfer("buyer", "123", "seller", 20.0) << endl;

	system("pause");
	return 0;
}
