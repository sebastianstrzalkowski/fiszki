package pl.strzalkowskisebastian.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import pl.strzalkowskisebastian.lists.FishCardList;
import pl.strzalkowskisebastian.models.FishCard;


import javax.security.auth.callback.Callback;
import java.io.FileNotFoundException;
import java.net.URL;
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
    public TableColumn<FishCard, Boolean> columnCan;
    public Text textCommentary;
    public Button buttonCheck;
    public TextArea commentaryArea;
    public TextField wordField;
    public Tab learnTab;
    public Tab checkTab;
    public TextField commentaryUpdate;
    public TextField wordUpdate;
    private Random generator = new Random();
    public FishCard fishCardLearn = new FishCard();
    private Boolean checkStatus = false;
    public ComboBox canBox;

    public void AddButton(ActionEvent actionEvent) {
        String word = WordAddField.getText();
        String commentary = CommentaryAddField.getText();
        int index = FishCardList.size() + 1;
        FishCard fishCard = new FishCard(word, commentary, index);
        FishCardList.addToList(fishCard);

    }

    public void ButtonCheck(ActionEvent actionEvent) {
        String word = learnWord.getText();
        if (word.equals(fishCardLearn.getWord())) {
            buttonCheck.setText("Dobrze");
            checkStatus = true;

        } else {
            buttonCheck.setText("Źle, popraw");
            checkStatus = false;
        }
    }

    public void updateButton(ActionEvent actionEvent) {
        if (tableFishCards.getSelectionModel().isEmpty()) {
        } else {
            FishCard fishCard = (FishCard) tableFishCards.getSelectionModel().getSelectedItem();
            if (wordUpdate.getLength() > 0) {
                fishCard.setWord(wordUpdate.getText());
            }
            if (commentaryUpdate.getLength() > 0) {
                fishCard.setCommentary(commentaryUpdate.getText());
            }
            if(!canBox.getSelectionModel().isEmpty()){
                if(canBox.getSelectionModel().getSelectedItem().equals("Tak")){
                    fishCard.setCan(true);
                }else if(canBox.getSelectionModel().getSelectedItem().equals("Nie")){
                    fishCard.setCan(false);
                }
            }
            tableFishCards.refresh();
        }
    }

    public void deleteButton(ActionEvent actionEvent) {
        if (tableFishCards.getSelectionModel().isEmpty()) {
        } else {
            tableFishCards.getItems().removeAll(tableFishCards.getSelectionModel().getSelectedItem());

        }
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            pl.strzalkowskisebastian.files_control.Operation.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        columnWord.setCellValueFactory(new PropertyValueFactory("word"));
        columnCommentary.setCellValueFactory(new PropertyValueFactory("commentary"));
        columnCan.setCellValueFactory(new PropertyValueFactory("can"));

        columnCan.setCellFactory(tc -> new TableCell<FishCard, Boolean>() {

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "Tak" : "Nie" );
            }
        });


        tableFishCards.setItems(FishCardList.getFishCardsList());

        canBox.getItems().addAll(
                "Tak", "Nie"
        );


    }

    public void canButton(ActionEvent actionEvent) {
        System.out.println(fishCardLearn.toString());
        if (checkStatus) {
            fishCardLearn.setCan(true);
            tableFishCards.refresh();
        }


    }

    public void nextButton(ActionEvent actionEvent) {
        int numberOfFalse = FishCardList.getNumbersOfFalse();
        System.out.println(numberOfFalse);
        if (numberOfFalse > 0) {
            int index = generator.nextInt(FishCardList.size());
            fishCardLearn = FishCardList.getElement(index);
            while (fishCardLearn.getCan() == true) {
                index = generator.nextInt(FishCardList.size());
                fishCardLearn = FishCardList.getElement(index);
            }
            if (learnTab.isSelected()) {
                commentaryArea.setText(fishCardLearn.getCommentary());
                wordField.setText(fishCardLearn.getWord());
            } else if (checkTab.isSelected()) {
                textCommentary.setText(fishCardLearn.getCommentary());
            }
            buttonCheck.setText("Sprawdź");
        } else {
            textCommentary.setText("Gratuluje, umiesz już wszystkie słowa");
        }
    }


}
