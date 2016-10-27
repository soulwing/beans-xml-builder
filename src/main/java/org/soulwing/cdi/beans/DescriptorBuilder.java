package org.soulwing.cdi.beans;

import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

/**
 * A builder that programmatically assembles a {@code beans.xml} deployment
 * descriptor.
 *
 * @author Carl Harris
 */
public interface DescriptorBuilder {

  /**
   * Specifies the beans descriptor version.
   * @param version descriptor version
   * @return this builder
   */
  DescriptorBuilder version(Version version);

  /**
   * Specifies the bean discovery mode for the descriptor.
   * @param discoveryMode discovery mode
   * @return this builder
   */
  DescriptorBuilder discoveryMode(DiscoveryMode discoveryMode);

  /**
   * Gets a builder for the list of bean alternatives to be enabled in
   * the descriptor.
   * @return alternatives builder
   */
  AppendableBuilder alternatives();

  /**
   * Gets a builder for the list of bean decorators to be enabled in
   * the descriptor.
   * @return decorators builder
   */
  InsertableBuilder decorators();

  /**
   * Gets a builder for the list of bean interceptors to be enabled in the
   * descriptor.
   * @return interceptors builder
   */
  InsertableBuilder interceptors();

  /**
   * Builds the descriptor into the given XML transformation result.
   * @param result that will receive the XML representation of the descriptor
   * @throws DescriptorBuildException if an error occurs in building the
   *    descriptor
   */
  void build(Result result) throws DescriptorBuildException;

  void buildToStream(OutputStream outputStream)
      throws DescriptorBuildException;

}
