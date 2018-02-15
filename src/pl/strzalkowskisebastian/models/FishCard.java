package pl.strzalkowskisebastian.models;

public class FishCard {
    String word;
    String commentary;
    int index;

    public FishCard(String word, String commentary, int index){
        this.word = word;
        this.commentary = commentary;
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public String getCommentary() {
        return commentary;
    }

    public int getIndex() {
        return index;
    }
}
