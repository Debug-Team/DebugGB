package corba;

import GroupPurchaseManagementSystemModule.GroupPurchaseManagementSystemPOA;
import assignment3.GroupPurchaseManagementSystem;

public class GroupPurchaseManagementSystemCORBA extends GroupPurchaseManagementSystemPOA {

    private GroupPurchaseManagementSystem system;

    public GroupPurchaseManagementSystemCORBA(GroupPurchaseManagementSystem systemInstance){
        this.system = systemInstance;
    }

    @Override
    public boolean confirmPurchase(String sellerSecretKey, String confirm) {
        if(system == null){
            System.err.println("system is null");
            return false;
        }
        return system.confirmPurchase(sellerSecretKey, confirm);
    }

    @Override
    public boolean publishGroupPurchaseItem(String sellerSecretKey, String productName, String introduction, double price, int limit) {
        if(system == null){
            System.err.println("system is null");
            return false;
        }
        return system.publishGroupPurchaseItem(sellerSecretKey, productName, introduction, price, limit);
    }
}
