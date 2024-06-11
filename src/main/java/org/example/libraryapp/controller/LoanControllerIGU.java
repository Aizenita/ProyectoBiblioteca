package org.example.libraryapp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.libraryapp.persistencia.BookCopyController;
import org.example.libraryapp.persistencia.LoanController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Objects;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class LoanControllerIGU implements IController{

    @FXML
    private HBox root;

    @FXML
    public JFXButton newMemberButton;

    @FXML
    public JFXButton loan_book_button;

    @FXML
    public TextField bookIdTextField;

    @FXML
    public TextField memberIdTextField;

    @FXML
    public void initialize() {

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

    private void closeApp() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loanBookButtonAction(MouseEvent event) {
        Integer bookId = Integer.valueOf(bookIdTextField.getText());
        Integer memberId = Integer.valueOf(memberIdTextField.getText()); // Nuevo código
        BookCopyController copyController = new BookCopyController();
        Integer copyId = copyController.getAvailableCopyId(bookId);
        if (copyId != null) {
            LoanController loanController = new LoanController();
            loanController.createNewLoan(memberId, bookId, copyId); // Modificado para incluir memberId
            loadContentRoot("loan.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No hay copias disponibles de este libro.");
            alert.showAndWait();
        }
    }
}



