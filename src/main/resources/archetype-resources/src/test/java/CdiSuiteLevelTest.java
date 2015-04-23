#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.api.ToDoResourceTest;
import ${package}.api.ToDosResourceTest;
import ${package}.data.ToDoDAOTest;
import ${package}.types.ToDoTest;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestSuiteRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(CdiTestSuiteRunner.class)
@Suite.SuiteClasses({
  ToDoDAOTest.class, 
  ToDoResourceTest.class, 
  ToDosResourceTest.class, 
  RestletRamlApplicationTest.class, 
  ToDoTest.class, 
  DateTimeDeserializeConverterTest.class,
  DateTimeSerializeConverterTest.class,
  CDIProducerTest.class
})
public class CdiSuiteLevelTest {

}
