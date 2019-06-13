import assignment3.GroupPurchaseManagementSystem;
import assignment3.GroupPurchaseManagementSystemFactory;
import banksystem.BankSystemImpl;
import banksystem.BankSystemMQ;
import corba.GroupPurchaseManagementSystemCORBAServer;
import purchaseWeb.PurchaseWebRMIHelper;
import shortMessage.ShortMessageSenderImpl;

public class Main {

    public static void main(String[] args) {

        GroupPurchaseManagementSystem systemInstance =
                GroupPurchaseManagementSystemFactory.createGroupPurchaseManagementSystem(new ShortMessageSenderImpl(), new BankSystemImpl());

        PurchaseWebRMIHelper.initRMI(systemInstance);
        new GroupPurchaseManagementSystemCORBAServer(systemInstance);
        BankSystemMQ.initMQClient();
    }
}
