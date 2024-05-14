package org.example.libraryapp.persistencia;

import org.example.libraryapp.Views.availableBooks;
import org.example.libraryapp.logica.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControladoraPersistencia {

    EntityManagerFactory emf;
    AuthorController authorController= new AuthorController();
    BookController bookController= new BookController();
    MemberController memberController= new MemberController();
    BookCopyController bookCopyController= new BookCopyController();
    LoanController loanController= new LoanController();
    NotificationController notificationController= new NotificationController();
    TransactionController transactionController= new TransactionController();


    public ControladoraPersistencia() {
        emf = authorController.getEntityManager().getEntityManagerFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    //Creamos en la base de datos un autor
    public void crearAutor(Author author){
        authorController.createAuthor(author);
    }

    //Buscamos un autor en la base de datos
    public Author buscarAutor(Integer id){
        return authorController.findAuthor(id);
    }

    //Actualizamos un autor en la base de datos
    public void actualizarAutor(Author author){
        authorController.updateAuthor(author);
    }

    //Eliminamos un autor de la base de datos
    public void eliminarAutor(Integer id){
        authorController.deleteAuthor(id);
    }

    //Llamar a la view available_books_view para obtener los libros disponibles
    public List<availableBooks> obtenerLibrosDisponibles() {
        EntityManager em = getEntityManager();
        List<availableBooks> resultados = em.createQuery("SELECT v FROM available_books_view v", availableBooks.class).getResultList();
        em.close();
        return resultados;
    }

    //Llamar a la view  authors_books_count para obtener la cantidad de libros que tiene cada autor
    public List<Author> obtenerCantidadLibrosPorAutor() {
        EntityManager em = getEntityManager();
        List<Author> resultados = em.createQuery("SELECT a FROM authors_books_count a", Author.class).getResultList();
        em.close();
        return resultados;
    }






}
