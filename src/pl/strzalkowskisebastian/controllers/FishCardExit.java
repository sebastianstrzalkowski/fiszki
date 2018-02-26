package pl.strzalkowskisebastian.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FishCardExit extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageExit) throws IOException {
        AnchorPane myPaneExit = (AnchorPane) FXMLLoader.load(getClass().getResource
                ("fxml/FishCardExit.fxml"));
        Scene mySceneExit = new Scene(myPaneExit);
        stageExit.setTitle("Fiszki");
        stageExit.setScene(mySceneExit);
        stageExit.show();
    }

    public void yes(ActionEvent actionEvent) {
        try {
            pl.strzalkowskisebastian.files_control.Operation.write();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void no(ActionEvent actionEvent) {
        System.exit(0);
    }
}
