package rules;

import com.googlecode.lanterna.TextCharacter;
import data.Score;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ScoreController {

    public void incrementScore(Score s){
        s.setScore(s.getScore()+1);
        List<TextCharacter> n= updatePrintable(s);
        s.setPrintableScore(n);
    }

    public List<TextCharacter> updatePrintable(Score s){

        List<TextCharacter> n =new ArrayList<>(s.getText());

        int number = s.getScore();
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
            n.add(numberConversion.get(i));
        }

        return n;
    }


    public /*List<TextCharacter>*/void fileReader(String filename,Score s) throws IOException {
        List<TextCharacter> numbers = new ArrayList<>();
        List<TextCharacter> numberConversion=new ArrayList<>();

        Charset encoding = Charset.defaultCharset();
        File file = new File(filename);
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in, encoding);
        BufferedReader buffer = new BufferedReader(reader);
        String r = buffer.readLine();

        //int aux=Integer.parseInt(r);
        s.setScore(Integer.parseInt(r));

        int number = s.getScore()/*this.topScore*/;
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

        List<TextCharacter> n=updatePrintable(s);
        s.setPrintableScore(n);
        //return numbers;
    }

    public void fileWriter(String filename,Score s) throws IOException {
        Writer file = new FileWriter(filename);
        file.write(Integer.toString(s.getScore()));
        file.flush();
    }

}
