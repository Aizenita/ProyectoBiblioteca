package org.example.libraryapp.persistencia;
import org.example.libraryapp.logica.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class BookController {

    public BookController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        EntityManager em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        return emf.createEntityManager();
    }

    public void createBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

    public Book findBook(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Book.class, id);
    }

    public void updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBook(Integer id) {
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, id);
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
        em.close();
    }

    //available_books_view
    public void availableBooks() {
        EntityManager em = getEntityManager();
        em.createQuery("SELECT b FROM Book b WHERE b.available = true").getResultList();
        em.close();
    }

    public long getTotalBooks() {
        EntityManager em = getEntityManager();
        long totalBooks = (long) em.createQuery("SELECT COUNT(b) FROM Book b").getSingleResult();
        em.close();
        return totalBooks;
    }

    public long getTotalMembers() {
        EntityManager em = getEntityManager();
        long totalMembers = (long) em.createQuery("SELECT COUNT(m) FROM Member m").getSingleResult();
        em.close();
        return totalMembers;
    }

    public void close() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryAppPU");
        emf.close();
    }


}
