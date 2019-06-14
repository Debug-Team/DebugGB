package banksystem;

import org.zeromq.ZMQ;
import util.IPUtil;

import java.io.UnsupportedEncodingException;

public class BankSystemMQ {

    private static ZMQ.Socket socket = null;

    public static void initMQClient(){
        try{
            ZMQ.Context context = ZMQ.context(1);
            ZMQ.Socket socket = context.socket(ZMQ.REQ);

            // System.out.println("Connecting to hello world server...");
            socket.connect("tcp://localhost:9999");

            BankSystemMQ.socket = socket;
        }catch (Exception e){
            System.err.println("连接MQ失败");
        }
    }

    /**
     * 转账，使用MQ，发送转账请求
     * @param account
     * @param password
     * @param target
     * @param amount
     * @return
     */
    public static boolean transfer(String account, String password, String target, double amount) throws Exception{
        if(socket == null){
            initMQClient();
        }
        String str = account + "," + password + "," + target + "," + amount + "\0";

        socket.send(str, 0);
        Thread.sleep(100);
        byte[] reply = socket.recv(0);
        String reply_str = new String(reply);
        System.out.println("客户端接收的是: [" + reply_str + "]");
        boolean result = reply_str.contains("1");
        System.out.println(result);

        return result;
    }

    //测试用main
    public static void main(String[] args){
        initMQClient();
        try {
            transfer("buyer", "123", "seller", 20.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
