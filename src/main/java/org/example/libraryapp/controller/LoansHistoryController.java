package org.example.libraryapp.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import com.jfoenix.controls.JFXButton;
import org.example.libraryapp.logica.Loan;
import org.example.libraryapp.persistencia.LoanController;
import org.example.libraryapp.logica.Book;


import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class LoansHistoryController implements IController{

    @FXML
    private HBox root;

    @FXML
    private TableView<Loan> loansTable;

    @FXML
    private JFXButton nextButton;

    private int currentPage = 1;

    private static final int LOANS_PER_PAGE = 50;

    public void loadNextPage() {
        currentPage++;
        loadData();
    }

public void initialize() {
        // Crear las columnas
        TableColumn<Loan, Integer> column1 = new TableColumn<>("Loan ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("loan_id"));

        TableColumn<Loan, Date> column2 = new TableColumn<>("Loan Date");
        column2.setCellValueFactory(new PropertyValueFactory<>("loan_date"));

        TableColumn<Loan, Date> column3 = new TableColumn<>("Return Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("return_date"));

        TableColumn<Loan, String> column4 = new TableColumn<>("Status");
        column4.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Loan, String> column5 = new TableColumn<>("Book Title");
        column5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBook().getTitle()));

        loansTable.getColumns().setAll(column1, column2, column3, column4, column5);
        // Cargar los datos
         loadData();
    }
    public void loadData() {
        LoanController loanController = new LoanController();
        List<Loan> loans = loanController.getAllLoans(currentPage, LOANS_PER_PAGE);

        // Agregar los datos a la tabla
        ObservableList<Loan> loansObservableList = FXCollections.observableArrayList(loans);
        loansTable.setItems(loansObservableList);
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

    public void nextButtonAction(MouseEvent event) {
        loadNextPage();
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
