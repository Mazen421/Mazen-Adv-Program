package application;


import java.io.IOException;

import database.LibSys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;


public class landingpagecontroller {

    @FXML
    private Button loginbut;
    @FXML
    private TextField userfield;
    @FXML
    private Label passlabel,userlabel;
    @FXML
    private PasswordField passwordfield;



    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public PasswordField getPasswordField() {
        return passwordfield;
    }
    

    public void login(ActionEvent event) throws IOException{

        userlabel.setTextFill(Color.BLACK); 
        passlabel.setTextFill(Color.BLACK);


        String username = userfield.getText();
        String password = passwordfield.getText();
        System.out.println(username + " " + password);

        

        if (LibSys.loginScreen(username, password) != null) {
            // Login successful
            System.out.println("Login successful");
            if(LibSys.loginScreen(username, password) instanceof database.Reader){
                root = FXMLLoader.load(getClass().getResource("userdashboard.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                root = FXMLLoader.load(getClass().getResource("libdashboard.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } 
        else {
            // Login failed
            userlabel.setTextFill(Color.RED); 
            passlabel.setTextFill(Color.RED);
            System.out.println("Login failed");
        }

    }


}
