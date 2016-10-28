package org.soulwing.cdi.beans;

import java.io.InputStream;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;

import org.soulwing.cdi.beans.model.Beans;

/**
 * A factory that produces {@link DescriptorBuilder} objects.
 *
 * @author Carl Harris
 */
public class DescriptorBuilderFactory {

  public static DescriptorBuilder newBuilder() {
    return new ConcreteDescriptorBuilder();
  }

  public static DescriptorBuilder newBuilder(Source source)
      throws DescriptorBuildException {
    try {
      return ConcreteDescriptorBuilder.fromBeans(
          (Beans) Beans.createUnmarshaller().unmarshal(source));
    }
    catch (JAXBException ex) {
      throw new DescriptorBuildException(ex);
    }
  }

  public static DescriptorBuilder newBuilder(InputStream inputStream)
      throws DescriptorBuildException {
    try {
      return ConcreteDescriptorBuilder.fromBeans(
          (Beans) Beans.createUnmarshaller().unmarshal(inputStream));
    }
    catch (JAXBException ex) {
      throw new DescriptorBuildException(ex);
    }
  }

}
