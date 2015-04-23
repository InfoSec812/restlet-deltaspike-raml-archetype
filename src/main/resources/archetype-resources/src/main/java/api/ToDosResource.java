#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.data.ToDoDAO;
import ${package}.types.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

@Dependent
public class ToDosResource extends ServerResource implements Serializable {
  
  @Inject
  private ToDoDAO dao;

  @Get("json|xml|csv")
  public List<ToDo> getAllToDo() {
    List<ToDo> todos = dao.getAllToDos();
    return todos;
  }
  
  @Post("json|xml|csv")
  public ToDo addToDo(ToDo item) throws ResourceException {
    ToDo todo = dao.addToDo(item);
    if (todo==null || todo.id()==null) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to persist the ToDo entity");
    }
    return todo;
  }
}
