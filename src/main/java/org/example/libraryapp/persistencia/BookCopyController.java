package org.example.libraryapp.persistencia;

import org.example.libraryapp.logica.BookCopy;

import javax.persistence.*;

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

    public long getTotalCopy() {
        EntityManager em = getEntityManager();
        long totalBooks = (long) em.createQuery("SELECT COUNT(b) FROM BookCopy b").getSingleResult();
        em.close();
        return totalBooks;
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }

    public Integer getAvailableCopyId(Integer bookId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c.copy_id FROM BookCopy c WHERE c.book.book_id = :bookId AND c.status = 'Available'", Integer.class);
            query.setParameter("bookId", bookId);
            query.setMaxResults(1);
            return (Integer) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}