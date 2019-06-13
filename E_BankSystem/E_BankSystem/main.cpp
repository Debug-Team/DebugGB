#include<iostream>
#include<string>
#include "BankSystem.h"
#pragma comment(lib, "BankSystem_vc10_mtd.lib")

#include "zmq.h"
#include "zmq_utils.h"            //Zeromq 函数的导入在这里帮我们实现了
#pragma comment(lib,"libzmq-v100-mt-gd-4_0_4.lib")

//com
#include "stdafx.h"
#include "atlcomcli.h"

#import "BankSystemAtl.dll" no_namespace

using namespace std;

void initTransferMQServer();
bool transferAtl(const char* account, const char* password, const char* target, double amount);

void initTransferMQServer(){
	std::cout << "-------------------Transfer Server by MQ-----------------------" << std::endl;

	void* context = zmq_init(3);    //指定zmq 处理I/0事件的thread pool 为1
	void* z_socket = zmq_socket(context, ZMQ_REP);

	zmq_bind(z_socket, "tcp://*:9999");    // accept connections on a socket

	int recvn = 1;
	while (1)    //循环工作
	{
		//接受部分
		zmq_msg_t recv_msg;
		zmq_msg_init(&recv_msg);
		zmq_msg_recv(&recv_msg, z_socket, 0);                            //0表示非阻塞

		char* str = (char*)zmq_msg_data(&recv_msg);
		std::cout << "第\t" << recvn++ << "\t次收到client信息：\t";
		cout << str << endl;
		zmq_msg_close(&recv_msg);

		//解析字符串参数
		string* args_str = new string(str);
		string args[4];
		for (int i = 0; i < 3; i++){
			int pos = args_str->find_first_of(',');
			args[i] = args_str->substr(0, pos);
			*args_str = (args_str->substr(pos + 1));

			//cout << args[i] << " ";
		}
		args[3] = *args_str;

		//转账
		//bool result = transfer(args[0], args[1], args[2], stod(args[3]));
		//string result_str = result ? "1" : "0";
		bool result = transferAtl(args[0].c_str(), args[1].c_str(), args[2].c_str(), stod(args[3]));
		string result_str = result ? "1" : "0";

		//发送部分,返回结果
		zmq_msg_t send_msg;
		zmq_msg_init_size(&send_msg, 2);
		memcpy(zmq_msg_data(&send_msg), result_str.c_str(), 2);
		zmq_sendmsg(z_socket, &send_msg, 0);
		zmq_msg_close(&send_msg);
	}
	zmq_close(z_socket);
	zmq_term(context);

}

bool transferAtl(const char* account, const char* password, const char* target, double amount){
	CoInitialize(NULL);
	CLSID clsid;
	CLSIDFromProgID(OLESTR("BankSystem.bank.1"), &clsid);
	ATL::CComPtr<IBankSystemAtlClass> pTest;//智能指针
	pTest.CoCreateInstance(clsid);
	BSTR a = _com_util::ConvertStringToBSTR(account);
	BSTR p = _com_util::ConvertStringToBSTR(password);
	BSTR t = _com_util::ConvertStringToBSTR(target);
	long ret = pTest->transferAtl(a, p, t, amount);

	//printf("%c\n",ret);
	cout << ret << endl;
	pTest.Release();
	CoUninitialize();

	return ret == 1 ? true : false;
}

int main(int args,char** argv) {

	//cout << transfer("buyer", "123", "seller", 20.0) << endl;

	initTransferMQServer();
	//transferAtl("buyer", "123", "seller", 20.0);

	system("pause");
	return 0;
}
