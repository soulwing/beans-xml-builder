package org.soulwing.cdi.beans;

/**
 * A builder for CDI alternative bean classes and stereotypes.
 *
 * @author Carl Harris
 */
public interface AlternativesBuilder {

  /**
   * Gets the builder for the set of alternative bean classes.
   * @return classes builder
   */
  AppendableBuilder classes();

  /**
   * Gets the builder for the set of alternative stereotypes.
   * @return stereotypes builder
   */
  AppendableBuilder stereotypes();

}
