package org.soulwing.cdi.beans;

import org.junit.Test;

/**
 * TODO: DESCRIBE THE TYPE HERE
 *
 * @author Carl Harris
 */
public class ConcreteDescriptorBuilderTest {

  private ConcreteDescriptorBuilder builder =
      new ConcreteDescriptorBuilder();

  @Test
  public void test() throws Exception {
    builder
        .discoveryMode(DiscoveryMode.ANNOTATED)
        .alternatives()
          .append(Integer.class)
          .append(Long.class)
          .end()
        .interceptors()
          .append(Number.class)
          .insertBefore(Number.class, String.class)
          .end()
      .buildToStream(System.out);
  }

}
