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
        LibSys.start(); // Populate the databases with random users, librarians, and books

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        try {
            Image icon = new Image("/application/LogoBlueBG_cleanup.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/libdashboard.fxml"));
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
            // Handle the exception appropriately, e.g., print an error message or show a dialog
            System.out.println("IOException: ");
        }
    }
}





/*
        LibSys.startRand(); // Populate the databases with random users, librarians, and books
        User loggedInUser;
         // Declare the book variable outside the if statement
        LibrarianDatabase Libbase = LibSys.getLibrarianDatabase();
        ReaderDatabase Readbase = LibSys.getReaderDatabase();


        while (true) {

            Librarian Admin = Libbase.getItems().get(0);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter anything about a User: ");
            String key = scanner.nextLine();

            List<Librarian> ListLib = new ArrayList<>();
            List<Reader> ListRead = new ArrayList<>();

            ListLib = Admin.SearchLibrarianByKeyword(key, Libbase);
            ListRead= Admin.SearchReaderByKeyword(key, Readbase);

            if(ListLib != null){

                for (int i = 0; i < ListLib.size(); i++) {
                    Librarian librarian = ListLib.get(i);
                    System.out.println((i + 1) + ". " + librarian.toString());
                }


            }

            if (ListRead != null){

                for (int i = 0; i < ListRead.size(); i++) {
                    Reader reader = ListRead.get(i);
                    System.out.println((i + 1) + ". " + reader.toString());
                }

            }
        }
    }
*/