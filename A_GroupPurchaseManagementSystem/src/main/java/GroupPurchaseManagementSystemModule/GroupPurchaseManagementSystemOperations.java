package GroupPurchaseManagementSystemModule;


/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019��6��11�� ���ڶ� ����11ʱ36��35�� CST
*/

public interface GroupPurchaseManagementSystemOperations 
{
  boolean confirmPurchase (String sellerSecretKey, String confirm);
  boolean publishGroupPurchaseItem (String sellerSecretKey, String productName, String introduction, double price, int limit);
} // interface GroupPurchaseManagementSystemOperations
