package purchaseWeb;

import assignment3.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
    public List<GroupPurchaseItem> listGroupPurchase() throws RemoteException {
        return this.groupPurchaseManagementSystem.listGroupPurchase();
    }

    @Override
    public boolean submitPurchase(String itemId, String bankAccount, String password, String phone)throws RemoteException {
        return this.groupPurchaseManagementSystem.submitPurchase(itemId, bankAccount, password, phone);
    }
}
