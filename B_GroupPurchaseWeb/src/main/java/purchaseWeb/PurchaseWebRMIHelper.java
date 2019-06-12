package purchaseWeb;


import java.rmi.Naming;

public class PurchaseWebRMIHelper {


    public static PurchaseWebService getRemotePurchaseService() {
        PurchaseWebService purchaseWebService = null;
        try {
            purchaseWebService = (PurchaseWebService) Naming.lookup("rmi://localhost:8888/purchaseWebService");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchaseWebService;

    }

}
