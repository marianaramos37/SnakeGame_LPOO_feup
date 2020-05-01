package data;

import com.googlecode.lanterna.TextCharacter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SinglePlayerTopScore {
    private int topScore;
    private List<TextCharacter> text=new ArrayList<>();
    private List<TextCharacter> printable=new ArrayList<>();
    private String filename;
    //private SinglePlayerTopScoreFileReader n;

    public SinglePlayerTopScore() throws IOException {

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

        List<TextCharacter> top=this.fileReader(filename);
        StringBuilder aux= new StringBuilder();
        for(TextCharacter ch:top){
            aux.append(ch.getCharacter());
        }
        String aux2= aux.toString();

        this.printable.addAll(top);
        this.topScore=Integer.parseInt(aux2);

        //System.out.println(n.getTopScore());
        //this.topScore = n.getTopScore();
    }

    public int getTopScore() {
        return topScore;
    }

    public List<TextCharacter> getPrintable() {
        return printable;
    }

    public String getFilename() { return filename;}

    public void incrementTopScore(){
        this.topScore++;
        updatePrintable();
    }

    public void updatePrintable(){
        this.printable.clear();
        this.printable.addAll(text);

        int number = this.topScore;
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
            this.printable.add(numberConversion.get(i));
        }

    }

    public List<TextCharacter> fileReader(String filename) throws IOException {
        List<TextCharacter> numbers = new ArrayList<>();
        List<TextCharacter> numberConversion=new ArrayList<>();

        Charset encoding = Charset.defaultCharset();
        File file = new File(filename);
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in, encoding);
        BufferedReader buffer = new BufferedReader(reader);
        String r = buffer.readLine();

        int aux=Integer.parseInt(r);
        //this.topScore = Integer.parseInt(r);

        int number = aux/*this.topScore*/;
        int digit;


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
            numbers.add(numberConversion.get(i));
        }

        return numbers;
    }

    public void fileWriter(String filename) throws IOException {
        Writer file = new FileWriter(filename);

        file.write(Integer.toString(topScore));
        file.flush();
    }
}
