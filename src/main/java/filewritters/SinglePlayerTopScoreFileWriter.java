package filewritters;

import java.io.*;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;

public class SinglePlayerTopScoreFileWriter {


    private String filename;
    private int topScore;

    public SinglePlayerTopScoreFileWriter(String filename, int topScore){
        this.filename = filename;
        this.topScore = topScore;
        try {
            fileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriter(String filename) throws IOException {
        Writer file = new FileWriter(filename);

        file.write(Integer.toString(topScore));
        file.flush();
    }



}
