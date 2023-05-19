
import database.startjavafx;
import database.LibSys;

import javafx.application.Application;

public class Main{
    public static void main(String[] args) {
        LibSys.startRand();
        Application.launch(startjavafx.class, args);
    }
}