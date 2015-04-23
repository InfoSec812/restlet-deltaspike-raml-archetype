#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.types;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ToDoTest extends GenericBeanTester {

  @Test
  public void testBean() {
    tester.testBean(ToDo.class, config);
  }
}