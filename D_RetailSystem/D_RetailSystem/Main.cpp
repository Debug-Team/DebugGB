#include <iostream>
#include "RetailSystem.h"

//CORBA
#include "GroupPurchaseManagementSystemCORBA.h"
#include "BankSystemCORBA.h"
#pragma comment(lib, "RetailSystem_vc10_mtd.lib")

//MQ
#include "zmq.h"
#include "zmq_utils.h"            //Zeromq 函数的导入在这里帮我们实现了
#pragma comment(lib,"libzmq-v100-mt-gd-4_0_4.lib")

using namespace std;

class RemoteBankSystem : public BankSystem{
private:
	void* context;
	void* z_socket;
public:
	void startMQClient(){
		context = zmq_init(3);    //指定zmq 处理I/0事件的thread pool 为1
		z_socket = zmq_socket(context, ZMQ_REQ);

		zmq_connect(z_socket, "tcp://127.0.0.1:7777");    // accept connections on a socket
	}
	~RemoteBankSystem(){		//析构，释放socket
		zmq_close(z_socket);
		zmq_term(context);
	}
	list<record> listHistory(string account, string password)
	{
		// TODO: adapt to a remote procedure call to the bank system
		//return *(new list<record>());
		list<record>* result = new list<record>();
		if (system == nullptr){
			cout << "system is nullptr";
			return *result;
		}
		//发送MQ请求
		//发送部分
		string args_str = account + "," + password;
		int args_size = args_str.size() + 1;
		zmq_msg_t send_msg;
		zmq_msg_init_size(&send_msg, args_size);
		memcpy(zmq_msg_data(&send_msg), args_str.c_str(), args_size);
		zmq_msg_send(&send_msg, z_socket, 0);
		zmq_msg_close(&send_msg);

		//接受部分
		zmq_msg_t recv_msg;
		zmq_msg_init(&recv_msg);
		zmq_msg_recv(&recv_msg, z_socket, 0);                    //0表示非阻塞
		//printf("收到Server端回答:\t");
		char* res = (char*)zmq_msg_data(&recv_msg);
		//std::cout << res << std::endl;

		string* res_str = new string(res);
		//cout << *res_str << endl;

		zmq_msg_close(&recv_msg);

		//解析结果字符串
		int pos = res_str->find_first_of("#");
		while (pos >= 0){
			string t_str = res_str->substr(0, pos);
			int t_pos = (pos + 1);
			int t_size = res_str->size();
			if (t_pos < t_size){
				*res_str = res_str->substr(pos + 1);
			}
			else{		//防止越界
				*res_str = "";
			}
			pos = res_str->find_first_of("#");		//结果字符串更新后，更新pos

			string args[3];
			//解析每个record字符串
			for (int i = 0; i < 2; i++){
				int pos2 = t_str.find_first_of(",");
				args[i] = t_str.substr(0, pos2);
				t_str = t_str.substr(pos2+1);
			}
			args[2] = t_str;
			record rec;
			rec.source = args[0];	rec.target = args[1];	rec.amount = stod(args[2]);
			result->push_back(rec);
		}
		return *result;
	}
};

class RemoteGroupPurchaseManagementSystem : public GroupPurchaseManagementSystem{
private:
	GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA_var system;
public:
	void setSystem(GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA_var s){
		system = s;
	}
	bool publishGroupPurchaseItem(string sellerSecretKey, string productName, string introduction, double price, int limit)
	{
		// TODO: adapt to a remote procedure call to the group purchase management system
		if (system == nullptr){
			cout << "system is nullptr";
			return false;
		}
		return system->publishGroupPurchaseItem(sellerSecretKey.c_str(), productName.c_str(), introduction.c_str(), price, limit);
	}
	bool confirmPurchase(string sellerSecretKey, string confirm)
	{
		// TODO: adapt to a remote procedure call to the group purchase management system
		if (system == nullptr){
			cout << "system is nullptr";
			return false;
		}
		return system->confirmPurchase(sellerSecretKey.c_str(), confirm.c_str());
	}
};

int main(int argc, char** argv){
	RemoteBankSystem bank;
	RemoteGroupPurchaseManagementSystem gpms;

	//A 团购管理系统
	try{
		int argc = 2;
		char* arguments[2] = {"-ORBInitRef", "NameService=corbaloc::127.0.0.1:6000/NameService"};
		//Init orb
		//CORBA::ORB_var orb = CORBA::ORB_init(argc, argv);
		CORBA::ORB_var orb = CORBA::ORB_init(argc, (char**)arguments);
		CORBA::Object_var nsobj = orb->resolve_initial_references("NameService");
		CosNaming::NamingContext_var nc =
			CosNaming::NamingContext::_narrow(nsobj);
		if (CORBA::is_nil(nc)) {
			cerr << "oops, I cannot access the Naming Service!" << endl;
			exit(1);
		}
		CosNaming::Name name;
		name.length(1);
		name[0].id = CORBA::string_dup("GroupPurchaseManagementSystem");
		//name[0].kind = CORBA::string_dup("");
		/*
		* try to find that node in the Naming Service tree
		*/
		CORBA::Object_var obj;
		cout << "Looking up GroupPurchaseManagementSystem " << endl << flush;
		obj = nc->resolve(name);

		//System就是获取到的接口对象的指针
		//要使用_unchecked_narrow(obj), 使用_narrow(obj)会报错内存无法访问
		//GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA_var system = GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA::_narrow(obj);
		GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA_var system = GroupPurchaseManagementSystemModule::GroupPurchaseManagementSystemCORBA::_unchecked_narrow(obj);
		if (CORBA::is_nil(system)){
			cerr << "Narrow err" << endl;
			throw 0;
		}
		//cout << system << endl << flush;
		//cout << system->confirmPurchase("", "") << endl;
		gpms.setSystem(system);		//设置接口的指针
	}
	catch (const CORBA::Exception&){
		cerr << "Uncaught CORBA exception " << endl;
		return 1;
	}

	//E MyBankSystem
	bank.startMQClient();

	//cout << bank.listHistory("buyer", "123").size() << endl;
	return launchRetailSystem(&gpms, &bank);
}