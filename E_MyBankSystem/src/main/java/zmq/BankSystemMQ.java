package zmq;

import banksystem.BankSystem;
import banksystem.Record;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.util.List;

public class BankSystemMQ {

    private static ZMQ.Socket socket = null;

    public static void initMQServerForD() {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        String url = "tcp://*:9999";
        try {
            socket.bind(url);//绑定地址
        } catch (ZMQException e) {
            throw e;
        }
        System.out.println("BankSystemMQ Server For D 启动成功");
        boolean wait = true;
        while (wait) {//服务器一直循环
            byte[] request;
            try {
                request = socket.recv(0);//接收的客户端数据
                String getData = new String(request);
                System.out.println("MQ接收：" + getData);
                //解析参数
                String[] arguments = getData.split(",");
                boolean result = BankSystem.transfer(arguments[0], arguments[1], arguments[2], Double.parseDouble(arguments[3]));
                if (result) {
                    socket.send("1", 1);
                } else {
                    socket.send("0", 1);
                }

            } catch (ZMQException e) {
                throw e;
            }
        } // while(wait)
    }

    public static void initMQServerForA(){
        ZMQ.Context context = ZMQ.context(2);
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        String url = "tcp://*:7777";
        try {
            socket.bind(url);//绑定地址
        } catch (ZMQException e) {
            throw e;
        }
        System.out.println("BankSystemMQ Server For A 启动成功");
        boolean wait = true;
        while (wait) {//服务器一直循环
            byte[] request;
            try {
                request = socket.recv(0);//接收的客户端数据
                String getData = new String(request);
                System.out.println("MQ接收：" + getData);
                //解析参数
                String[] arguments = getData.split(",");
                List<Record> recordList = BankSystem.listHistory(arguments[0], arguments[1]);
                String result = "";
                for(Record record : recordList){
                    result += record.toString() + "#";
                }
                result += "\0";
                socket.send(result, 1);

            } catch (ZMQException e) {
                throw e;
            }
        } // while(wait)
    }

    //测试用main
    public static void main(String[] args){
        Thread threadForD = new Thread(){
            @Override
            public void run() {
                initMQServerForD();
            }
        };
        threadForD.start();
        Thread threadForA = new Thread(){
            @Override
            public void run() {
//                System.out.println("a");
                initMQServerForA();
            }
        };
        threadForA.start();
//        initMQServerForD();

//        banksystem.BankSystem.transfer("buyer", "123", "system", 20);
//        banksystem.BankSystem.transfer("buyer", "123", "system", 20);
    }
}
