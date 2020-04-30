package data;

import com.googlecode.lanterna.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerTopScore {
    private int topScore;
    private List<TextCharacter> text=new ArrayList<>();
    private List<TextCharacter> printable=new ArrayList<>();

    public SinglePlayerTopScore(){

        //ler top score do ficheiro

        text.add(new TextCharacter('T'));
        text.add(new TextCharacter('O'));
        text.add(new TextCharacter('P'));
        text.add(new TextCharacter(' '));
        text.add(new TextCharacter('S'));
        text.add(new TextCharacter('C'));
        text.add(new TextCharacter('O'));
        text.add(new TextCharacter('R'));
        text.add(new TextCharacter('E'));
        text.add(new TextCharacter(':'));
        text.add(new TextCharacter(' '));
    }

    public int gettopScore() {
        return topScore;
    }

    public List<TextCharacter> getText() {
        return text;
    }

    public void incrementTopScore(){
        topScore++;
    }

    public List<TextCharacter> getPrintableScore(){

        printable.clear();
        printable.addAll(text);

        int number = topScore;
        int digit;

        while (number > 0) {
            digit = number % 10;

            if(digit == 1) printable.add(new TextCharacter('1'));
            if(digit == 2) printable.add(new TextCharacter('2'));
            if(digit == 3) printable.add(new TextCharacter('3'));
            if(digit == 4) printable.add(new TextCharacter('4'));
            if(digit == 5) printable.add(new TextCharacter('5'));
            if(digit == 6) printable.add(new TextCharacter('6'));
            if(digit == 7) printable.add(new TextCharacter('7'));
            if(digit == 8) printable.add(new TextCharacter('8'));
            if(digit == 9) printable.add(new TextCharacter('9'));
            if(digit == 0) printable.add(new TextCharacter('0'));

            number = number/ 10;
        }

        return printable;
    }
}
