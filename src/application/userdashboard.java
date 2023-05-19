package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.*;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;

public class userdashboard implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    BookDatabase bookDatabase = LibSys.getBookDatabase();
    ReaderDatabase readerDatabase = LibSys.getReaderDatabase();
    LibrarianDatabase librarianDatabase = LibSys.getLibrarianDatabase();
    Login login = new Login(readerDatabase, librarianDatabase);
    

    @FXML
    private TextField booksearchfield,booksearchfield2,usersearchfield;
    @FXML
    private TextArea jeff;
    @FXML
    ListView<Book> booksearchlist,booksorderlist,rentabooklist,userrentedbooks;
    @FXML
    ListView<Reader> usersearchlist,userswaitlist;
    @FXML
    Label loggeduser,orderlabel;
    @FXML
    private TreeView usertree;
    @FXML
    TabPane usertabpane;
    
    private String username;
    private String password;

    public void setuserandpass(String name, String pass){
        username = name;
        password = pass;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        usersearchlist.getItems().addAll(readerDatabase.getItems());
        booksearchlist.getItems().addAll(bookDatabase.getItems());
        booksorderlist.getItems().addAll(bookDatabase.getItems());
        rentabooklist.getItems().addAll(bookDatabase.getItems());
        

        booksorderlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {

                Book currentbook = booksorderlist.getSelectionModel().selectedItemProperty().getValue();
                if(currentbook != null){
                userswaitlist.getItems().addAll(currentbook.getWaitlist());
            }
            }
            
        });

        booksorderlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {
                if(booksorderlist.getSelectionModel().selectedItemProperty().getValue() != null){
                    updatelist(userswaitlist,booksorderlist.getSelectionModel().selectedItemProperty().getValue().getWaitlist());
                }
                
            }
            
        });
        booksearchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {
                if(booksearchlist.getSelectionModel().selectedItemProperty().getValue() != null){
                    jeff.setText(booksearchlist.getSelectionModel().getSelectedItem().toStringDebug());
                }
                
            }
            
        });


        usersearchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reader>() {

            @Override
            public void changed(ObservableValue<? extends Reader> arg0, Reader arg1, Reader arg2) {
                    if(usersearchlist.getSelectionModel().selectedItemProperty().getValue() != null){
                    jeff.setText(usersearchlist.getSelectionModel().getSelectedItem().toStringDebug());
                    }
            }
            
        });
        

        TreeItem<String> treeroot = new TreeItem<>("Dashboard");

        TreeItem<String> searchtree = new TreeItem<> ("Search");
        TreeItem<String> searchbu = new TreeItem<> ("Search for a book or user");

        searchtree.getChildren().add(searchbu);

        TreeItem<String> books = new TreeItem<> ("Books");
        TreeItem<String> rentabook = new TreeItem<> ("Rent a book");
        TreeItem<String> orderbook = new TreeItem<> ("Add yourself to the order list");

        books.getChildren().addAll(rentabook, orderbook);

        treeroot.getChildren().addAll(searchtree, books);


        usertree.setShowRoot(false);
        usertree.setRoot(treeroot);


        
    }

    
    public void displayname(String name){
        loggeduser.setText(name);
        orderlabel.setText("Welcome " + name + " which book would you like to order");
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

        
    public void searchbook(TextField field, ListView listView){
        String keyword = field.getText();
        List<Book> foundBooks = new ArrayList<>();
        foundBooks = bookDatabase.searchBooks(keyword);
        updatelist(listView, foundBooks);
    }
    public void searchuser(TextField field, ListView listView){
        String keyword = field.getText();
        List<Reader> foundBooks = new ArrayList<>();
        foundBooks = User.SearchReaderByKeyword(keyword, readerDatabase);
        updatelist(listView, foundBooks);
    }

    public void searchbook1(){
        searchbook(booksearchfield, booksearchlist);
    }
    public void searchbook2(){
        searchbook(booksearchfield2, booksorderlist);
    }

    public void searchuser1(){
        searchuser(usersearchfield, usersearchlist);
    }
    
    public void resetsearch(){
        updateall();
    }

    public void orderbook(){
        if(booksorderlist.getSelectionModel().getSelectedItem() != null){
            if(booksorderlist.getSelectionModel().getSelectedItem().getHeldBy() == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Book is not rented");
                alert.setContentText("This book is available, you can rent if you want");
                alert.showAndWait();
                updatelist(userswaitlist,booksorderlist.getSelectionModel().selectedItemProperty().getValue().getWaitlist());
            }
            else{
                if(booksorderlist.getSelectionModel().getSelectedItem().getHeldBy() == LibSys.loginScreen(username, password).getName()){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You already rented this book");
                    alert.setContentText("This book is already ordered by you");
                    alert.showAndWait();
                    updatelist(userswaitlist,booksorderlist.getSelectionModel().selectedItemProperty().getValue().getWaitlist());
                }
                else{
                    booksorderlist.getSelectionModel().getSelectedItem().addToWaitlist(login.loginReader(username,password));
                    updatelist(userswaitlist,booksorderlist.getSelectionModel().selectedItemProperty().getValue().getWaitlist());
                }
            }
        }
    }
    
    public void rentabook(){
        Book book = rentabooklist.getSelectionModel().getSelectedItem();
        Reader reader = login.loginReader(username, password);
        if(book == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No book or user selected");
            alert.setContentText("Please select a book and a user to rent the book to");
            alert.showAndWait();
            updateall();
            }
        else {
            try{
                if(!login.loginReader(username, password).borrowBook(book,bookDatabase)){ 
                    
                    throw new DoubleBorrowException("Book already owned, You have been added to the order list");
                }
            }
            catch(DoubleBorrowException doubleborrow){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText("Book Already Borrowed");
                alert2.setContentText("Book already rented");
                alert2.showAndWait();
                updateall();
                }
                
            }
    
            updateall();
    }

    public void returnabook(){
        if(userrentedbooks.getSelectionModel().getSelectedItem() != null){
            login.loginReader(username, password).returnBook(userrentedbooks.getSelectionModel().getSelectedItem(), bookDatabase);
            updateall();
        }
        else{
            Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setHeaderText("Select a book");
            alert2.setContentText("Please select a book to return");
            alert2.showAndWait();
            updateall();
        }
        
    }

    public void updatelist(ListView list, List db) {
        list.getItems().clear();
        list.getItems().addAll(db);
    }

    public void updateall(){
        updatelist(booksearchlist, bookDatabase.getItems());
        updatelist(usersearchlist, readerDatabase.getItems());
        updatelist(booksorderlist, bookDatabase.getItems());
        updatelist(rentabooklist, bookDatabase.getItems());
        updatelist(userrentedbooks, login.loginReader(username, password).getReaderBorrowedBooks());
    }
    
    public void selectitem(){
        TreeItem<String> item = (TreeItem<String>) usertree.getSelectionModel().getSelectedItem();

        if(item != null){
            switch(item.getValue()){
                case "Search for a book or user":
                    usertabpane.getSelectionModel().select(0);
                    System.out.println("add book");
                    updateall();
                    break;
                case "Rent a book":
                    usertabpane.getSelectionModel().select(1);
                    System.out.println("Rent a book");
                    updateall();
                    break;
                case "Add yourself to the order list":
                    usertabpane.getSelectionModel().select(2);
                    System.out.println("order list");
                    updateall();
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
}

}
