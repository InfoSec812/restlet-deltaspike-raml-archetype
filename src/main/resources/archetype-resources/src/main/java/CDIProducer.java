#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CDIProducer {

  EntityManagerFactory emf;

  @Produces
  @Dependent
  @Default
  public EntityManager create() {
    createPU();
    return this.emf.createEntityManager();
  }

  @Produces
  @Dependent
  @Default
  public PersistenceUnitUtil getUtil() {
    createPU();
    return emf.getPersistenceUnitUtil();
  }

  public void dispose(@Disposes @Default EntityManager em) {
    if (em.isOpen()) {
      LOG.debug("Disposing of EntityManager");
      em.close();
    }
  }

  private void createPU() {
    if (emf == null) {
      emf = Persistence.createEntityManagerFactory("todo");
    }
  }
}
