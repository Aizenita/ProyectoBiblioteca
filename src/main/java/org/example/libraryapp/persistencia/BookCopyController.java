package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.BookCopy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookCopyController {

    public BookCopyController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createBookCopy(BookCopy bookCopy) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(bookCopy);
        em.getTransaction().commit();
        em.close();
    }

    public BookCopy findBookCopy(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(BookCopy.class, id);
    }

    public void updateBookCopy(BookCopy bookCopy) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(bookCopy);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBookCopy(Integer id) {
        EntityManager em = getEntityManager();
        BookCopy bookCopy = em.find(BookCopy.class, id);
        em.getTransaction().begin();
        em.remove(bookCopy);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

}