package data;

import com.googlecode.lanterna.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerTopScore implements Score{

    private int topScore;
    private List<TextCharacter> text=new ArrayList<>();
    private List<TextCharacter> printable=new ArrayList<>();
    private String filename;

    public SinglePlayerTopScore() {

        this.filename = "src/main/java/files/topScore.txt";

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

        this.printable.addAll(text);

    }

    @Override
    public int getScore() {
        return topScore;
    }

    @Override
    public List<TextCharacter> getPrintableScore() {
        return printable;
    }

    public String getFilename() { return filename;}

    @Override
    public void setScore(int s) {
        this.topScore=s;
    }

    @Override
    public List<TextCharacter> getText() {
        return this.text;
    }

    @Override
    public void setPrintableScore(List<TextCharacter> p) {
        this.printable = p;
    }

}
