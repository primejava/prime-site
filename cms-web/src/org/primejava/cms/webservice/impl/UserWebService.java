
package org.primejava.cms.webservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.primejava.cms.webservice.ObjectFactory;
import org.primejava.cms.webservice.User;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserWebService", targetNamespace = "http://webservice.cms.primejava.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserWebService {


    /**
     * 
     * @param arg0
     * @return
     *     returns org.primejava.cms.webservice.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUserbyId", targetNamespace = "http://webservice.cms.primejava.org/", className = "org.primejava.cms.webservice.GetUserbyId")
    @ResponseWrapper(localName = "getUserbyIdResponse", targetNamespace = "http://webservice.cms.primejava.org/", className = "org.primejava.cms.webservice.GetUserbyIdResponse")
    public User getUserbyId(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
