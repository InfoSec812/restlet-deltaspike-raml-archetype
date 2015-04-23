#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.data.ToDoDAO;
import ${package}.types.ToDo;
import java.util.concurrent.ConcurrentMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.restlet.Request;
import org.restlet.resource.ResourceException;

public class ToDoResourceTest {
  
  @Mock
  private ToDoDAO dao;
  
  @Mock
  private Request request;
  
  @InjectMocks
  private ToDoResource res;
  
  private ConcurrentMap<String, Object> attr = mock(ConcurrentMap.class);
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    res.setRequest(request);
  }

  /**
   * Test of doInit method, of class ToDoResource.
   */
  @Test
  public void testDoInit() {
    when(request.getAttributes()).thenReturn(attr);
    when(attr.get(anyString())).thenReturn("A");
    try {
      res.doInit();
      fail("When the requested ToDo ID is not a number, getToDo MUST throw an exception");
    } catch (Exception re) {
      assertTrue("When 'id' field is not a number, getToDo MUST throw ResourceException", ResourceException.class.isInstance(re));
    }
    reset(request);
    reset(attr);
    
    when(request.getAttributes()).thenReturn(attr);
    when(attr.get(anyString())).thenReturn("1");
    when(dao.getToDo(anyLong())).thenReturn(mock(ToDo.class));
    res.doInit();
    verify(request).getAttributes();
    verify(attr).get(anyString());
    reset(request);
    reset(attr);
    reset(dao);
  }

  /**
   * Test of getToDo method, of class ToDoResource.
   */
  @Test
  public void testGetToDo() {
    res.id = 1L;
    when(dao.getToDo(anyLong())).thenReturn(null);
    try {
      ToDo result = res.getToDo();
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).getToDo(anyLong());
    reset(dao);
    
    when(dao.getToDo(anyLong())).thenReturn(mock(ToDo.class));
    ToDo result = res.getToDo();
    assertNotNull("getToDo MUST NOT return a null value when DAO returns a valid object.", result);
    verify(dao).getToDo(anyLong());
    reset(dao);
  }

  /**
   * Test of updateToDo method, of class ToDoResource.
   */
  @Test
  public void testUpdateToDo() {
    res.id = 1L;
    when(dao.updateToDo(any(ToDo.class))).thenReturn(null);
    try {
      ToDo result = res.updateToDo(mock(ToDo.class));
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).updateToDo(any(ToDo.class));
    reset(dao);
    
    when(dao.updateToDo(any(ToDo.class))).thenReturn(mock(ToDo.class));
    ToDo result = res.updateToDo(mock(ToDo.class));
    assertNotNull("getToDo MUST NOT return a null value when DAO returns a valid object.", result);
    verify(dao).updateToDo(any(ToDo.class));
    reset(dao);
  }

  /**
   * Test of deleteToDo method, of class ToDoResource.
   */
  @Test
  public void testDeleteToDo() {
    res.id = 1L;
    doReturn(false).when(dao).deleteToDo(anyLong());
    try {
      res.deleteToDo();
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).deleteToDo(anyLong());
    reset(dao);
    
    doReturn(true).when(dao).deleteToDo(anyLong());
    res.deleteToDo();
    verify(dao).deleteToDo(anyLong());
    reset(dao);
  }
  
}
