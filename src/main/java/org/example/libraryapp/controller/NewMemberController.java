package org.example.libraryapp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.libraryapp.logica.Member;
import org.example.libraryapp.persistencia.BookController;
import org.example.libraryapp.persistencia.BookCopyController;
import org.example.libraryapp.persistencia.MemberController;

import java.io.IOException;
import java.util.Objects;

public class NewMemberController implements IController{

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;

    @FXML
    private HBox root;

    @FXML
    public JFXButton newMemberButton;

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleResetButtonAction(MouseEvent event) {
        // Limpiar los campos del formulario
        nameField.clear();
        surnameField.clear();
        addressField.clear();
        phoneNumberField.clear();
        emailField.clear();
    }

    @FXML
    public void handleSubmitButtonAction(MouseEvent event) {
        // Crear un nuevo objeto Member
        Member newMember = new Member();
        newMember.setName(nameField.getText());
        newMember.setSurname(surnameField.getText());
        newMember.setAddress(addressField.getText());
        newMember.setPhone_number(phoneNumberField.getText());
        newMember.setEmail(emailField.getText());

        // Crear un nuevo MemberController y usarlo para guardar el nuevo miembro
        MemberController memberController = new MemberController();
        memberController.createMember(newMember);

        // Mostrar un mensaje de éxito
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("New member has been added successfully!");
        alert.showAndWait();

        // Limpiar los campos del formulario
        nameField.clear();
        surnameField.clear();
        addressField.clear();
        phoneNumberField.clear();
        emailField.clear();
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
        loadContentRoot("loansHistory.fxml");
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
        loadContentRoot("loansHistory.fxml");
    }
}
