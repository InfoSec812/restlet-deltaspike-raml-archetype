#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.data.ToDoDAO;
import ${package}.types.ToDo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.restlet.resource.ResourceException;

public class ToDosResourceTest {

  @InjectMocks
  private ToDosResource res;
  
  @Mock
  private ToDoDAO dao;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test of getAllToDo method, of class ToDosResource.
   */
  @Test
  public void testGetAllToDo() {
    when(dao.getAllToDos()).thenReturn(new ArrayList<>());
    List<ToDo> todos = res.getAllToDo();
    assertTrue("List MUST be empty.", todos.isEmpty());
    verify(dao).getAllToDos();
    reset(dao);
    
    ArrayList<ToDo> result = new ArrayList<>();
    result.add(mock(ToDo.class));
    result.add(mock(ToDo.class));
    when(dao.getAllToDos()).thenReturn(result);
    todos = res.getAllToDo();
    assertTrue("List MUST be of size 2", todos.size()==2);
    verify(dao).getAllToDos();
    reset(dao);
  }

  /**
   * Test of addToDo method, of class ToDosResource.
   */
  @Test
  public void testAddToDo() {
    when(dao.addToDo(any(ToDo.class))).thenReturn(null);
    try {
      res.addToDo(mock(ToDo.class));
      fail("When dao returns null, resource MUST throw ResourceException");
    } catch (Exception e) {
      assertTrue("Exception type MUST be ResourceException", ResourceException.class.isInstance(e));
    }
    verify(dao).addToDo(any(ToDo.class));
    reset(dao);
    
    ToDo todoMock = mock(ToDo.class);
    when(dao.addToDo(any(ToDo.class))).thenReturn(todoMock);
    when(todoMock.id()).thenReturn(null);
    try {
      res.addToDo(mock(ToDo.class));
      fail("When dao returns entity with null ID, resource MUST throw ResourceException");
    } catch (Exception e) {
      assertTrue("Exception type MUST be ResourceException", ResourceException.class.isInstance(e));
    }
    verify(dao).addToDo(any(ToDo.class));
    verify(todoMock).id();
    reset(dao);
    reset(todoMock);
    
    when(dao.addToDo(any(ToDo.class))).thenReturn(todoMock);
    when(todoMock.id()).thenReturn(1L);
    ToDo result = res.addToDo(mock(ToDo.class));
    assertEquals("Mock object and result MUST be equal.", result, todoMock);
    verify(dao).addToDo(any(ToDo.class));
    verify(todoMock).id();
    reset(dao);
    reset(todoMock);
  }
  
}
