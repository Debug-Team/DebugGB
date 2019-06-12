package purchaseWeb;

import assignment3.GroupPurchaseItem;

import java.rmi.Remote;
import java.util.List;

public interface PurchaseWebService extends Remote {

    List<GroupPurchaseItem> listGroupPurchase();

    boolean submitPurchase(String itemId, String bankAccount, String
            password, String phone);

}
