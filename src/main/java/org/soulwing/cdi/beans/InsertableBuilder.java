package org.soulwing.cdi.beans;

/**
 * A builder for an insertable list of bean classes.
 *
 * @author Carl Harris
 */
interface InsertableBuilder {

  /**
   * Appends the given class to the list of bean classes.
   * @param beanClass the bean class to append
   * @return this builder
   */
  InsertableBuilder append(Class<?> beanClass);

  /**
   * Appends the given class to the list of bean classes.
   * @param beanClassName the fully-qualified bean class name to append
   * @return this builder
   */
  InsertableBuilder append(String beanClassName);

  /**
   * Removes the given class from the list of bean classes.
   * @param beanClass the bean class to remove
   * @return this builder
   */
  InsertableBuilder remove(Class<?> beanClass);

  /**
   * Removes the given class from the list of bean classes.
   * @param beanClassName the fully-qualified name of the bean class to remove
   * @return this builder
   */
  InsertableBuilder remove(String beanClassName);

  /**
   * Inserts a bean class before another bean class in the list.
   * <p>
   * If {@code otherBeanClass} is not found in the list, {@code beanClass}
   * is inserted as the first class in the list.
   * @param otherBeanClass the bean class to find
   * @param beanClass the bean class to insert
   * @return this builder
   */
  InsertableBuilder insertBefore(Class<?> otherBeanClass, Class<?> beanClass);

  /**
   * Inserts a bean class before another bean class in the list.
   * <p>
   * If {@code otherBeanClassName} is not found in the list, {@code beanClassName}
   * is inserted as the first class name in the list.
   * @param otherBeanClassName the fully-qualified name of the bean class to
   *    find
   * @param beanClassName the fully-qualified bean class name to insert
   * @return this builder
   */
  InsertableBuilder insertBefore(String otherBeanClassName, String beanClassName);

  /**
   * Inserts a bean class after another bean class in the list.
   * <p>
   * If {@code otherBeanClass} is not found in the list, {@code beanClass}
   * is inserted as the last class in the list.
   * @param otherBeanClass the bean class to find
   * @param beanClass the bean class to insert
   * @return this builder
   */
  InsertableBuilder insertAfter(Class<?> otherBeanClass, Class<?> beanClass);

  /**
   * Inserts a bean class after another bean class in the list.
   * <p>
   * If {@code otherBeanClassName} is not found in the list, {@code beanClassName}
   * is inserted as the last class in the list.
   * @param otherBeanClassName the fully-qualified name of the bean class to
   *    find
   * @param beanClassName the fully-qualified bean class name to insert
   * @return this builder
   */
  InsertableBuilder insertAfter(String otherBeanClassName, String beanClassName);

  /**
   * Return to descriptor builder.
   * @return descriptor builder
   */
  DescriptorBuilder end();

}
