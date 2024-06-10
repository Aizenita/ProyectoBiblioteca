package org.example.libraryapp;

import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.libraryapp.controller.MainController;

public class Main extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage); // Set primaryStage before initialize method is called
        controller.setupStage(); // Set up the stage after primaryStage is set

        primaryStage.show();

        controller.postInitialize();
    }
}