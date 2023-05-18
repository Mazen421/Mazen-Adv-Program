package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class libdashboard implements Initializable  {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TreeView dashtree;
    @FXML
    Button logbut;
    @FXML
    Label loggedlib,nousers,noAbooks,noBbooks;
    @FXML
    AnchorPane landingpane;


    private void enablepane(Boolean state, AnchorPane pane) {
        landingpane.setVisible(state);
    }


    public void logout(ActionEvent le) throws IOException{

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");

        alert.setHeaderText("You are about to log out");
        alert.setContentText("Are you sure you want to log out?");

        if(alert.showAndWait().get() ==  ButtonType.OK){
            root = FXMLLoader.load(getClass().getResource("landingpage.fxml"));
            stage = (Stage) ((Node)le.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("landingpage.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }
        else{
            alert.close();
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        TreeItem<String> root = new TreeItem<>("Dashboard");

        TreeItem<String> books = new TreeItem<>("Books");
        TreeItem<String> addbook = new TreeItem<>("Add a book to the library");
        TreeItem<String> removebook = new TreeItem<>("Remove a book from the library");
        TreeItem<String> rentbook = new TreeItem<>("Rent a book from the library");
        books.getChildren().addAll(addbook,removebook,rentbook);


        TreeItem<String> users = new TreeItem<>("Users");
        TreeItem<String> adduser = new TreeItem<>("Add a user to the library");
        TreeItem<String> removeuser = new TreeItem<>("Remove a user from the library");
        TreeItem<String> blockuser = new TreeItem<>("Block a user from the library");
        users.getChildren().addAll(adduser,removeuser,blockuser);


        TreeItem<String> search = new TreeItem<>("Search");
        TreeItem<String> searchbook = new TreeItem<>("Search for a book");
        TreeItem<String> searchuser = new TreeItem<>("Search for a user");
        search.getChildren().addAll(searchbook,searchuser);


        TreeItem<String> orderlist = new TreeItem<>("Order List");
        TreeItem<String> adduserorder = new TreeItem<>("Add a user to the order list");
        TreeItem<String> removeuserorder = new TreeItem<>("Remove a user from the order list");
        orderlist.getChildren().addAll(adduserorder,removeuserorder);

        root.getChildren().addAll(books,users,search,orderlist);
        dashtree.setShowRoot(false);
        dashtree.setRoot(root);
        
    }

    public void selectitem(){


        TreeItem<String> item = (TreeItem<String>) dashtree.getSelectionModel().getSelectedItem();

        
        if(item != null){
            ArrayList list = new ArrayList<AnchorPane>();
            list.add(landingpane);
            
            switch(item.getValue()){
            case "Add a book to the library":
                landingpane.disableProperty().set(true);
                System.out.println("add book");
                break;
            case "Remove a book from the library":
                System.out.println("remove book");
                break;
            case "Rent a book from the library":
                System.out.println("rent book");
                break;
            case "Add a user to the library":
                System.out.println("add user");
                break;
            case "Remove a user from the library":
                System.out.println("remove user");
                break;
            case "Block a user from the library":
                System.out.println("block user");
                break;
            case "Search for a book":
                System.out.println("search book");
                break;
            case "Search for a user":
                System.out.println("search user");
                break;
            case "Add a user to the order list":
                System.out.println("add user to order list");
                break;
            case "Remove a user from the order list":
                System.out.println("remove user from order list");
                break;
            default:
                System.out.println("default");
                break;

            }
        }
}
    
}
