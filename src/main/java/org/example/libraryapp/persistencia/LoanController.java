package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
