package purchaseWeb;

import assignment3.GroupPurchaseItem;
import assignment3.GroupPurchaseManagementSystem;

import java.rmi.RemoteException;
import java.util.List;

public class GroupPurchaseManagementSystemImpl implements GroupPurchaseManagementSystem {

    public List<GroupPurchaseItem> listGroupPurchase() {
        try {
            return PurchaseWebRMIHelper.getRemotePurchaseService().listGroupPurchase();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean submitPurchase(String s, String s1, String s2, String s3) {
        try {
            return PurchaseWebRMIHelper.getRemotePurchaseService().submitPurchase(s, s1, s2, s3);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
