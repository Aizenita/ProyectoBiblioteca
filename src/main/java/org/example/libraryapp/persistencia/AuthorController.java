package org.example.libraryapp.persistencia;


import org.example.libraryapp.logica.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AuthorController {

    public AuthorController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    public Author findAuthor(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Author.class, id);
    }

    public void updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(author);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAuthor(Integer id) {
        EntityManager em = getEntityManager();
        Author author = em.find(Author.class, id);
        em.getTransaction().begin();
        em.remove(author);
        em.getTransaction().commit();
        em.close();
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

}
