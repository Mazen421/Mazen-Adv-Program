package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class libdashboard implements Initializable  {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    BookDatabase bookDatabase = LibSys.getBookDatabase();
    ReaderDatabase readerDatabase = LibSys.getReaderDatabase();

    @FXML
    private TreeView dashtree;
    @FXML
    Label loggedlib;
    @FXML
    TabPane tabPane;
    @FXML
    private TextField titlefield,authorfield,genrefield,ISBNfield,pubYfield,namefield,emailfield,addressfield,phonefield,IDfield,booksearch1,booksearch2,booksearch3, usersearch1,usersearch2,usersearch3,usersearch4,usersearch5;
    @FXML
    private PasswordField passfield;
    @FXML
    private ListView<Book> listremovebook,listrentabook,listrentedbooks,allbookslist,allbookslist1,searchbooklist;
    @FXML
    private ListView<Reader> listusers,listremoveuser,avuserlist,banneduserlist,waitingusersadd,waitingusersremove,alluserslist,searchuserlist;
    @FXML
    private TextArea steve;


    

    Book currentbook;
    Book currentbook1;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {



        listusers.getItems().addAll(readerDatabase.getItems());
        listremoveuser.getItems().addAll(readerDatabase.getItems());
        avuserlist.getItems().addAll(readerDatabase.getItems());
        banneduserlist.getItems().addAll(readerDatabase.returnBanned());
        alluserslist.getItems().addAll(readerDatabase.getItems());


        listremovebook.getItems().addAll(bookDatabase.getItems());
        listrentabook.getItems().addAll(bookDatabase.getItems());
        allbookslist.getItems().addAll(bookDatabase.getItems());
        allbookslist1.getItems().addAll(bookDatabase.getItems());


        searchbooklist.getItems().addAll(bookDatabase.getItems());
        searchuserlist.getItems().addAll(readerDatabase.getItems());


        searchbooklist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {
                    if(searchbooklist.getSelectionModel().selectedItemProperty().getValue() != null){
                    steve.setText(searchbooklist.getSelectionModel().getSelectedItem().toStringDebug());
                    }
            }
            
        });

        searchuserlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reader>() {

            @Override
            public void changed(ObservableValue<? extends Reader> arg0, Reader arg1, Reader arg2) {
                    if(searchuserlist.getSelectionModel().selectedItemProperty().getValue() != null){
                    steve.setText(searchuserlist.getSelectionModel().getSelectedItem().toStringDebug());
                    }
            }
            
        });
        

        allbookslist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

            @Override
            public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {

                currentbook = allbookslist.getSelectionModel().getSelectedItem();
                if(currentbook != null){
                    updatelist(waitingusersadd, currentbook.getWaitlist());

                }
            }
            }
        );
        allbookslist1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {

                @Override
                public void changed(ObservableValue<? extends Book> arg0, Book arg1, Book arg2) {

                    currentbook1 = allbookslist1.getSelectionModel().getSelectedItem();
                    if(currentbook1 != null){
                        updatelist(waitingusersremove, currentbook1.getWaitlist());
                    }
                }
            });
            
        

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

        TreeItem<String> orderlist = new TreeItem<>("Order List");
        TreeItem<String> adduserorder = new TreeItem<>("Add/Remove user to the order list");
        orderlist.getChildren().addAll(adduserorder);

        TreeItem<String> search = new TreeItem<>("Search");
        TreeItem<String> searchuser = new TreeItem<>("Search User/Book");
        search.getChildren().addAll(searchuser);

        root.getChildren().addAll(books,users,orderlist,search);
        dashtree.setShowRoot(false);
        dashtree.setRoot(root);
        
    }

    public void displayname(String name){
        loggedlib.setText(name);
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

    public void addusertowaitlist(){
        Reader reader = alluserslist.getSelectionModel().getSelectedItem();
        try{
            allbookslist.getSelectionModel().getSelectedItem().addToWaitlist(reader);
        }
        catch(DoubleBorrowException exception){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Book already held by user");
            alert.setContentText("The user you selected already has the book and can't be added to the waitlist");
            alert.showAndWait();
        }
        
        updateall(allbookslist.getSelectionModel().getSelectedItem());
    }

    public void removeuserfromwaitlist(){
        Reader reader = waitingusersremove.getSelectionModel().getSelectedItem();
        allbookslist1.getSelectionModel().getSelectedItem().removeFromWaitlist(reader);
        updateall(allbookslist1.getSelectionModel().getSelectedItem());
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
        Reader reader = avuserlist.getSelectionModel().getSelectedItem();
        reader.setBanned(true);
        updateall();
    }

    public void unbanuser(){
        Reader reader = banneduserlist.getSelectionModel().getSelectedItem();
        reader.setBanned(false);
        updateall();
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
        searchbook(booksearch1, listrentabook);
    }
    public void searchbook2(){
        searchbook(booksearch2, listremovebook);
    }
    public void searchbook3(){
        searchbook(booksearch3, searchbooklist);
    }
    public void searchuser1(){
        searchuser(usersearch1, listusers);
    }
    public void searchuser2(){
        searchuser(usersearch2, listremoveuser);
    }
    public void searchuser3(){
        searchuser(usersearch3, avuserlist);
    }
    public void searchuser4(){
        searchuser(usersearch4, banneduserlist);
    }
    public void searchuser5(){
        searchuser(usersearch5, searchuserlist);
    }
    public void resetsearch(){
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
                if(!bookDatabase.borrowBook(book, reader)){ 
                    throw new DoubleBorrowException("Book already owned, You have been added to the order list");
                }
            }
            catch(DoubleBorrowException doubleborrow){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText("Book Already Borrowed");
                alert2.setContentText("Book already rented, you have been added to the waitlist");
                alert2.showAndWait();
                }
                updateall();
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
        updatelist(avuserlist, readerDatabase.returnPermitted());
        updatelist(banneduserlist, readerDatabase.returnBanned());
        updatelist(allbookslist, bookDatabase.getItems());
        updatelist(allbookslist1, bookDatabase.getItems());
        updatelist(alluserslist, readerDatabase.getItems());
        if(allbookslist.getSelectionModel().getSelectedItem() != null){
        updatelist(waitingusersadd, allbookslist.getSelectionModel().getSelectedItem().getWaitlist());
        }
        updatelist(searchbooklist, bookDatabase.getItems());
        updatelist(searchuserlist, readerDatabase.getItems());
    }

    public void updateall(Book book){
        updatelist(listrentabook, bookDatabase.getItems());
        updatelist(listrentedbooks, bookDatabase.getBorrowedBooks());
        updatelist(listremovebook, bookDatabase.getItems());
        updatelist(listusers, readerDatabase.getItems());
        updatelist(listremoveuser, readerDatabase.getItems());
        updatelist(avuserlist, readerDatabase.returnPermitted());
        updatelist(banneduserlist, readerDatabase.returnBanned());
        updatelist(allbookslist, bookDatabase.getItems());
        updatelist(allbookslist1, bookDatabase.getItems());
        updatelist(alluserslist, readerDatabase.getItems());
        updatelist(waitingusersadd, book.getWaitlist());
        updatelist(waitingusersremove, book.getWaitlist());
        updatelist(searchbooklist, bookDatabase.getItems());
        updatelist(searchuserlist, readerDatabase.getItems());
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
            case "Add/Remove user to the order list":
                tabPane.getSelectionModel().select(6);
                System.out.println("add user to order list");
                break;
            case "Search User/Book":
                tabPane.getSelectionModel().select(7);
                System.out.println("search user/book");
                break;
            default:
                System.out.println("default");
                break;

            }
        }
}
    
}
