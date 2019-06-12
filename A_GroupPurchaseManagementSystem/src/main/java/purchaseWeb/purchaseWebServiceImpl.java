package purchaseWeb;

import assignment3.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class purchaseWebServiceImpl extends UnicastRemoteObject implements PurchaseWebService {

    private GroupPurchaseManagementSystem groupPurchaseManagementSystem;

    public purchaseWebServiceImpl() throws RemoteException {
    }

    public purchaseWebServiceImpl(ShortMessageSender shortMessageSender, BankSystem bankSystem) throws RemoteException {
        this.groupPurchaseManagementSystem = GroupPurchaseManagementSystemFactory.createGroupPurchaseManagementSystem(shortMessageSender, bankSystem);
    }

    public purchaseWebServiceImpl(GroupPurchaseManagementSystem systemInstance) throws RemoteException{
        this.groupPurchaseManagementSystem = systemInstance;
    }

    @Override
    public List<MyGroupPurchaseItem> listGroupPurchase() throws RemoteException {
        List<GroupPurchaseItem> list = this.groupPurchaseManagementSystem.listGroupPurchase();
        List<MyGroupPurchaseItem> res = new ArrayList<>();
        for (GroupPurchaseItem groupPurchaseItem : list) {
            res.add(new MyGroupPurchaseItem(groupPurchaseItem));
        }
        return res;
    }

    @Override
    public boolean submitPurchase(String itemId, String bankAccount, String password, String phone)throws RemoteException {
        return this.groupPurchaseManagementSystem.submitPurchase(itemId, bankAccount, password, phone);
    }
}
