#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.data.ToDoDAO;
import ${package}.types.ToDo;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

@Dependent
public class ToDoResource extends ServerResource implements Serializable {
  Long id;
  
  @Inject
  private ToDoDAO dao;

  @Override
  protected void doInit() throws ResourceException {
    try {
      id = Long.parseLong(getAttribute("id"));
    } catch (NumberFormatException nfe) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, String.format("ToDo ID of '%d' is not a valid long integer.", id), nfe);
    }
  }

  @Get("json|xml")
  public ToDo getToDo() {
    ToDo todo = dao.getToDo(id);
    if (todo==null) {
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
    return todo;
  }
  
  @Put("json|xml")
  public ToDo updateToDo(ToDo item) {
    ToDo todo = dao.updateToDo(item);
    if (todo==null) {
       throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to update ToDo entity.");
    }
    return todo;
  }
  
  @Delete
  public void deleteToDo() {
    if (!dao.deleteToDo(id)) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
    }
  }
}
