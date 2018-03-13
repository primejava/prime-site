
package org.primejava.cms.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.primejava.cms.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUserbyId_QNAME = new QName("http://webservice.cms.primejava.org/", "getUserbyId");
    private final static QName _GetUserbyIdResponse_QNAME = new QName("http://webservice.cms.primejava.org/", "getUserbyIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.primejava.cms.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserbyId }
     * 
     */
    public GetUserbyId createGetUserbyId() {
        return new GetUserbyId();
    }

    /**
     * Create an instance of {@link GetUserbyIdResponse }
     * 
     */
    public GetUserbyIdResponse createGetUserbyIdResponse() {
        return new GetUserbyIdResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserbyId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cms.primejava.org/", name = "getUserbyId")
    public JAXBElement<GetUserbyId> createGetUserbyId(GetUserbyId value) {
        return new JAXBElement<GetUserbyId>(_GetUserbyId_QNAME, GetUserbyId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserbyIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cms.primejava.org/", name = "getUserbyIdResponse")
    public JAXBElement<GetUserbyIdResponse> createGetUserbyIdResponse(GetUserbyIdResponse value) {
        return new JAXBElement<GetUserbyIdResponse>(_GetUserbyIdResponse_QNAME, GetUserbyIdResponse.class, null, value);
    }

}
