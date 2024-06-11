package org.example.libraryapp.controller;

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
        LoanController loanController = new LoanController();
        List<Loan> loans = loanController.getAllLoans(currentPage, LOANS_PER_PAGE);

        ObservableList<Loan> loansObservableList = FXCollections.observableArrayList(loans);
        loansTable.setItems(loansObservableList);
    }

    public void initialize() {
        LoanController loanController = new LoanController();
        List<Loan> loans = loanController.getAllLoans(currentPage, LOANS_PER_PAGE);

        // Crear las columnas
        TableColumn<Loan, String> memberColumn = new TableColumn<>("Member");
        TableColumn<Loan, String> bookColumn = new TableColumn<>("Book");
        TableColumn<Loan, Date> loanDateColumn = new TableColumn<>("Loan Date");
        TableColumn<Loan, Date> returnDateColumn = new TableColumn<>("Return Date");
        TableColumn<Loan, String> statusColumn = new TableColumn<>("Status");

        // Configurar las columnas
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("member"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loan_date"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Agregar las columnas a la tabla
        loansTable.getColumns().add(memberColumn);
        loansTable.getColumns().add(bookColumn);
        loansTable.getColumns().add(loanDateColumn);
        loansTable.getColumns().add(returnDateColumn);
        loansTable.getColumns().add(statusColumn);

        // Agregar los datos a la tabla
        ObservableList<Loan> loansObservableList = FXCollections.observableArrayList(loans);
        loansTable.setItems(loansObservableList);
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
