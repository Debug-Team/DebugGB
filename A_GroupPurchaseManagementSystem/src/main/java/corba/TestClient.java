package corba;

import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem;
import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class TestClient {
    private BufferedReader reader;
    private ORB orb;
    private org.omg.CORBA.Object objRef;
    private NamingContextExt ncRef;
    private GroupPurchaseManagementSystem system;

    public TestClient() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        TestClient testClient = new TestClient();
        testClient.init();
    }


    private void init() {
        System.out.println("Client init config starts....");
        String[] args = {};
        Properties properties = new Properties();
        properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
        properties.put("org.omg.CORBA.ORBInitialPort", "6000");       //指定ORB的端口

        //创建一个ORB实例
        orb = ORB.init(args, properties);

        //获取根名称上下文
        try {
            objRef = orb.resolve_initial_references("NameService");
        } catch (InvalidName e) {
            e.printStackTrace();
        }
        ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "GroupPurchaseManagementSystem";
        try {
            //通过ORB拿到server实例化好的Creator类
            system = GroupPurchaseManagementSystemHelper.narrow(ncRef.resolve_str(name));
        } catch (NotFound e) {
            e.printStackTrace();
        } catch (CannotProceed e) {
            e.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
            e.printStackTrace();
        }

        System.out.println("Client init config ends...");

        boolean result = system.confirmPurchase("", "");
        System.out.println(result);
    }

}
