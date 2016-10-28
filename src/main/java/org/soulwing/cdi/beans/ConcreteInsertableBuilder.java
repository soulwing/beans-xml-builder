package org.soulwing.cdi.beans;

import java.util.List;
import java.util.stream.Stream;

/**
 * A concrete {@link InsertableBuilder} implementation.
 *
 * @author Carl Harris
 */
class ConcreteInsertableBuilder implements InsertableBuilder {

  private final DescriptorBuilder descriptorBuilder;
  private final MutableList<String> list = new MutableList<>();

  ConcreteInsertableBuilder(DescriptorBuilder descriptorBuilder) {
    this.descriptorBuilder = descriptorBuilder;
  }

  @Override
  public InsertableBuilder append(Class<?> beanClass) {
    return append(beanClass.getName());
  }

  @Override
  public InsertableBuilder append(String beanClassName) {
    list.append(beanClassName);
    return this;
  }

  @Override
  public InsertableBuilder remove(Class<?> beanClass) {
    return remove(beanClass.getName());
  }

  @Override
  public InsertableBuilder remove(String beanClassName) {
    list.remove(beanClassName);
    return this;
  }

  @Override
  public InsertableBuilder insertBefore(Class<?> otherBeanClass, Class<?> beanClass) {
    return insertBefore(otherBeanClass.getName(), beanClass.getName());
  }

  @Override
  public InsertableBuilder insertBefore(String otherBeanClassName, String beanClassName) {
    list.insertBefore(otherBeanClassName, beanClassName);
    return this;
  }

  @Override
  public InsertableBuilder insertAfter(Class<?> otherBeanClass, Class<?> beanClass) {
    return insertAfter(otherBeanClass.getName(), beanClass.getName());
  }

  @Override
  public InsertableBuilder insertAfter(String otherBeanClassName, String beanClassName) {
    list.insertAfter(otherBeanClassName, beanClassName);
    return this;
  }

  @Override
  public InsertableBuilder clear() {
    list.clear();
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
