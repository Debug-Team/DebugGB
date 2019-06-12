package purchaseWeb;

import assignment3.GroupPurchaseItem;
import assignment3.GroupPurchaseManagementSystem;

import java.util.List;

public class GroupPurchaseManagementSystemImpl implements GroupPurchaseManagementSystem {

    public List<GroupPurchaseItem> listGroupPurchase() {
        return PurchaseWebRMIHelper.getRemotePurchaseService().listGroupPurchase();
    }

    public boolean submitPurchase(String s, String s1, String s2, String s3) {
        return PurchaseWebRMIHelper.getRemotePurchaseService().submitPurchase(s, s1, s2, s3);
    }
}
