package purchaseWeb;


import util.IPUtil;

import java.rmi.Naming;

public class PurchaseWebRMIHelper {

    private static PurchaseWebService purchaseWebService;

    public static PurchaseWebService getRemotePurchaseService() {
        if(purchaseWebService == null){
            try {
                System.out.println("-------------RMI连接启动-------------");
//                purchaseWebService = (PurchaseWebService) Naming.lookup("rmi://localhost:8888/purchaseWebService");
                purchaseWebService = (PurchaseWebService) Naming.lookup("rmi://" + IPUtil.REMOTE_IP + ":8888/purchaseWebService");

            } catch (Exception e) {
                System.out.println("-------------RMI连接失败-------------");
                e.printStackTrace();
            }
        }
        return purchaseWebService;

    }

}
