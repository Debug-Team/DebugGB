package banksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystem {
    private static Map<String, Double> bankAccounts = new HashMap<String, Double>(){
        {
            this.put("buyer", 99999.0);
            this.put("system", 0.0);
            this.put("seller", 0.0);
        }
    };
    private static Map<String, List<Record>> recordLists = new HashMap<String, List<Record>>(){
        {
            this.put("buyer", new ArrayList<>());
            this.put("system", new ArrayList<>());
            this.put("seller", new ArrayList<>());
        }
    };

    public static boolean transfer(String account, String password, String target, double amount){
        if(!bankAccounts.containsKey(account)){
            System.out.println("源账户不存在："+account);
            return false;
        }
        if(!"123".equals(password)){
            System.out.println("密码错误");
            return false;
        }
        if(!bankAccounts.containsKey(target)){
            System.out.println("目标账户不存在："+account);
            return false;
        }
        boolean result;
        //扣除原账户
        double balance1 = bankAccounts.get(account) - amount;
        if(balance1 >= 0){
            bankAccounts.put(account, balance1);
            double balance2 = bankAccounts.get(target) + amount;
            bankAccounts.put(target, balance2);
            System.out.println("转账成功 " + account + " " + password + " " + target + " " + amount);
            //添加转账记录
            List<Record> recordList = recordLists.get(target);
            recordList.add(new Record(account, target, amount));
            recordLists.put(target, recordList);
            result = true;
        }
        else {
            System.out.println("转账失败 " + account + " " + password + " " + target + " " + amount + " 余额不足");
            result = false;
        }
        return result;
    }

    public static List<Record> listHistory(String account, String password){
        if(!recordLists.containsKey(account)){
            System.out.println("账户不存在");
            return new ArrayList<>();
        }
        if(!("123".equals(password) || "123\0".equals(password))){
            System.out.println("密码错误");
            return new ArrayList<>();
        }
        return recordLists.get(account);
    }

    public static void main(String[] args){
        transfer("buyer", "123", "system", 20);
        transfer("buyer", "123", "system", 20);
        transfer("system", "123", "seller", 20);
        System.out.println(listHistory("buyer", "123").toString());
    }
}
