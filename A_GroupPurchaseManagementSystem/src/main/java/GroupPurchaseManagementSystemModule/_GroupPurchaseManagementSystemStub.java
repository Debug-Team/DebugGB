package GroupPurchaseManagementSystemModule;


/**
* GroupPurchaseManagementSystemModule/_GroupPurchaseManagementSystemStub.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从D:/Projects/DebugGB/A_GroupPurchaseManagementSystem/src/main/GroupPurchaseManagementSystem.idl
* 2019年6月11日 星期二 下午11时36分35秒 CST
*/

public class _GroupPurchaseManagementSystemStub extends org.omg.CORBA.portable.ObjectImpl implements GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem
{

  public boolean confirmPurchase (String sellerSecretKey, String confirm)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("confirmPurchase", true);
                $out.write_string (sellerSecretKey);
                $out.write_string (confirm);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return confirmPurchase (sellerSecretKey, confirm        );
            } finally {
                _releaseReply ($in);
            }
  } // confirmPurchase

  public boolean publishGroupPurchaseItem (String sellerSecretKey, String productName, String introduction, double price, int limit)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("publishGroupPurchaseItem", true);
                $out.write_string (sellerSecretKey);
                $out.write_string (productName);
                $out.write_string (introduction);
                $out.write_double (price);
                $out.write_long (limit);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return publishGroupPurchaseItem (sellerSecretKey, productName, introduction, price, limit        );
            } finally {
                _releaseReply ($in);
            }
  } // publishGroupPurchaseItem

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:GroupPurchaseManagementSystemModule/GroupPurchaseManagementSystem:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _GroupPurchaseManagementSystemStub
