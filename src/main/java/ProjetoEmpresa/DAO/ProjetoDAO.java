package ProjetoEmpresa.DAO;

import ProjetoEmpresa.Model.Projeto;
import jakarta.persistence.*;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProjetoDAO extends GenericaDAO<Projeto> {

    public ProjetoDAO() {
        super(Projeto.class);
    }

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Adiciona métodos CRUD específicos para Projeto
    public void save(Projeto projeto) {
        HibernateUtil.inSession(sessionFactory, em -> em.persist(projeto));
    }

    public void update(Projeto projeto) {
        HibernateUtil.inSession(sessionFactory, em -> em.merge(projeto));
    }

    public void delete(Long id) {
        HibernateUtil.inSession(sessionFactory, em -> {
            Projeto projeto = em.find(Projeto.class, id);
            if (projeto != null) {
                em.remove(projeto);
            }
        });
    }

    public Projeto find(Long id) {
        EntityManager em = sessionFactory.createEntityManager();
        Projeto projeto = em.find(Projeto.class, id);
        em.close();
        return projeto;
    }

    public List<Projeto> findAll() {
        EntityManager em = sessionFactory.createEntityManager();
        List<Projeto> projetos = em.createQuery("from Projeto", Projeto.class).getResultList();
        em.close();
        return projetos;
    }
}
