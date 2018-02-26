package pl.strzalkowskisebastian;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.strzalkowskisebastian.controllers.FishCardExit;
import pl.strzalkowskisebastian.datebase.Operation;
import pl.strzalkowskisebastian.models.FishCard;

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

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                FishCardExit fishCardExit = new FishCardExit();
                Stage stageExit = new Stage();
                try {
                    fishCardExit.start(stageExit);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args){
        launch(args);
    }

}
