package org.soulwing.cdi.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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

  private Version version = Version.V1_0;
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
      XMLStreamWriter writer =
          XMLOutputFactory.newFactory().createXMLStreamWriter(result);
      writer.setDefaultNamespace(version.namespaceUri);
      writer.setPrefix("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
      writer.writeStartDocument();
      writer.writeStartElement(version.namespaceUri, BeansXml.BEANS);
      writer.writeDefaultNamespace(version.namespaceUri);
      writer.writeNamespace("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
      writer.writeAttribute(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
          "schemaLocation", version.namespaceUri + " " + version.schemaLocation);
      writer.writeAttribute(BeansXml.DISCOVERY_MODE,
          discoveryMode.name().toLowerCase());
      writeClassList(BeansXml.ALTERNATIVES, alternativesBuilder.toList(), writer);
      writeClassList(BeansXml.DECORATORS, decoratorsBuilder.toList(), writer);
      writeClassList(BeansXml.INTERCEPTORS, interceptorsBuilder.toList(), writer);
      writer.writeEndElement();
      writer.writeEndDocument();
    }
    catch (RuntimeException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new DescriptorBuildException(ex);
    }

  }

  private void writeClassList(String listElement, List<String> classNames,
      XMLStreamWriter writer) throws XMLStreamException {
    if (classNames.isEmpty()) return;
    writer.writeStartElement(listElement);
    for (final String className : classNames) {
      writer.writeStartElement(BeansXml.CLASS);
      writer.writeCharacters(className);
      writer.writeEndElement();
    }
    writer.writeEndElement();
  }

}
