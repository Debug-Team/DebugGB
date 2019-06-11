package message;

import javax.jws.WebMethod;

@javax.jws.WebService
public interface ShortMessageService {

    @WebMethod
    boolean sendMessage(String receiver, String msg);
}
