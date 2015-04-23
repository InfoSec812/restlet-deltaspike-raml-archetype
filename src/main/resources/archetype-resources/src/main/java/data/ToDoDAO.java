#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import ${package}.types.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class ToDoDAO implements Serializable {

    @Inject
    private EntityManager em;

    public ToDoDAO() {
    }

    @Transactional
    public List<ToDo> getAllToDos() {
        try {
            return em.createQuery("FROM ToDo", ToDo.class).getResultList();
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo getToDo(Long id) {
        try {
            return em.find(ToDo.class, id);
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo addToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            return retVal;
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo updateToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            return retVal;
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public boolean deleteToDo(Long id) {
        try {
            em.remove(em.find(ToDo.class, id));
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return false;
        }
        return true;
    }

}