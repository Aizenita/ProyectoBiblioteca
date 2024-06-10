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

public class HomeController implements IController{

    @FXML
    private HBox root;

    @FXML
    private HBox contentPane;

    @FXML
    private Button closeButton;

    @FXML
    public Label totalBooksLabel;

    @FXML
    public Label totalUsersLabel;

    @FXML
    public Label totalCopiesLabel;

    @FXML
    public JFXButton newMemberButton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        int totalBooks = getTotalBooks();
        totalBooksLabel.setText(String.valueOf(totalBooks));

        int totalUsers = getTotalUsers();
        totalUsersLabel.setText(String.valueOf(totalUsers));

        int totalCopies = getTotalCopies();
        totalCopiesLabel.setText(String.valueOf(totalCopies));
    }

    private void loadContent(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            contentPane.getChildren().setAll(node);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo cargar la vista: " + fxmlFile);

            alert.showAndWait();
        }
    }

    private void loadContentRoot(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            root.getChildren().setAll(node);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo cargar la vista: " + fxmlFile);

            alert.showAndWait();
        }
    }

    public void btn_workbench(MouseEvent event) {
        // Your code here
    }



    private void closeApp() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public int getTotalBooks() {
        BookController bookController = new BookController();
        return (int) bookController.getTotalBooks();
    }

    public int getTotalUsers() {
        BookController bookController = new BookController();
        return (int) bookController.getTotalMembers();
    }

    public int getTotalCopies() {
        BookCopyController bookController = new BookCopyController();
        return (int) bookController.getTotalCopy();
    }
    @FXML
    @Override
    public void handleNewMemberButtonAction(MouseEvent event) {
        loadContentRoot("/newMemberForm.fxml");
    }

    @Override
    @FXML
    public void homeButtonAction(MouseEvent event) {
        loadContent("/home.fxml");
    }

    @Override
    @FXML
    public void returnBookButtonAction(MouseEvent event) {

    }

    @Override
    @FXML
    public void deleteMemberButtonAction(MouseEvent event) {

    }

    @Override
    @FXML
    public void loansHistoryButtonAction(MouseEvent event) {

    }
}
