package filereaders;

import com.googlecode.lanterna.TextCharacter;
import data.Wall;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SinglePlayerTopScoreFileReader {

    private String filename;
    private int topScore=0;

    public SinglePlayerTopScoreFileReader(String filename) throws IOException {
        this.filename =filename;
        fileReader(filename);
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
        this.topScore = Integer.parseInt(r);


        int number = this.topScore;
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

    public int getTopScore(){
        return topScore;
    }

}
