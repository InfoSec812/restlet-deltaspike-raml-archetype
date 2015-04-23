#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.api.ToDoResource;
import ${package}.api.ToDosResource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestletRamlApplicationTest {
  
  private RestletRamlApplication app;
  
  @Before
  public void setup() {
    app = new RestletRamlApplication();
  }

  /**
   * Test of createInboundRoot method, of class RestletRamlApplication.
   */
  @Test
  public void testCreateInboundRoot() {
    Restlet router = app.createInboundRoot();
    assertTrue("Returned object MUST be instance of Router", Router.class.isInstance(router));
    int endpointCount = ((Router)router).getRoutes().size();
    assertTrue(String.format("Router MUST have 3 mapped endpoints but actually has %d endpoints", endpointCount), endpointCount==3);
  }
  
}
