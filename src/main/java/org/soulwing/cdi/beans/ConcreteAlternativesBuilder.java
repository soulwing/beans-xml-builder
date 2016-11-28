package org.soulwing.cdi.beans;

/**
 * A concrete {@link AlternativesBuilder} implementation.
 *
 * @author Carl Harris
 */
class ConcreteAlternativesBuilder implements AlternativesBuilder {

  private final ConcreteAppendableBuilder classesBuilder;
  private final ConcreteAppendableBuilder stereotypesBuilder;

  ConcreteAlternativesBuilder(DescriptorBuilder descriptorBuilder) {
    this.classesBuilder = new ConcreteAppendableBuilder(descriptorBuilder);
    this.stereotypesBuilder = new ConcreteAppendableBuilder(descriptorBuilder);
  }

  @Override
  public ConcreteAppendableBuilder classes() {
    return classesBuilder;
  }

  @Override
  public ConcreteAppendableBuilder stereotypes() {
    return stereotypesBuilder;
  }

}
