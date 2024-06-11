package org.example.libraryapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.libraryapp.persistencia.MemberController;

import java.io.IOException;
import java.util.Objects;

public class DeleteMemberController implements IController{

    @FXML
    private HBox root;

    @FXML
    private TextField memberIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    private MemberController memberController = new MemberController();

    @FXML
    public void handleDeleteMemberAction() {
        String memberIdText = memberIdField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Member");

        if (memberIdText != null && !memberIdText.isEmpty()) {
            // Delete member by ID
            int memberId = Integer.parseInt(memberIdText);
            memberController.deleteMember(memberId);
            alert.setHeaderText("Member Deleted");
            alert.setContentText("Member with ID " + memberId + " has been successfully deleted.");
        } else if (name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {
            // Delete member by name and surname
            memberController.deleteMemberByNameAndSurname(name, surname);
            alert.setHeaderText("Member Deleted");
            alert.setContentText("Member " + name + " " + surname + " has been successfully deleted.");
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Please enter either a member ID or a name and surname.");
        }

        alert.showAndWait();
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
    @Override
    @FXML
    public void handleNewMemberButtonAction(MouseEvent event) {
        loadContentRoot("/newMemberForm.fxml");
    }

    @Override
    @FXML
    public void homeButtonAction(MouseEvent event) {
        loadContentRoot("/home.fxml");
    }

    @Override
    public void loanBookButtonAction(MouseEvent event) {
        loadContentRoot("/loan.fxml");
    }

    @Override
    @FXML
    public void returnBookButtonAction(MouseEvent event) {
        loadContentRoot("/returnBook.fxml");
    }

    @Override
    @FXML
    public void deleteMemberButtonAction(MouseEvent event) {
        loadContentRoot("/deleteMember.fxml");
    }

    @Override
    @FXML
    public void loansHistoryButtonAction(MouseEvent event) {
        loadContentRoot("/loansHistory.fxml");
    }
}
