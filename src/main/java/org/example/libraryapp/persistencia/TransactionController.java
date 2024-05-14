package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionController {

    public TransactionController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createTransaction(Transaction transaction) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        em.close();
    }

    public Transaction findTransaction(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Transaction.class, id);
    }

    public void updateTransaction(Transaction transaction) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(transaction);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTransaction(Integer id) {
        EntityManager em = getEntityManager();
        Transaction transaction = em.find(Transaction.class, id);
        em.getTransaction().begin();
        em.remove(transaction);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

}
