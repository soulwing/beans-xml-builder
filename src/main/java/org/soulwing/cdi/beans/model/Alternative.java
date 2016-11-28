package org.soulwing.cdi.beans.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A JAXB model for CDI alternatives. An alternative can be either a bean
 * class or a stereotype.
 *
 * @author Carl Harris
 */
public abstract class Alternative
    extends org.soulwing.cdi.beans.model.BeanClass {

  public Alternative() {
  }

  public Alternative(String className) {
    super(className);
  }

  @XmlRootElement(name = XML.STEREOTYPE)
  public static class Stereotype extends Alternative {

    @SuppressWarnings("unused")
    public Stereotype() {
    }

    public Stereotype(String className) {
      super(className);
    }

  }

  @XmlRootElement(name = XML.BEAN_CLASS)
  @XmlType(name = "alternativeBeanClass")
  public static class BeanClass extends Alternative {

    @SuppressWarnings("unused")
    public BeanClass() {
    }

    public BeanClass(String className) {
      super(className);
    }

  }

}
