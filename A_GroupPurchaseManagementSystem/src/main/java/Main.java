import assignment3.GroupPurchaseManagementSystem;
import assignment3.GroupPurchaseManagementSystemFactory;
import corba.GroupPurchaseManagementSystemCORBAServer;
import purchaseWeb.PurchaseWebRMIHelper;

public class Main {

    public static void main(String[] args) {

//        GroupPurchaseManagementSystem ins = GroupPurchaseManagementSystemFactory

        PurchaseWebRMIHelper.initRMI();
//        GroupPurchaseManagementSystemCORBAServer corbaServer = new GroupPurchaseManagementSystemCORBAServer();
    }
}
