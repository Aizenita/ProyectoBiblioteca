package org.example.libraryapp.persistencia;

import org.example.libraryapp.Views.*;
import org.example.libraryapp.logica.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.StoredProcedureQuery;
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

    public void llamarProcedimientoRegistrarMiembro(String memberName, String address, String email, String phone) {
        EntityManager em = getEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("RegisterMember");

        storedProcedure.setParameter("member_name", memberName);
        storedProcedure.setParameter("address", address);
        storedProcedure.setParameter("email", email);
        storedProcedure.setParameter("phone", phone);

        storedProcedure.execute();
        em.close();
    }

    public void llamarProcedimientoCheckInBook(Integer memberId, Integer bookId) {
        EntityManager em = getEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("CheckInBook");

        storedProcedure.setParameter("member_id", memberId);
        storedProcedure.setParameter("book_id", bookId);

        storedProcedure.execute();
        em.close();
    }


    public void llamarProcedimientoReturnandUpdateLoan(Integer loanId) {
        EntityManager em = getEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("ReturnAndUpdateStatusBook");

        storedProcedure.setParameter("loan_id", loanId);

        storedProcedure.execute();
        em.close();
    }

    public void guardarMiembro(Member member){
        memberController.createMember(member);
    }

    public void guardarLibro(Book book){
        bookController.createBook(book);
    }

    public void guardarPrestamo(Loan loan){
        loanController.createLoan(loan);
    }

    public void guardarNotificacion(Notification notification){
        notificationController.createNotification(notification);
    }

    public void guardarTransaccion(Transaction transaction){
        transactionController.createTransaction(transaction);
    }
    //Creamos en la base de datos un autor
    public void guardarAutor(Author author){
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

    public List<books_with_copy_status> obtenerLibrosConEstadoCopias() {
        EntityManager em = getEntityManager();
        List<books_with_copy_status> resultados = em.createQuery("SELECT b FROM books_with_copy_status b", books_with_copy_status.class).getResultList();
        em.close();
        return resultados;
    }

    public List<member_loans_history> obtenerHistorialPrestamosMiembro(Integer id) {
        EntityManager em = getEntityManager();
        List<member_loans_history> resultados = em.createQuery("SELECT m FROM member_loans_history m WHERE m.member_id = :id", member_loans_history.class).setParameter("id", id).getResultList();
        em.close();
        return resultados;
    }
    public List<overdue_loans_view> obtenerPrestamosVencidos() {
        EntityManager em = getEntityManager();
        List<overdue_loans_view> resultados = em.createQuery("SELECT o FROM overdue_loans_view o", overdue_loans_view.class).getResultList();
        em.close();
        return resultados;
    }

    public List<popular_genres_view> obtenerGenerosPopulares() {
        EntityManager em = getEntityManager();
        List<popular_genres_view> resultados = em.createQuery("SELECT p FROM popular_genres_view p", popular_genres_view.class).getResultList();
        em.close();
        return resultados;
    }

}
