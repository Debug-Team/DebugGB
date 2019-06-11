package shortMessage;

import shortMessage.webService.ShortMessageServiceImpl;
import shortMessage.webService.ShortMessageServiceImplService;

public class ShortMessages {

    public static boolean send(String receiver, String msg) {

        ShortMessageServiceImplService shortMessageServiceImplService = new ShortMessageServiceImplService();
        ShortMessageServiceImpl shortMessageService = shortMessageServiceImplService.getShortMessageServiceImplPort();
        return shortMessageService.sendMessage(receiver, msg);

    }
}
