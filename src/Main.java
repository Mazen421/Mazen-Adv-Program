import java.util.Scanner;

import application.landingpagecontroller;
import database.LibrarianDatabase;
import database.ReaderDatabase;
import database.User;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

import database.LibSys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.io.IOException;


public class    Main extends Application{

    //TODO MAIN IS FOR TESTS ONLY, THE MENU LOOP WILL BE ADDED LATER USING EVENTS FROM THE GUI.
    public static void main(String[] args) {
        LibSys.startRand();

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
            Image icon = new Image("/application/LogoBlueBG_cleanup.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/landingpage.fxml"));
            Parent root = loader.load();
            Scene scene1 = new Scene(root);
            String css = this.getClass().getResource("/application/landingpage.css").toExternalForm();
            scene1.getStylesheets().add(css);
            stage.getIcons().add(icon);
            stage.setTitle("Demo");
            stage.setScene(scene1);

            stage.show();

            /*stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);});
                */
        } 
        catch (IOException e) {
            System.out.println("IOException: ");
        }
    }
}