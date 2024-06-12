package org.example.libraryapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.libraryapp.logica.BookCopy;
import org.example.libraryapp.logica.Loan;
import org.example.libraryapp.persistencia.LoanController;
import org.example.libraryapp.persistencia.BookCopyController;

import java.io.IOException;
import java.util.Objects;

public class ReturnBookController implements IController{

    @FXML
    private HBox root;

    @FXML
    private TextField loanIdField;

    private LoanController loanController;
    private BookCopyController bookCopyController;

    private void loadContentRoot(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            root.getChildren().setAll(node);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo cargar la vista: " + fxmlFile);
            alert.showAndWait();
        }
    }

    public ReturnBookController() {
        this.loanController = new LoanController();
        this.bookCopyController = new BookCopyController();
    }

    @FXML
    public void handleReturnBookAction() {
    Integer loanId = Integer.parseInt(loanIdField.getText());
    Loan loan = loanController.findLoan(loanId);
    if (loan != null && loan.getStatus().equals("Pending")) {
        // Llama al procedimiento almacenado para devolver y actualizar el préstamo
        loanController.llamarProcedimientoReturnandUpdateLoan(loanId);

        BookCopy bookCopy = loan.getBookCopy();
        bookCopy.setStatus("Available");
        bookCopyController.updateBookCopy(bookCopy);
    }
}

    @FXML
    public void handleNewMemberButtonAction(MouseEvent event) {
        loadContentRoot("/newMemberForm.fxml");
    }

    @FXML
    public void homeButtonAction(MouseEvent event) {
        loadContentRoot("/home.fxml");
    }

    @Override
    @FXML
    public void loanBookButtonAction(MouseEvent event) {
        loadContentRoot("loan.fxml");
    }

    @Override
    @FXML
    public void returnBookButtonAction(MouseEvent event) {
        loadContentRoot("returnBook.fxml");

    }

    @Override
    @FXML
    public void deleteMemberButtonAction(MouseEvent event) {
        loadContentRoot("deleteMember.fxml");
    }

    @Override
    @FXML
    public void loansHistoryButtonAction(MouseEvent event) {

    }
}