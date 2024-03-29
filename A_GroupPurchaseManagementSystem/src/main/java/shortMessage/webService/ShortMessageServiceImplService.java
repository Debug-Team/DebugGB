
package shortMessage.webService;

import util.IPUtil;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ShortMessageServiceImplService", targetNamespace = "http://message/", wsdlLocation = "http://localhost:9000/sendShortMessages?wsdl")
public class ShortMessageServiceImplService
    extends Service
{

    private final static URL SHORTMESSAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SHORTMESSAGESERVICEIMPLSERVICE_EXCEPTION;
    private final static QName SHORTMESSAGESERVICEIMPLSERVICE_QNAME = new QName("http://message/", "ShortMessageServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
//            url = new URL("http://localhost:9000/sendShortMessages?wsdl");
            url = new URL("http://"+ IPUtil.REMOTE_IP +":9000/sendShortMessages?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SHORTMESSAGESERVICEIMPLSERVICE_WSDL_LOCATION = url;
        SHORTMESSAGESERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public ShortMessageServiceImplService() {
        super(__getWsdlLocation(), SHORTMESSAGESERVICEIMPLSERVICE_QNAME);
    }

    public ShortMessageServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SHORTMESSAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public ShortMessageServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SHORTMESSAGESERVICEIMPLSERVICE_QNAME);
    }

    public ShortMessageServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SHORTMESSAGESERVICEIMPLSERVICE_QNAME, features);
    }

    public ShortMessageServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShortMessageServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ShortMessageServiceImpl
     */
    @WebEndpoint(name = "ShortMessageServiceImplPort")
    public ShortMessageServiceImpl getShortMessageServiceImplPort() {
        return super.getPort(new QName("http://message/", "ShortMessageServiceImplPort"), ShortMessageServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShortMessageServiceImpl
     */
    @WebEndpoint(name = "ShortMessageServiceImplPort")
    public ShortMessageServiceImpl getShortMessageServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://message/", "ShortMessageServiceImplPort"), ShortMessageServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SHORTMESSAGESERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw SHORTMESSAGESERVICEIMPLSERVICE_EXCEPTION;
        }
        return SHORTMESSAGESERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
