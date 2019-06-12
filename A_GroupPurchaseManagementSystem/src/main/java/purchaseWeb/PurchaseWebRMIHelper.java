package purchaseWeb;

import banksystem.BankSystemImpl;
import shortMessage.ShortMessageSenderImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PurchaseWebRMIHelper {

    public static void initRMI() {
        try {
            PurchaseWebService purchaseWebService = new purchaseWebServiceImpl(new ShortMessageSenderImpl(), new BankSystemImpl());
            LocateRegistry.createRegistry(8888);
            Naming.rebind("rmi://localhost:8888/purchaseWebService", purchaseWebService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
