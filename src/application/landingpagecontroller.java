package application;


import java.io.IOException;

import database.LibSys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;


public class landingpagecontroller {

    @FXML
    private Button loginbut;
    @FXML
    private TextField userfield,passwordfield;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException{

        String username = userfield.getText();
        String password = passwordfield.getText();
        System.out.println(username + " " + password);

        

        if (LibSys.loginScreen(username, password) != null) {
            // Login successful
            System.out.println("Login successful");
                    root = FXMLLoader.load(getClass().getResource("css.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
        } 
        else {
            // Login failed
            System.out.println("Login failed");
        }

    }


}
