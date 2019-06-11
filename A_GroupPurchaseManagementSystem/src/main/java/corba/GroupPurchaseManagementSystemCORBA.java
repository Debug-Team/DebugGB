package corba;

import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemPOA;

public class GroupPurchaseManagementSystemCORBA extends GroupPurchaseManagementSystemPOA {

    @Override
    public boolean confirmPurchase(String sellerSecretKey, String confirm) {
        return true;
    }

    @Override
    public boolean publishGroupPurchaseItem(String sellerSecretKey, String productName, String introduction, double price, int limit) {
        return false;
    }
}
