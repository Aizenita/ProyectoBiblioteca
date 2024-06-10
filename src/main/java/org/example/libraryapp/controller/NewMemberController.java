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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.libraryapp.persistencia.BookController;
import org.example.libraryapp.persistencia.BookCopyController;

import java.io.IOException;
import java.util.Objects;

public class NewMemberController implements IController{

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
    public void returnBookButtonAction(MouseEvent event) {

    }

    @Override
    public void deleteMemberButtonAction(MouseEvent event) {

    }

    @Override
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
