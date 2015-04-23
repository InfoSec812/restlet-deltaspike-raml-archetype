#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {

  public static void main(String[] args) throws Exception {
    CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
    cdiContainer.boot();

    // Starting the all contexts
    cdiContainer.getContextControl().startContexts();
    // You can use CDI here

    Component component = new Component();

    component.getServers().add(Protocol.HTTP, 8180);
    
    Application app = new RestletRamlApplication();

    component.getDefaultHost().attach("/rest/v1", app);

    component.start();
  }
}
