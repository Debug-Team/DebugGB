package GroupPurchaseManagementSystemModule;

/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019��6��11�� ���ڶ� ����11ʱ36��35�� CST
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
