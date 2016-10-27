package org.soulwing.cdi.beans;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit tests for {@link ConcreteAppendableBuilder}.
 *
 * @author Carl Harris
 */
public class ConcreteAppendableBuilderTest {

  @Rule
  public final JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private DescriptorBuilder descriptorBuilder;

  private ConcreteAppendableBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new ConcreteAppendableBuilder(descriptorBuilder);
  }

  @Test
  public void testEnd() throws Exception {
    assertThat(builder.end(), is(sameInstance(descriptorBuilder)));
  }

  @Test
  public void testAppendClass() throws Exception {
    builder.append(Integer.class);
    builder.append(Long.class);
    assertThat(builder.toList(),
      contains(Integer.class.getName(), Long.class.getName()));
  }

  @Test
  public void testRemoveClass() throws Exception {
    builder.append(Integer.class);
    assertThat(builder.toList(), contains(Integer.class.getName()));
    builder.remove(Integer.class);
    assertThat(builder.toList(), is(empty()));
  }

}
