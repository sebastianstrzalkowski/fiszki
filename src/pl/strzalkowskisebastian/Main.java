package pl.strzalkowskisebastian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.strzalkowskisebastian.datebase.Operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import static javafx.application.Application.launch;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        AnchorPane myPane = (AnchorPane) FXMLLoader.load(getClass().getResource
                ("controllers/fxml/FishCards.fxml"));
        Scene myScene = new Scene(myPane);

        stage.setTitle("Fiszki");
        stage.setScene(myScene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
    public void stop(){
        try {
            pl.strzalkowskisebastian.files_control.Operation.write();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
