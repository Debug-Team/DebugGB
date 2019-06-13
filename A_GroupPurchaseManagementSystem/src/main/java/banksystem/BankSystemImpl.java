package banksystem;

import assignment3.BankSystem;

public class BankSystemImpl implements BankSystem {

    @Override
    public boolean transfer(String account, String password, String target, double amount) {
        try {
            return BankSystemMQ.transfer(account, password, target, amount);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("转账失败");
        }
        return false;
    }
}
