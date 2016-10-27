package org.soulwing.cdi.beans;

/**
 * Version tags.
 *
 * @author Carl Harris
 */
@SuppressWarnings("unused")
public enum Version {

  V1_0("http://xmlns.jcp.org/xml/ns/javaee",
      "http://xmlns.jcp.org/xml/ns/javaee/beans_1_0.xsd"),
  V1_1("http://xmlns.jcp.org/xml/ns/javaee",
      "http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd");

  String namespaceUri;
  String schemaLocation;

  Version(String namespaceUri, String schemaLocation) {
    this.namespaceUri = namespaceUri;
    this.schemaLocation = schemaLocation;
  }

}
