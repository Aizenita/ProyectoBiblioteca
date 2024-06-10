package org.example.libraryapp.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface IController {

    @FXML
    public void handleNewMemberButtonAction(MouseEvent event);

    @FXML
    public void homeButtonAction(MouseEvent event);

    @FXML
    public void returnBookButtonAction(MouseEvent event);

    @FXML
    public void deleteMemberButtonAction(MouseEvent event);

    @FXML
    public void loansHistoryButtonAction(MouseEvent event);
}
