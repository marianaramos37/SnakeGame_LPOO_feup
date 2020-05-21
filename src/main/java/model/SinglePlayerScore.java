package model;

import com.googlecode.lanterna.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerScore extends Score {

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

    public SinglePlayerScore(int x){
        this.score=x;
        text.add(new TextCharacter('S'));
        text.add(new TextCharacter('C'));
        text.add(new TextCharacter('O'));
        text.add(new TextCharacter('R'));
        text.add(new TextCharacter('E'));
        text.add(new TextCharacter(':'));
        text.add(new TextCharacter(' '));
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int s) {
        this.score=s;
    }

    @Override
    public List<TextCharacter> getText() {
        return text;
    }

    @Override
    public List<TextCharacter> getPrintableScore() {
        return printable;
    }

    @Override
    public void setPrintableScore(List<TextCharacter> p) {
        this.printable=p;
    }



 }
