package purchaseWeb;

import assignment3.GroupPurchaseItem;
import assignment3.GroupPurchaseManagementSystem;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GroupPurchaseManagementSystemImpl implements GroupPurchaseManagementSystem {

    public List<GroupPurchaseItem> listGroupPurchase() {
        try {
            System.out.println("-------------获取今日团购-------------");
            List<MyGroupPurchaseItem> list = PurchaseWebRMIHelper.getRemotePurchaseService().listGroupPurchase();
            List<GroupPurchaseItem> res = new ArrayList<GroupPurchaseItem>();
            for (MyGroupPurchaseItem myGroupPurchaseItem : list) {
                res.add(myGroupPurchaseItem.standard());
            }
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean submitPurchase(String s, String s1, String s2, String s3) {
        try {
            System.out.println("-------------提交购买-------------");
            return PurchaseWebRMIHelper.getRemotePurchaseService().submitPurchase(s, s1, s2, s3);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
