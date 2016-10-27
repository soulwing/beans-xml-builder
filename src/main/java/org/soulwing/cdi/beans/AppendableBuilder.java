package org.soulwing.cdi.beans;

/**
 * A builder for an appendable list of bean classes.
 *
 * @author Carl Harris
 */
interface AppendableBuilder {

  /**
   * Appends the given class to the list of bean classes.
   * @param beanClass the bean class to append
   * @return this builder
   */
  AppendableBuilder append(Class<?> beanClass);

  /**
   * Appends the given class to the list of bean classes.
   * @param beanClassName the fully-qualified bean class name to append
   * @return this builder
   */
  AppendableBuilder append(String beanClassName);

  /**
   * Removes the given class from the list of bean classes.
   * @param beanClass the bean class to remove
   * @return this builder
   */
  AppendableBuilder remove(Class<?> beanClass);

  /**
   * Removes the given class from the list of bean classes.
   * @param beanClassName the fully-qualified name of the bean class to remove
   * @return this builder
   */
  AppendableBuilder remove(String beanClassName);

  /**
   * Return to descriptor builder.
   * @return descriptor builder
   */
  DescriptorBuilder end();

}
