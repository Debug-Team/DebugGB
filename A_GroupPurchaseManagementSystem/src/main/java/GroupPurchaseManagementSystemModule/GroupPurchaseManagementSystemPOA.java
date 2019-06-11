package GroupPurchaseManagementSystemModule;


/**
* GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystemPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019年6月11日 星期二 下午11时36分35秒 CST
*/

public abstract class GroupPurchaseManagementSystemPOA extends org.omg.PortableServer.Servant
 implements GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("confirmPurchase", new java.lang.Integer (0));
    _methods.put ("publishGroupPurchaseItem", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystem/confirmPurchase
       {
         String sellerSecretKey = in.read_string ();
         String confirm = in.read_string ();
         boolean $result = false;
         $result = this.confirmPurchase (sellerSecretKey, confirm);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystem/publishGroupPurchaseItem
       {
         String sellerSecretKey = in.read_string ();
         String productName = in.read_string ();
         String introduction = in.read_string ();
         double price = in.read_double ();
         int limit = in.read_long ();
         boolean $result = false;
         $result = this.publishGroupPurchaseItem (sellerSecretKey, productName, introduction, price, limit);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystem:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GroupPurchaseManagementSystem _this() 
  {
    return GroupPurchaseManagementSystemHelper.narrow(
    super._this_object());
  }

  public GroupPurchaseManagementSystem _this(org.omg.CORBA.ORB orb) 
  {
    return GroupPurchaseManagementSystemHelper.narrow(
    super._this_object(orb));
  }


} // class GroupPurchaseManagementSystemPOA
