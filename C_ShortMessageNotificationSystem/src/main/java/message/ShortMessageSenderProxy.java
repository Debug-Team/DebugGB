package message;


import javax.xml.ws.Endpoint;

public class ShortMessageSenderProxy {

    public static void main(String[] argv) {
        String address = "http://localhost:9000/sendShortMessages";
        Endpoint.publish(address, new ShortMessageServiceImpl());
    }
}
