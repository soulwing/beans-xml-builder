package org.soulwing.cdi.beans;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit tests for {@link ConcreteInsertableBuilder}.
 *
 * @author Carl Harris
 */
public class ConcreteInsertableBuilderTest {

  @Rule
  public final JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private DescriptorBuilder descriptorBuilder;


  private ConcreteInsertableBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new ConcreteInsertableBuilder(descriptorBuilder);
  }

  @Test
  public void testInsertBeforeClass() throws Exception {
    builder.append(Long.class)
        .insertBefore(Long.class, Integer.class);
    assertThat(builder.toList(),
        contains(Integer.class.getName(), Long.class.getName()));
  }

  @Test
  public void testInsertBeforeClassWhenNotFOund() throws Exception {
    builder.append(Byte.class)
        .insertBefore(Long.class, Integer.class);
    assertThat(builder.toList(),
        contains(Integer.class.getName(), Byte.class.getName()));
  }

  @Test
  public void testInsertAfterClass() throws Exception {
    builder.append(Long.class)
        .insertAfter(Long.class, Integer.class);
    assertThat(builder.toList(),
        contains(Long.class.getName(), Integer.class.getName()));
  }

  @Test
  public void testInsertAfterClassWhenNotFound() throws Exception {
    builder.append(Byte.class)
        .insertAfter(Long.class, Integer.class);
    assertThat(builder.toList(),
        contains(Byte.class.getName(), Integer.class.getName()));
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
