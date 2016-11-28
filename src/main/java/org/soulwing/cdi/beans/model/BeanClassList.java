package org.soulwing.cdi.beans.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * A JAXB model for a list of bean classes.
 *
 * @author Carl Harris
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanClassList {

  @XmlElement(name = XML.BEAN_CLASS)
  private List<BeanClass> classes = new ArrayList<>();

  public List<BeanClass> getClasses() {
    return classes;
  }

  public void addClass(String className) {
    getClasses().add(new BeanClass(className));
  }

}
