package purchaseWeb;

import assignment3.GroupPurchaseItem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PurchaseWebService extends Remote {

    List<GroupPurchaseItem> listGroupPurchase() throws RemoteException;

    boolean submitPurchase(String itemId, String bankAccount, String
            password, String phone) throws RemoteException;

}
