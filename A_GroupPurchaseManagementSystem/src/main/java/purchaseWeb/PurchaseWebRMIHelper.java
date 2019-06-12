package purchaseWeb;

import assignment3.GroupPurchaseManagementSystem;
import banksystem.BankSystemImpl;
import shortMessage.ShortMessageSenderImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PurchaseWebRMIHelper {

    public static void initRMI(GroupPurchaseManagementSystem systemInstance) {
        try {
            System.out.println("-------------RMI连接启动-------------");
            PurchaseWebService purchaseWebService = new purchaseWebServiceImpl(systemInstance);
            LocateRegistry.createRegistry(8888);
            Naming.rebind("rmi://localhost:8888/purchaseWebService", purchaseWebService);
        } catch (Exception e) {
            System.out.println("-------------RMI连接失败-------------");
            e.printStackTrace();

        }
    }
}
