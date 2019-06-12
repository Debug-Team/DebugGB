package message;

import assignment3.ShortMessageSender;
import assignment3.ShortMessageSenderFactory;

import javax.jws.WebService;

@WebService
public class ShortMessageServiceImpl implements ShortMessageService {

    @Override
    public boolean sendMessage(String receiver, String msg) {
        ShortMessageSender sms = ShortMessageSenderFactory.createShortMessageSender();
        return sms.sendMessage(receiver, msg);
    }

}
