package org.soulwing.cdi.beans.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementRef;

/**
 * A JAXB model for the list of alternative bean classes and stereotypes.
 *
 * @author Carl Harris
 */
public class AlternativeList {

  @XmlElementRef
  private List<Alternative> classes = new ArrayList<>();


  public List<Alternative> getClasses() {
    return classes;
  }

  public void addStereotype(String className) {
    getClasses().add(new Alternative.Stereotype(className));
  }

  public void addClass(String className) {
    getClasses().add(new Alternative.BeanClass(className));
  }

}
