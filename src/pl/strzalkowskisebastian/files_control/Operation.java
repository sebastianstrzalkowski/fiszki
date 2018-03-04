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

        int index = FishCardList.size()+1;
        do {
            String word = new String("");
            String commentary = new String("");
            String can = new String("");
            Boolean meaning = false;
            Boolean canCheck = false;
            text = in.nextLine();
            char[] array = text.toCharArray();
            for (char anArray : array) {

                if (anArray == ';') {
                    meaning = true;
                }else if (anArray == ':') {
                    canCheck = true;
                    meaning = false;
                }
                else {
                    if (meaning) {
                        commentary += anArray;
                    } else if(canCheck) {
                        can += anArray;
                    }else {
                        word += anArray;
                    }
                }
            }

            canCheck = Boolean.parseBoolean(can);
            FishCard fishCard = new FishCard(word,commentary,index,canCheck );
            FishCardList.addToList(fishCard);
            index++;


        } while (in.hasNext());
        in.close();

    }


    public static void write() throws FileNotFoundException {
        PrintWriter file = new PrintWriter("fiszki.txt");

        for(int i = 0; i < FishCardList.size(); i++){
            FishCard fishCard = FishCardList.getElement(i);
            file.println(fishCard.getWord() + ";" + fishCard.getCommentary() + ":" + fishCard.getCan());
        }


        file.close();
    }

}
