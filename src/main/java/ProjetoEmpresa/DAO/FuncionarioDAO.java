package ProjetoEmpresa.DAO;

import ProjetoEmpresa.Model.Funcionario;
import jakarta.persistence.*;
import org.hibernate.SessionFactory;

import java.util.List;

public class FuncionarioDAO extends GenericaDAO<Funcionario> {

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Adiciona métodos CRUD específicos para Funcionario
    public void save(Funcionario funcionario) {
        HibernateUtil.inSession(sessionFactory, em -> em.persist(funcionario));
    }

    public void update(Funcionario funcionario) {
        HibernateUtil.inSession(sessionFactory, em -> em.merge(funcionario));
    }

    public void delete(Long id) {
        HibernateUtil.inSession(sessionFactory, em -> {
            Funcionario funcionario = em.find(Funcionario.class, id);
            if (funcionario != null) {
                em.remove(funcionario);
            }
        });
    }

    public Funcionario find(Long id) {
        EntityManager em = sessionFactory.createEntityManager();
        Funcionario funcionario = em.find(Funcionario.class, id);
        em.close();
        return funcionario;
    }

    public List<Funcionario> findAll() {
        EntityManager em = sessionFactory.createEntityManager();
        List<Funcionario> funcionarios = em.createQuery("from Funcionario", Funcionario.class).getResultList();
        em.close();
        return funcionarios;
    }

    // Método para buscar funcionários por departamento
    public List<Funcionario> findByDepartamento(Long departamentoId) {
        EntityManager em = sessionFactory.createEntityManager();
        List<Funcionario> funcionarios = em.createQuery("select f from Funcionario f where f.departamento.id = :departamentoId", Funcionario.class)
                .setParameter("departamentoId", departamentoId)
                .getResultList();
        em.close();
        return funcionarios;
    }

    // Método para buscar funcionários por projeto
    public List<Funcionario> findByProjeto(Long projetoId) {
        EntityManager em = sessionFactory.createEntityManager();
        List<Funcionario> funcionarios = em.createQuery("select f from Funcionario f where f.projeto.id = :projetoId", Funcionario.class)
                .setParameter("projetoId", projetoId)
                .getResultList();
        em.close();
        return funcionarios;
    }
}