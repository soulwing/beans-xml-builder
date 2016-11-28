package org.soulwing.cdi.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.soulwing.cdi.beans.model.Alternative;
import org.soulwing.cdi.beans.model.BeanClass;
import org.soulwing.cdi.beans.model.BeanClassList;
import org.soulwing.cdi.beans.model.Beans;

/**
 * A concrete implementation of {@link DescriptorBuilder}.
 *
 * @author Carl Harris
 */
class ConcreteDescriptorBuilder implements DescriptorBuilder {

  private final ConcreteAppendableBuilder alternativesBuilder =
      new ConcreteAppendableBuilder(this);

  private final ConcreteInsertableBuilder decoratorsBuilder =
      new ConcreteInsertableBuilder(this);

  private final ConcreteInsertableBuilder interceptorsBuilder =
      new ConcreteInsertableBuilder(this);

  private Version version = Version.V1_1;
  private DiscoveryMode discoveryMode = DiscoveryMode.ALL;

  @Override
  public DescriptorBuilder version(Version version) {
    this.version = version;
    return this;
  }

  @Override
  public DescriptorBuilder discoveryMode(DiscoveryMode discoveryMode) {
    this.discoveryMode = discoveryMode;
    return this;
  }

  @Override
  public AppendableBuilder alternatives() {
    return alternativesBuilder;
  }

  @Override
  public InsertableBuilder decorators() {
    return decoratorsBuilder;
  }

  @Override
  public InsertableBuilder interceptors() {
    return interceptorsBuilder;
  }

  @Override
  public void buildToStream(OutputStream outputStream) throws DescriptorBuildException {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      build(new StreamResult(bos));
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      final StreamSource source = new StreamSource(new ByteArrayInputStream(bos.toByteArray()));
      final StreamResult result = new StreamResult(outputStream);
      transformer.transform(source, result);
    }
    catch (RuntimeException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new DescriptorBuildException(ex);
    }
  }

  @Override
  public void build(Result result) throws DescriptorBuildException {
    try {
      final Marshaller marshaller = Beans.createMarshaller(version);
      marshaller.marshal(buildBeans(), result);
    }
    catch (RuntimeException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new DescriptorBuildException(ex);
    }

  }

  private Beans buildBeans() {
    Beans beans = new Beans();
    beans.setDiscoveryMode(discoveryMode);
    for (final Alternative alternative : alternativesBuilder.toList()) {

    }
    buildClassList(beans.getAlternatives(), alternativesBuilder.toList());
    buildClassList(beans.getDecorators(), decoratorsBuilder.toList());
    buildClassList(beans.getInterceptors(), interceptorsBuilder.toList());
    return beans;
  }

  private void buildClassList(BeanClassList classList, List<String> classNames) {
    for (final String className : classNames) {
      classList.addClass(className);
    }
  }

  static ConcreteDescriptorBuilder fromBeans(Beans beans) {
    ConcreteDescriptorBuilder builder = new ConcreteDescriptorBuilder();
    builder.discoveryMode(beans.getDiscoveryMode());
    for (final BeanClass beanClass : beans.getAlternatives().getClasses()) {
      builder.alternatives().append(beanClass.getClassName());
    }
    for (final BeanClass beanClass : beans.getDecorators().getClasses()) {
      builder.decorators().append(beanClass.getClassName());
    }
    for (final BeanClass beanClass : beans.getInterceptors().getClasses()) {
      builder.interceptors().append(beanClass.getClassName());
    }
    return builder;
  }

}
