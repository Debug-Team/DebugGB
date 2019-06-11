package GroupPurchaseManagementSystemModule;


/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019年6月11日 星期二 下午11时36分35秒 CST
*/

public interface GroupPurchaseManagementSystemOperations 
{
  boolean confirmPurchase (String sellerSecretKey, String confirm);
  boolean publishGroupPurchaseItem (String sellerSecretKey, String productName, String introduction, double price, int limit);
} // interface GroupPurchaseManagementSystemOperations
