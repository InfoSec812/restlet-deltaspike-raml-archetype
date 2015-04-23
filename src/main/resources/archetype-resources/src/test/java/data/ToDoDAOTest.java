#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import ${package}.types.ToDo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ToDoDAOTest {
  
  @Mock
  private EntityManager em;
  
  @InjectMocks
  private ToDoDAO dao;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test of getAllToDos method, of class ToDoDAO.
   */
  @Test
  public void testGetAllToDos() {
    when(em.createQuery(anyString(), any(Class.class))).thenThrow(Exception.class);
    List<ToDo> emptyList = dao.getAllToDos();
    assertNull("List must be null when an Exception is encountered.", emptyList);
    verify(em).createQuery(anyString(), any(Class.class));
    reset(em);
    
    TypedQuery<ToDo> query = mock(TypedQuery.class);
    ArrayList<ToDo> retVal = new ArrayList<>();
    when(em.createQuery(anyString(), any(Class.class))).thenReturn(query);
    when(query.getResultList()).thenReturn(retVal);
    emptyList = dao.getAllToDos();
    assertTrue("List must be empty when no results are returned", emptyList.isEmpty());
    verify(em).createQuery(anyString(), any(Class.class));
    verify(query).getResultList();
    reset(em);
    reset(query);
    
    retVal.add(mock(ToDo.class));
    when(em.createQuery(anyString(), any(Class.class))).thenReturn(query);
    when(query.getResultList()).thenReturn(retVal);
    emptyList = dao.getAllToDos();
    assertTrue("List must have 1 element", emptyList.size()==1);
    verify(em).createQuery(anyString(), any(Class.class));
    verify(query).getResultList();
    reset(em);
    reset(query);
    
    retVal.add(mock(ToDo.class));
    when(em.createQuery(anyString(), any(Class.class))).thenReturn(query);
    when(query.getResultList()).thenReturn(retVal);
    emptyList = dao.getAllToDos();
    assertTrue("List must have 2 elements", emptyList.size()==2);
    verify(em).createQuery(anyString(), any(Class.class));
    verify(query).getResultList();
    reset(em);
    reset(query);
  }

  /**
   * Test of getToDo method, of class ToDoDAO.
   */
  @Test
  public void testGetToDo() {
    when(em.find(any(Class.class), anyLong())).thenThrow(Exception.class);
    ToDo testResult = dao.getToDo(1L);
    assertNull("Result MUST be null when an exception is thrown", testResult);
    verify(em).find(any(Class.class), anyLong());
    reset(em);
    
    when(em.find(any(Class.class), anyLong())).thenReturn(mock(ToDo.class));
    testResult = dao.getToDo(2L);
    assertTrue("Result MUST be of type ToDo.class", ToDo.class.isInstance(testResult));
    verify(em).find(any(Class.class), anyLong());
    reset(em);
  }

  /**
   * Test of addToDo method, of class ToDoDAO.
   */
  @Test
  public void testAddToDo() {
    when(em.merge(any(ToDo.class))).thenThrow(Exception.class);
    ToDo testResult = dao.addToDo(mock(ToDo.class));
    assertNull("Result MUST be NULL when an exception is thrown", testResult);
    verify(em).merge(any(ToDo.class));
    reset(em);
    
    ToDo newToDo = new ToDo().created(new Date()).title("TEST TITLE").description("Test description");
    when(em.merge(any(ToDo.class))).thenReturn(newToDo.id(42L));
    testResult = dao.addToDo(newToDo);
    assertTrue("Result MUST have the id field set.", testResult.id()!=null);
    verify(em).merge(any(ToDo.class));
    reset(em);
  }

  /**
   * Test of updateToDo method, of class ToDoDAO.
   */
  @Test
  public void testUpdateToDo() {
    when(em.merge(any(ToDo.class))).thenThrow(Exception.class);
    ToDo testResult = dao.updateToDo(mock(ToDo.class));
    assertNull("Result MUST be NULL when an exception is thrown", testResult);
    verify(em).merge(any(ToDo.class));
    reset(em);
    
    ToDo newToDo = new ToDo().created(new Date()).title("TEST TITLE").description("Test description");
    when(em.merge(any(ToDo.class))).thenReturn(newToDo.id(42L));
    testResult = dao.updateToDo(newToDo);
    assertTrue("Result MUST have the id field set.", testResult.id()!=null);
    verify(em).merge(any(ToDo.class));
    reset(em);
  }

  /**
   * Test of deleteToDo method, of class ToDoDAO.
   */
  @Test
  public void testDeleteToDo() {
    ToDo todo = mock(ToDo.class);
    when(em.find(any(Class.class), anyLong())).thenReturn(todo);
    doThrow(Exception.class).when(em).remove(any(ToDo.class));
    assertFalse("deleteToDo MUST return false when an exception is thrown.", dao.deleteToDo(1L));
    verify(em).find(any(Class.class), eq(1L));
    verify(em).remove(any(ToDo.class));
    reset(em);
    
    todo = mock(ToDo.class);
    when(em.find(any(Class.class), anyLong())).thenReturn(todo);
    doNothing().when(em).remove(any(ToDo.class));
    assertTrue("deleteToDo MUST return true when no exception is thrown.", dao.deleteToDo(2L));
    verify(em).find(any(Class.class), eq(2L));
    verify(em).remove(any(ToDo.class));
    reset(em);
  }
  
}
