package pl.strzalkowskisebastian.models;

public class FishCard {
    String word;
    String commentary;
    int index;
    Boolean can;

    public FishCard(){};

    public Boolean getCan() {
        return can;
    }

    public void setCan(Boolean can) {

        this.can = can;
    }

    public FishCard(String word, String commentary, int index){
        this.word = word;
        this.commentary = commentary;
        this.index = index;
        this.can = false;

    }
    public FishCard(String word, String commentary, int index, Boolean can){
        this.word = word;
        this.commentary = commentary;
        this.index = index;
        this.can = can;

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



    @Override
    public String toString() {
        return "FishCard{" +
                "word='" + word + '\'' +
                ", commentary='" + commentary + '\'' +
                ", index=" + index +
                ", can=" + can +
                '}';
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
