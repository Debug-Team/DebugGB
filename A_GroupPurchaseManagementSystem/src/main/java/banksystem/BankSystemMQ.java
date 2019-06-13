package banksystem;

import org.zeromq.ZMQ;

import java.io.UnsupportedEncodingException;

public class BankSystemMQ {

    public static void initMQClient(){
        try{
            ZMQ.Context context = ZMQ.context(1);
            ZMQ.Socket socket = context.socket(ZMQ.REQ);

            // System.out.println("Connecting to hello world server...");
            socket.connect("tcp://localhost:9999");

            String str = "getSingle";
            String requestString = null;
            try {
                requestString = new String(str.getBytes("GB2312"),"ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //byte[] request = requestString.getBytes();
            socket.send(requestString, 0);
            Thread.sleep(100);
            byte[] reply = socket.recv(0);
            System.out.println("客户端接收的是: [" + new String(reply) + "]");
        }catch (Exception e){

        }
    }

    public static void main(String[] args){
        initMQClient();
    }
}
