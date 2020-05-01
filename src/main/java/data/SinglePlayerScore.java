package data;

import com.googlecode.lanterna.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerScore {

    private int score;
    private List<TextCharacter> text=new ArrayList<>();
    private List<TextCharacter> printable=new ArrayList<>();

    public SinglePlayerScore(){
        this.score=0;
        text.add(new TextCharacter('S'));
        text.add(new TextCharacter('C'));
        text.add(new TextCharacter('O'));
        text.add(new TextCharacter('R'));
        text.add(new TextCharacter('E'));
        text.add(new TextCharacter(':'));
        text.add(new TextCharacter(' '));
    }

    public int getScore() {
        return score;
    }

    public List<TextCharacter> getText() {
        return text;
    }

    public void incrementScore(){
        score++;
        updatePrintable();
    }

    public List<TextCharacter> getPrintableScore() {
        return printable;
    }

    public List<TextCharacter> updatePrintable(){

        printable.clear();
        printable.addAll(text);

        int number = score;
        int digit;

        List<TextCharacter> numberConversion=new ArrayList<>();

        while (number > 0) {
            digit = number % 10;

            if(digit == 1) numberConversion.add(new TextCharacter('1'));
            if(digit == 2) numberConversion.add(new TextCharacter('2'));
            if(digit == 3) numberConversion.add(new TextCharacter('3'));
            if(digit == 4) numberConversion.add(new TextCharacter('4'));
            if(digit == 5) numberConversion.add(new TextCharacter('5'));
            if(digit == 6) numberConversion.add(new TextCharacter('6'));
            if(digit == 7) numberConversion.add(new TextCharacter('7'));
            if(digit == 8) numberConversion.add(new TextCharacter('8'));
            if(digit == 9) numberConversion.add(new TextCharacter('9'));
            if(digit == 0) numberConversion.add(new TextCharacter('0'));

            number = number/ 10;
        }

        for(int i=numberConversion.size()-1;i>=0;i--){
            printable.add(numberConversion.get(i));
        }

        return printable;
    }
}
