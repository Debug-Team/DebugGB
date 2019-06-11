package GroupPurchaseManagementSystemModule;

/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019年6月11日 星期二 下午11时36分35秒 CST
*/

public final class GroupPurchaseManagementSystemHolder implements org.omg.CORBA.portable.Streamable
{
  public GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem value = null;

  public GroupPurchaseManagementSystemHolder ()
  {
  }

  public GroupPurchaseManagementSystemHolder (GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper.type ();
  }

}
