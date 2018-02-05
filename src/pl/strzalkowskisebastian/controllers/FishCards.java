package pl.strzalkowskisebastian.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.strzalkowskisebastian.datebase.Operation;

import java.sql.SQLException;

public class FishCards {
    public Button AddButton;
    public TextField WordAddField;
    public TextField CommentaryAddField;


    public void AddButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Operation.write(WordAddField.getText(),CommentaryAddField.getText());
            Operation.read();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
