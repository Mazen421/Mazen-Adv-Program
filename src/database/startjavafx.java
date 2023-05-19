package database;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class startjavafx extends Application{

    public void startlunch(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        try {
            Image icon = new Image("./application/LogoBlueBG_cleanup.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/landingpage.fxml"));
            Parent root = loader.load();
            Scene scene1 = new Scene(root);
            String css = this.getClass().getResource("/application/landingpage.css").toExternalForm();
            scene1.getStylesheets().add(css);
            stage.getIcons().add(icon);
            stage.setTitle("LibSys");
            stage.setScene(scene1);

            stage.show();
        } 
        catch (IOException e) {
            System.out.println("IOException: ");
        }
        
    }
    
}
