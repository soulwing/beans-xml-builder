package org.soulwing.cdi.beans.model;

/**
 * Constants for the CDI XML beans descriptor.
 *
 * @author Carl Harris
 */
interface XML {

  String NAMESPACE = "http://xmlns.jcp.org/xml/ns/javaee";

  String DISCOVERY_MODE = "bean-discovery-mode";

  String ALTERNATIVES = "alternatives";

  String DECORATORS = "decorators";

  String INTERCEPTORS = "interceptors";

  String BEAN_CLASS = "class";

  String STEREOTYPE = "stereotype";

}
