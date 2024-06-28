package ProjetoEmpresa.DAO;



import org.hibernate.SessionFactory;

import jakarta.persistence.*;
import java.util.List;

public class GenericaDAO<T> {

    private Class<T> entityClass;
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericaDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        HibernateUtil.inSession(sessionFactory, em -> em.persist(entity));
    }

    public void update(T entity) {
        HibernateUtil.inSession(sessionFactory, em -> em.merge(entity));
    }

    public void delete(Long id) {
        HibernateUtil.inSession(sessionFactory, em -> {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
        });
    }

    public T find(Long id) {
        EntityManager em = sessionFactory.createEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    public List<T> findAll() {
        EntityManager em = sessionFactory.createEntityManager();
        List<T> entities = em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        em.close();
        return entities;
    }


}
