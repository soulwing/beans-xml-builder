package org.soulwing.cdi.beans.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

/**
 * A JAXB model for a bean class element.
 *
 * @author Carl Harris
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanClass {

  @XmlValue
  private String className;

  public BeanClass() {
  }

  public BeanClass(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;

  }
}
