package org.example.libraryapp.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoanControllerIGU implements IController{

    @FXML
    private HBox root;

    @FXML
    public JFXButton newMemberButton;

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
}
