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
import org.soulwing.cdi.beans.Version;

/**
 * A JAXB model for beans.xml
 *
 * @author Carl Harris
 */
@XmlRootElement(name = "beans", namespace = XML.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Beans {

  private static final Lock lock = new ReentrantLock();

  private static volatile JAXBContext jaxbContext;

  @XmlAttribute(name = XML.DISCOVERY_MODE)
  private String discoveryMode;

  @XmlElement(name = XML.ALTERNATIVES)
  private AlternativeList alternatives = new AlternativeList();

  @XmlElement(name = XML.DECORATORS)
  private BeanClassList decorators = new BeanClassList();

  @XmlElement(name = XML.INTERCEPTORS)
  private BeanClassList interceptors = new BeanClassList();

  public DiscoveryMode getDiscoveryMode() {
    if (discoveryMode == null) return null;
    return DiscoveryMode.valueOf(discoveryMode.toUpperCase());
  }

  public void setDiscoveryMode(DiscoveryMode discoveryMode) {
    this.discoveryMode = discoveryMode != null ?
        discoveryMode.name().toLowerCase() : null;
  }

  public AlternativeList getAlternatives() {
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
          jaxbContext = JAXBContext.newInstance(Beans.class,
              Alternative.BeanClass.class, Alternative.Stereotype.class);
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

  public static Marshaller createMarshaller(Version version)
      throws JAXBException {
    final Marshaller marshaller = getJaxbContext().createMarshaller();

    marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
        XML.NAMESPACE + " " + version.schemaLocation);
    return marshaller;
  }

}
