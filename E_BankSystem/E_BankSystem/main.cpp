#include<iostream>
#include<string>
#include "BankSystem.h"
#pragma comment(lib, "BankSystem_vc10_mtd.lib")

#include "zmq.h"
#include "zmq_utils.h"            //Zeromq 函数的导入在这里帮我们实现了
#pragma comment(lib,"libzmq-v100-mt-gd-4_0_4.lib")

using namespace std;

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
		bool result = transfer(args[0], args[1], args[2], stod(args[3]));
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

int main(int args,char** argv) {

	//cout << transfer("buyer", "123", "seller", 20.0) << endl;

	initTransferMQServer();

	system("pause");
	return 0;
}
