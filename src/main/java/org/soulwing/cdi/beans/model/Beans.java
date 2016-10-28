package org.soulwing.cdi.beans.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.soulwing.cdi.beans.DiscoveryMode;

/**
 * A JAXB model for beans.xml
 *
 * @author Carl Harris
 */
@XmlRootElement(name = "beans", namespace = "http://xmlns.jcp.org/xml/ns/javaee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Beans {

  private static final Lock lock = new ReentrantLock();

  private static volatile JAXBContext jaxbContext;

  @XmlAttribute(name = "bean-discovery-mode")
  private String discoveryMode;

  @XmlElement
  private BeanClassList alternatives = new BeanClassList();

  @XmlElement
  private BeanClassList decorators = new BeanClassList();

  @XmlElement
  private BeanClassList interceptors = new BeanClassList();

  public DiscoveryMode getDiscoveryMode() {
    if (discoveryMode == null) return null;
    return DiscoveryMode.valueOf(discoveryMode.toUpperCase());
  }

  public void setDiscoveryMode(DiscoveryMode discoveryMode) {
    this.discoveryMode = discoveryMode != null ?
        discoveryMode.name().toLowerCase() : null;
  }

  public BeanClassList getAlternatives() {
    return alternatives;
  }

  public BeanClassList getDecorators() {
    return decorators;
  }

  public BeanClassList getInterceptors() {
    return interceptors;
  }

  public static JAXBContext getJaxbContext() throws JAXBException {
    if (jaxbContext == null) {
      lock.lock();
      try {
        if (jaxbContext == null) {
          jaxbContext = JAXBContext.newInstance(Beans.class);
        }
      }
      finally {
        lock.unlock();
      }
    }
    return jaxbContext;
  }

  public static Unmarshaller createUnmarshaller() throws JAXBException {
    return getJaxbContext().createUnmarshaller();
  }

  public static Marshaller createMarshaller() throws JAXBException {
    return getJaxbContext().createMarshaller();
  }

}
