package corba;

import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystem;
import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

public class GroupPurchaseManagementSystemCORBAServer {
    private ORB orb;
    private POA rootPOA;
    private org.omg.CORBA.Object obj;
    private org.omg.CORBA.Object ref;
    private org.omg.CORBA.Object objRef;
    private NamingContextExt ncRef;

    public GroupPurchaseManagementSystemCORBAServer(){
        try {
            //启动orb端口
            String[] command = {"orbd", "-ORBInitialPort", "6000", "-ORBInitialHost", "127.0.0.1"};
            Process p = Runtime.getRuntime().exec(command);
//            p.waitFor();
//            p.destroy();

            String[] args = {};
            Properties properties = new Properties();

            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
            properties.put("org.omg.CORBA.ORBInitialPort", "6000");       //指定ORB的端口

            //创建一个ORB实例
            orb = ORB.init(args, properties);

            //拿到根POA的引用,并激活POAManager,相当于启动了server
            obj = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(obj);
            rootPOA.the_POAManager().activate();

            //创建一个GroupPurchaseManagementSystemCORBA实例
            GroupPurchaseManagementSystemCORBA groupPurchaseManagementSystemCORBA = new GroupPurchaseManagementSystemCORBA();

            //从服务中得到对象的引用,并注册到服务中
            ref = rootPOA.servant_to_reference(groupPurchaseManagementSystemCORBA);
            GroupPurchaseManagementSystem systemHref = GroupPurchaseManagementSystemHelper.narrow(ref);

            //得到一个根命名的上下文
            objRef = orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);

            //在命名上下文中绑定这个对象
            String name = "GroupPurchaseManagementSystem";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, systemHref);

            System.out.println("server.GroupPurchaseManagementSystemCORBAServer is ready and waiting....");

            //启动线程服务,等待客户端调用
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
