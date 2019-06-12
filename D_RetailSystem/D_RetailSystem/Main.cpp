#include <iostream>
#include "RetailSystem.h"
#include "GroupPurchaseManagementSystemCORBA.h"
#pragma comment(lib, "RetailSystem_vc10_mtd.lib")

using namespace std;

class RemoteBankSystem : public BankSystem{
public:
	list<record> listHistory(string account, string password)
	{
		// TODO: adapt to a remote procedure call to the bank system
		return *(new list<record>());
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
	return launchRetailSystem(&gpms, &bank);
}