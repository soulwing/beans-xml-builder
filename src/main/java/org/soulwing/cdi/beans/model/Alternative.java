package org.soulwing.cdi.beans.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO: DESCRIBE THE TYPE HERE
 *
 * @author Carl Harris
 */
public interface Alternative {

  @XmlRootElement(name = XML.STEREOTYPE)
  class Stereotype
      extends org.soulwing.cdi.beans.model.BeanClass
      implements Alternative {

    @SuppressWarnings("unused")
    public Stereotype() {
    }

    public Stereotype(String className) {
      super(className);
    }

  }

  @XmlRootElement(name = XML.BEAN_CLASS)
  class BeanClass
      extends org.soulwing.cdi.beans.model.BeanClass
      implements Alternative {

    @SuppressWarnings("unused")
    public BeanClass() {
    }

    public BeanClass(String className) {
      super(className);
    }

  }

}
