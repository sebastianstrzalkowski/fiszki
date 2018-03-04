package pl.strzalkowskisebastian.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pl.strzalkowskisebastian.lists.FishCardList;
import pl.strzalkowskisebastian.models.FishCard;


import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FishCards implements Initializable {
    public Button addButton;
    public TextField wordAddField;
    public TextField commentaryAddField;
    public TextField learnWord;
    public Text textWord;
    public TableView<FishCard> tableFishCards;
    public TableColumn<FishCard, String> columnCommentary;
    public TableColumn<FishCard, String> columnWord;
    public TableColumn<FishCard, Boolean> columnCan;
    public Text textCommentary;
    public Button buttonCheck;
    public TextArea commentaryArea;
    public TextField wordField;
    public Tab learnTab;
    public Tab checkTab;
    public TextField commentaryUpdate;
    public TextField wordUpdate;
    public CheckBox wordsCheckBoxCheckYourself;
    public CheckBox wordsCheckBoxLearn;
    private Random generator = new Random();
    public FishCard fishCardLearn = new FishCard();
    private Boolean checkStatus = false;
    public ComboBox canBox;

    public void addButton(ActionEvent actionEvent) {
        String word = wordAddField.getText();
        String commentary = commentaryAddField.getText();
        int index = FishCardList.size() + 1;
        FishCard fishCard = new FishCard(word, commentary, index);
        FishCardList.addToList(fishCard);

    }

    public void buttonCheck(ActionEvent actionEvent) {
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
        Boolean checkAllWords = true;
        if(wordsCheckBoxCheckYourself.isSelected() == false && checkTab.isSelected() == true){
            checkAllWords = false;
        }
        if(wordsCheckBoxLearn.isSelected() == false && learnTab.isSelected() == true){
            checkAllWords = false;
        }


        if (numberOfFalse > 0 && checkAllWords == false) {
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
        } else if(numberOfFalse == 0 && checkAllWords == false) {
            textCommentary.setText("Gratuluje, umiesz już wszystkie słowa");
            commentaryArea.setText("Gratuluje, umiesz już wszystkie słowa");
        }else{
            int index = generator.nextInt(FishCardList.size());
            fishCardLearn = FishCardList.getElement(index);
            if (learnTab.isSelected()) {
                commentaryArea.setText(fishCardLearn.getCommentary());
                wordField.setText(fishCardLearn.getWord());
            } else if (checkTab.isSelected()) {
                textCommentary.setText(fishCardLearn.getCommentary());
            }
        }
    }


}
