package data;

import com.googlecode.lanterna.TextCharacter;

import java.util.List;

public interface Score {
    int getScore();
    void setScore(int s);
    public List<TextCharacter> getText();
    public List<TextCharacter> getPrintableScore();
    public void setPrintableScore(List<TextCharacter> p);
}
