package purchaseWeb;


import java.rmi.Naming;

public class PurchaseWebRMIHelper {


    public static PurchaseWebService getRemotePurchaseService() {
        PurchaseWebService purchaseWebService = null;
        try {
            System.out.println("-------------RMI连接启动-------------");
            purchaseWebService = (PurchaseWebService) Naming.lookup("rmi://localhost:8888/purchaseWebService");

        } catch (Exception e) {
            System.out.println("-------------RMI连接失败-------------");
            e.printStackTrace();
        }
        return purchaseWebService;

    }

}
