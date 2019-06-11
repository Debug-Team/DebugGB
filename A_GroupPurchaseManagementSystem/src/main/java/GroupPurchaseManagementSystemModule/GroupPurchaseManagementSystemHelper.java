package GroupPurchaseManagementSystemModule;


/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemHelper.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019��6��11�� ���ڶ� ����11ʱ36��35�� CST
*/

abstract public class GroupPurchaseManagementSystemHelper
{
  private static String  _id = "IDL:GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystem:1.0";

  public static void insert (org.omg.CORBA.Any a, GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper.id (), "GroupPurchaseManagementSystem");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_GroupPurchaseManagementSystemStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem)
      return (GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      GroupPurchaseManagementSystemModule._GroupPurchaseManagementSystemStub stub = new GroupPurchaseManagementSystemModule._GroupPurchaseManagementSystemStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem)
      return (GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      GroupPurchaseManagementSystemModule._GroupPurchaseManagementSystemStub stub = new GroupPurchaseManagementSystemModule._GroupPurchaseManagementSystemStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
