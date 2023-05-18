package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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

    BookDatabase bookDatabase = LibSys.getBookDatabase();
    ReaderDatabase readerDatabase = LibSys.getReaderDatabase();

    //TODO CHECK IF THE BOOK IS RENTED OR NOT FIRST BEFORE REMOVING IT
    @FXML
    private TreeView dashtree;
    @FXML
    Button logbut;
    @FXML
    Label loggedlib,nousers,noAbooks,noBbooks;
    @FXML
    TabPane tabPane;
    @FXML
    private TextField titlefield,authorfield,genrefield,ISBNfield,pubYfield,namefield,emailfield,addressfield,phonefield,IDfield;
    @FXML
    private PasswordField passfield;
    @FXML
    private ListView<Book> listremovebook,listrentabook,listrentedbooks;
    @FXML
    private ListView<Reader> listusers,listremoveuser,blockuserlist;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        listusers.getItems().addAll(readerDatabase.getItems());
        listremoveuser.getItems().addAll(readerDatabase.getItems());

        listremovebook.getItems().addAll(bookDatabase.getItems());
        listrentabook.getItems().addAll(bookDatabase.getItems());

        


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

    public void addabook(){
        
        String title = titlefield.getText();
        String author = authorfield.getText();
        String genre = genrefield.getText();
        String ISBN = ISBNfield.getText();
        int pubY = Integer.parseInt(pubYfield.getText());
        
        Book book = new Book(title,author,genre,ISBN,pubY);
        bookDatabase.addItem(book);
        updateall();

    }

    public void addauser(){
        String name = namefield.getText();
        String email = emailfield.getText();
        String password = passfield.getText();
        String address = addressfield.getText();
        String phone = phonefield.getText();
        String ID = IDfield.getText();

        if(phone.length() <=12 || phone.length() >= 14){
            Reader reader = new Reader(name,email,password,address,phone,ID);
            readerDatabase.addItem(reader);
            System.out.println(reader.toString());
            updateall();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Please enter a valid phone number (+01XXXXXXXX))");
            alert.showAndWait();
        }
    }

    public void removebookbutton(){
        Book book = listremovebook.getSelectionModel().getSelectedItem();

        System.out.println(bookDatabase.toString());
        

        bookDatabase.removeItem(book);
        updateall();
        
    }

    public void removeuserbutton(){
        Reader reader = listremoveuser.getSelectionModel().getSelectedItem();

        System.out.println(reader.toString());

        readerDatabase.removeItem(reader);
        updateall();
    }

    public void banuser(){
        Reader reader = blockuserlist.getSelectionModel().getSelectedItem();
        reader.setBanned(reader);
        updateall();
    }

    
    public void rentabook(){
        Book book = listrentabook.getSelectionModel().getSelectedItem();
        Reader reader = listusers.getSelectionModel().getSelectedItem();
        if(book == null || reader == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No book or user selected");
            alert.setContentText("Please select a book and a user to rent the book to");
            alert.showAndWait();
            }
        else {
            try{
                bookDatabase.borrowBook(book, reader);
                updateall();
            }
            catch(DoubleBorrowException doubleborrow){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText("Book Already Borrowed");
                alert2.setContentText("Book already rented, you have been added to the waitlist");
                alert2.showAndWait();
                }
                

            }
        }
    
    public void returnabook(){

        Book book = listrentedbooks.getSelectionModel().getSelectedItem();
        bookDatabase.returnBook(book);
        updateall();

    }
    
    public void updateall(){
        updatelist(listrentabook, bookDatabase.getItems());
        updatelist(listrentedbooks, bookDatabase.getBorrowedBooks());
        updatelist(listremovebook, bookDatabase.getItems());
        updatelist(listusers, readerDatabase.getItems());
        updatelist(listremoveuser, readerDatabase.getItems());
    }

    public void updatelist(ListView list, List db) {
        list.getItems().clear();
        list.getItems().addAll(db);
    }



    public void selectitem(){
        TreeItem<String> item = (TreeItem<String>) dashtree.getSelectionModel().getSelectedItem();
        
        if(item != null){
            switch(item.getValue()){
            case "Add a book to the library":
                tabPane.getSelectionModel().select(0);
                System.out.println("add book");
                updateall();
                break;
            case "Remove a book from the library":
                updateall();
                tabPane.getSelectionModel().select(1);
                System.out.println("remove book");
                break;
            case "Rent a book from the library":
                updateall();
                tabPane.getSelectionModel().select(2);
                System.out.println("rent book");
                break;
            case "Add a user to the library":
                tabPane.getSelectionModel().select(3);
                System.out.println("add user");
                break;
            case "Remove a user from the library":
                tabPane.getSelectionModel().select(4);
                System.out.println("remove user");
                break;
            case "Block a user from the library":
                tabPane.getSelectionModel().select(5);
                System.out.println("block user");
                break;
            case "Search for a book":
                tabPane.getSelectionModel().select(6);
                System.out.println("search book");
                break;
            case "Search for a user":
                tabPane.getSelectionModel().select(7);
                System.out.println("search user");
                break;
            case "Add a user to the order list":
                tabPane.getSelectionModel().select(8);
                System.out.println("add user to order list");
                break;
            case "Remove a user from the order list":
                tabPane.getSelectionModel().select(9);
                System.out.println("remove user from order list");
                break;
            default:
                System.out.println("default");
                break;

            }
        }
}
    
}
