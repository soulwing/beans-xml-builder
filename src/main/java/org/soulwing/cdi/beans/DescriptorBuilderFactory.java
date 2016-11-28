package org.soulwing.cdi.beans;

import java.io.InputStream;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.soulwing.cdi.beans.model.Beans;

/**
 * A factory that produces {@link DescriptorBuilder} objects.
 *
 * @author Carl Harris
 */
public class DescriptorBuilderFactory {

  /**
   * Creates a builder that will produce a CDI beans descriptor.
   * @return descriptor builder
   */
  public static DescriptorBuilder newBuilder() {
    return new ConcreteDescriptorBuilder();
  }

  /**
   * Creates a builder that contains the contents of an existing CDI beans
   * descriptor, allowing the contents to be edited.
   * @param source XML source for the existing descriptor
   * @return descriptor builder
   * @throws DescriptorBuildException
   */
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

  /**
   * Creates a builder that contains the contents of an existing CDI beans
   * descriptor, allowing the contents to be edited.
   * @param inputStream input stream for the XML descriptor source
   * @return descriptor builder
   * @throws DescriptorBuildException
   */
  public static DescriptorBuilder newBuilder(InputStream inputStream)
      throws DescriptorBuildException {
    return newBuilder(new StreamSource(inputStream));
  }

}
