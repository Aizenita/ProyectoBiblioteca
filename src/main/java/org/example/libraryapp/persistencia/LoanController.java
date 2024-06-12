package org.example.libraryapp.persistencia;

import javafx.scene.control.Alert;
import org.example.libraryapp.logica.Book;
import org.example.libraryapp.logica.BookCopy;
import org.example.libraryapp.logica.Loan;
import org.example.libraryapp.logica.Member;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Calendar;
import java.util.Date;
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

    public void createNewLoan(Integer memberId, Integer bookId, Integer copyId) {
        Loan loan = new Loan();
        BookCopyController bookCopyController = new BookCopyController();
        MemberController memberController = new MemberController();
        BookController bookController = new BookController(); // Asegúrate de tener una clase BookController

        BookCopy bookCopy = bookCopyController.findBookCopy(copyId);
        Member member = memberController.findMember(memberId);
        Book book = bookController.findBook(bookId); // Encuentra el Book usando el bookId

        // Establece el Book en el BookCopy
        bookCopy.setBook(book);
        loan.setBook(book);
        loan.setBookCopy(bookCopy);
        loan.setMember(member);
        loan.setLoan_date(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 7);
        loan.setReturn_date(c.getTime());
        loan.setStatus("Pending");
        createLoan(loan);

        // Mostrar un mensaje de éxito
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Préstamo exitoso");
        alert.setHeaderText(null);
        alert.setContentText("El préstamo se ha realizado con éxito.");
        alert.showAndWait();
    }

   public void llamarProcedimientoReturnandUpdateLoan(Integer loanId) {
    EntityManager em = getEntityManager();
    StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("ReturnandUpdateLoan");

    storedProcedure.setParameter("loan_id", loanId);

    storedProcedure.execute();
    em.close();
}
}
