package pl.strzalkowskisebastian.lists;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.strzalkowskisebastian.models.FishCard;

public class FishCardList {

    public static ObservableList<FishCard> fishCardsList =
            FXCollections.observableArrayList();

    public FishCardList(){};

    public static ObservableList<FishCard> getFishCardsList() {
        return fishCardsList;
    }

    public static void addToList(FishCard fishCard) {
        fishCardsList.add(fishCard);
    }

    public static void showList(ObservableList<FishCard> fishCardsList) {
        for (FishCard x : fishCardsList) {
            System.out.println(x.toString());
        }
    }
    public static int size(){
        return fishCardsList.size();
    }

    public static FishCard getElement(int index){
        return fishCardsList.get(index);
    }

}
