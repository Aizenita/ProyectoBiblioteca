package org.example.libraryapp.controller;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {

    @FXML
    private ImageView logo;

    @FXML
    private Text appName;

    @FXML
    private Text subtitle;

    @FXML
    private Button closeButton;

    private double xOffset = 0;
    private double yOffset = 0;

    private Stage primaryStage;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/resources/Main.fxml"));

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        // Load logo image (assuming logo.png is in resources folder)
        logo.setImage(new Image(getClass().getResource("/logo.png").toExternalForm()));

        // Close button action
        closeButton.setOnAction(event -> closeApp());

        // Make window draggable
        logo.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        logo.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        // Create animations
    }
    public void setupStage() {
        primaryStage.setMinWidth(800); // Set the width as needed
        primaryStage.setMinHeight(600); // Set the height as needed

        Scene scene = primaryStage.getScene();
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    private void createAnimations() {
        // Animation for logo
        FadeTransition fadeInLogo = new FadeTransition(Duration.seconds(2), logo);
        fadeInLogo.setFromValue(0);
        fadeInLogo.setToValue(1);

        FadeTransition fadeOutLogo = new FadeTransition(Duration.seconds(1), logo);
        fadeOutLogo.setFromValue(1);
        fadeOutLogo.setToValue(0);
        fadeOutLogo.setDelay(Duration.seconds(1));

        // Animation for app name
        String appNameText = "ALEJANDRIA";
        Duration delayBetweenChars = Duration.seconds(0.3);
        Timeline appNameTimeline = new Timeline();
        for (int i = 0; i < appNameText.length(); i++) {
            String currentText = appNameText.substring(0, i + 1);
            KeyFrame keyFrame = new KeyFrame(delayBetweenChars.multiply(i + 1), e -> appName.setText(currentText));
            appNameTimeline.getKeyFrames().add(keyFrame);
        }

        FadeTransition fadeInAppName = new FadeTransition(Duration.seconds(1.5), appName);
        fadeInAppName.setFromValue(0);
        fadeInAppName.setToValue(1);
        fadeInAppName.setDelay(delayBetweenChars.multiply(appNameText.length()));

        // Animation for subtitle
        FadeTransition fadeInSubtitle = new FadeTransition(Duration.seconds(1.5), subtitle);
        fadeInSubtitle.setFromValue(0);
        fadeInSubtitle.setToValue(1);
        fadeInSubtitle.setDelay(Duration.seconds(2));

        // Sequence of animations
        SequentialTransition sequentialTransition = new SequentialTransition(
                fadeInLogo, fadeOutLogo, new PauseTransition(Duration.seconds(0.1)),
                new ParallelTransition(appNameTimeline, fadeInAppName), fadeInSubtitle);

        // Play animations and handle media
        sequentialTransition.play();
        sequentialTransition.setOnFinished(event -> {
            Media sound = new Media(getClass().getResource("/audio.mpeg").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();

            // Close splash screen after delay (same logic as original class)
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(e -> closeApp());
            pause.play();
        });

        primaryStage.setOnHidden(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml")); // Assuming home.fxml is your new FXML file
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void postInitialize() {
        createAnimations();
    }
    private void closeApp() {
        primaryStage.close();
    }
}
