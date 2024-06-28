package ProjetoEmpresa.DAO;

import ProjetoEmpresa.Model.Departamento;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;



public class DepartamentoDAO extends GenericaDAO<Departamento> {

    public DepartamentoDAO() {
        super(Departamento.class);
    }

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Adiciona métodos CRUD específicos para Departamento
    public void save(Departamento departamento) {
        HibernateUtil.inSession(sessionFactory, em -> em.persist(departamento));
    }

    public void update(Departamento departamento) {
        HibernateUtil.inSession(sessionFactory, em -> em.merge(departamento));
    }

    public void delete(Long id) {
        HibernateUtil.inSession(sessionFactory, em -> {
            Departamento departamento = em.find(Departamento.class, id);
            if (departamento != null) {
                em.remove(departamento);
            }
        });
    }

    public Departamento find(Long id) {
        EntityManager em = sessionFactory.createEntityManager();
        Departamento departamento = em.find(Departamento.class, id);
        em.close();
        return departamento;
    }

    public List<Departamento> findAll() {
        EntityManager em = sessionFactory.createEntityManager();
        List<Departamento> departamentos = em.createQuery("from Departamento", Departamento.class).getResultList();
        em.close();
        return departamentos;
    }


}
