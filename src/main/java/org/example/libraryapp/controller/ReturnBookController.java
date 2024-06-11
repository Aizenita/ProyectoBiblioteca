package org.example.libraryapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class ReturnBookController implements IController{

    @FXML
    private HBox root;

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
