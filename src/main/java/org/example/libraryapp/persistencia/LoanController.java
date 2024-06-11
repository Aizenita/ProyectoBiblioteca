package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LoanController {

    public LoanController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createLoan(Loan loan) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    public Loan findLoan(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Loan.class, id);
    }

    public void updateLoan(Loan loan) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteLoan(Integer id) {
        EntityManager em = getEntityManager();
        Loan loan = em.find(Loan.class, id);
        em.getTransaction().begin();
        em.remove(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

  public List<Loan> getAllLoans(int pageNumber, int loansPerPage) {
    EntityManager em = getEntityManager();
    try {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Loan.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(loansPerPage);
        q.setFirstResult((pageNumber - 1) * loansPerPage);
        return q.getResultList();
    } finally {
        em.close();
    }
}

}
