package shortMessage;

import assignment3.ShortMessageSender;
import shortMessage.webService.ShortMessageServiceImpl;
import shortMessage.webService.ShortMessageServiceImplService;

public class ShortMessageSenderImpl implements ShortMessageSender {

    @Override
    public boolean sendMessage(String s, String s1) {

        ShortMessageServiceImplService shortMessageServiceImplService = new ShortMessageServiceImplService();
        ShortMessageServiceImpl shortMessageService = shortMessageServiceImplService.getShortMessageServiceImplPort();
        return shortMessageService.sendMessage(s, s1);

    }
}
