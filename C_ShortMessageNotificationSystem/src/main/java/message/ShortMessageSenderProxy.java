package message;


import util.IPUtil;

import javax.xml.ws.Endpoint;

public class ShortMessageSenderProxy {

    public static void main(String[] argv) {
//        String address = "http://localhost:9000/sendShortMessages";
        String address = "http://" + IPUtil.THIS_IP + ":9000/sendShortMessages";
        Endpoint.publish(address, new ShortMessageServiceImpl());
        System.out.println("启动成功");
    }
}
