package org.soulwing.cdi.beans;

import java.util.List;
import java.util.stream.Stream;

/**
 * A concrete {@link AppendableBuilder} implementation.
 *
 * @author Carl Harris
 */
class ConcreteAppendableBuilder implements AppendableBuilder {

  private final DescriptorBuilder descriptorBuilder;
  private final MutableList<String> list = new MutableList<>();

  ConcreteAppendableBuilder(DescriptorBuilder descriptorBuilder) {
    this.descriptorBuilder = descriptorBuilder;
  }

  @Override
  public AppendableBuilder append(Class<?> beanClass) {
    return append(beanClass.getName());
  }

  @Override
  public AppendableBuilder append(String beanClassName) {
    list.append(beanClassName);
    return this;
  }

  @Override
  public AppendableBuilder remove(Class<?> beanClass) {
    return remove(beanClass.getName());
  }

  @Override
  public AppendableBuilder remove(String beanClassName) {
    list.remove(beanClassName);
    return this;
  }

  @Override
  public DescriptorBuilder end() {
    return descriptorBuilder;
  }

  List<String> toList() {
    return list.toList();
  }

  boolean isEmpty() {
    return list.isEmpty();
  }

}
