package model;

import java.io.*;
import java.nio.charset.Charset;

public class SinglePlayerTopScore{

    private int topScore;
    private String filename;

    public SinglePlayerTopScore() {
        this.filename = "src/main/java/files/topScore.txt";
        this.topScore=0;
    }

    public int getScore() {
        return topScore;
    }

    public String getFilename() { return filename;}

    public void setScore(int s) {
        this.topScore=s;
    }

    public void incrementScore(){
        this.topScore+=1;
    }

    public void fileReader() throws IOException {
        Charset encoding = Charset.defaultCharset();
        File file = new File(filename);
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in, encoding);
        BufferedReader buffer = new BufferedReader(reader);
        String r = buffer.readLine();
        topScore=Integer.parseInt(r);
    }

    public void fileWriter() throws IOException {
        Writer file = new FileWriter(filename);
        file.write(Integer.toString(topScore));
        file.flush();
    }

}
