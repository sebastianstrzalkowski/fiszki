package pl.strzalkowskisebastian.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pl.strzalkowskisebastian.datebase.Operation;
import pl.strzalkowskisebastian.lists.FishCardList;
import pl.strzalkowskisebastian.models.FishCard;


import java.io.FileNotFoundException;
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
    public Button buttonCheck;
    public TextArea commentaryArea;
    public TextField wordField;
    public Tab learnTab;
    public Tab checkTab;
    private Random generator = new Random();
    public FishCard fishCardLearn = new FishCard();
    private Boolean checkStatus;



    public void AddButton(ActionEvent actionEvent) {
        String word = WordAddField.getText();
        String commentary = CommentaryAddField.getText();
        int index = FishCardList.size()+1;
        FishCard fishCard = new FishCard(word,commentary,index);
        FishCardList.addToList(fishCard);

    }

    public void ButtonCheck(ActionEvent actionEvent) {
        String word = learnWord.getText();
        if(word.equals(fishCardLearn.getWord())){
            buttonCheck.setText("Dobrze");
            checkStatus = true;

        }
        else{
            buttonCheck.setText("Źle, popraw");
            checkStatus = false;
        }
    }

    public void updateButton(ActionEvent actionEvent) {
    }

    public void deleteButton(ActionEvent actionEvent) {

    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            pl.strzalkowskisebastian.files_control.Operation.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        columnWord.setCellValueFactory(new PropertyValueFactory("word"));
            columnCommentary.setCellValueFactory(new PropertyValueFactory("commentary"));
            columnID.setCellValueFactory(new PropertyValueFactory("index"));
            tableFishCards.setItems(FishCardList.getFishCardsList());


    }

    public void canButton(ActionEvent actionEvent) {
        if(checkStatus == true) {
            fishCardLearn.setCan(true);
        }
        else{
            fishCardLearn.setCan(false);
        }
    }

    public void nextButton(ActionEvent actionEvent) {
        int index = generator.nextInt(FishCardList.size());
        fishCardLearn = FishCardList.getElement(index);
        if(learnTab.isSelected()){
            commentaryArea.setText(fishCardLearn.getCommentary());
            wordField.setText(fishCardLearn.getWord());
        }else if(checkTab.isSelected()) {
            textCommentary.setText(fishCardLearn.getCommentary());
        }
        buttonCheck.setText("Sprawdź");
    }
}
