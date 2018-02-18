package pl.strzalkowskisebastian.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pl.strzalkowskisebastian.datebase.Operation;
import pl.strzalkowskisebastian.lists.FishCardList;
import pl.strzalkowskisebastian.models.FishCard;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class FishCards implements Initializable {
    public Button AddButton;
    public TextField WordAddField;
    public TextField CommentaryAddField;
    public TextField learnWord;
    public Text textWord;
    public TableView<FishCard> tableFishCards;
    public TableColumn<ObservableList, FishCard> columnCommentary;
    public TableColumn<ObservableList, FishCard> columnWord;
    public TableColumn<ObservableList, FishCard> columnID;
    public Text textCommentary;
    Random generator = new Random();
    public FishCard fishCardLearn = new FishCard();



    public void AddButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Operation.write(WordAddField.getText(),CommentaryAddField.getText());
            Operation.read();
            FishCardList.showList(FishCardList.getFishCardsList());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void ButtonCheck(ActionEvent actionEvent) {

    }

    public void updateButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    }

    public void deleteButton(ActionEvent actionEvent) {
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            Operation.read();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            columnWord.setCellValueFactory(new PropertyValueFactory("word"));
            columnCommentary.setCellValueFactory(new PropertyValueFactory("commentary"));
            columnID.setCellValueFactory(new PropertyValueFactory("index"));
            tableFishCards.setItems(FishCardList.getFishCardsList());


    }

    public void canButton(ActionEvent actionEvent) {
    }

    public void nextButton(ActionEvent actionEvent) {
        int index = generator.nextInt(FishCardList.size());
        fishCardLearn = FishCardList.getElement(index);
        textCommentary.setText(fishCardLearn.getCommentary());
    }
}
