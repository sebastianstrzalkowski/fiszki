package pl.strzalkowskisebastian.files_control;

import javafx.collections.ObservableList;
import pl.strzalkowskisebastian.lists.FishCardList;
import pl.strzalkowskisebastian.models.FishCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Operation {

    public static void read() throws FileNotFoundException {
        File file = new File("fiszki.txt");
        Scanner in = new Scanner(file);
        String text;
        String word = new String("");
        String commentary = new String("");
        int index = FishCardList.size()+1;
        do {
            Boolean meaning = false;
            text = in.nextLine();
            char[] array = text.toCharArray();
            for (char anArray : array) {

                if (anArray == ';') meaning = true;
                else {
                    if (meaning) {
                        commentary += anArray;
                    } else {
                        word += anArray;
                    }
                }
            }

            FishCard fishCard = new FishCard(word,commentary,index);
            FishCardList.addToList(fishCard);
            index++;


        } while (in.hasNext());
        in.close();

    }


    public static void write() throws FileNotFoundException {
        PrintWriter file = new PrintWriter("fiszki.txt");

        for(int i = 0; i < FishCardList.size(); i++){
            FishCard fishCard = FishCardList.getElement(i);
            file.println(fishCard.getWord() + ";" + fishCard.getCommentary());
        }


        file.close();
    }

}
