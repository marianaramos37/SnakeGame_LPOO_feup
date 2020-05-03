package data;

import com.googlecode.lanterna.TextCharacter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SPTopScoreTest {
    @Test
    public void testConstructor() throws IOException {
        SinglePlayerTopScore sptopscore = new SinglePlayerTopScore();

        String filename= "src/main/java/files/topScore.txt";

        List<TextCharacter> textExpected=new ArrayList<>();
        textExpected.add(new TextCharacter('T'));
        textExpected.add(new TextCharacter('O'));
        textExpected.add(new TextCharacter('P'));
        textExpected.add(new TextCharacter(' '));
        textExpected.add(new TextCharacter('S'));
        textExpected.add(new TextCharacter('C'));
        textExpected.add(new TextCharacter('O'));
        textExpected.add(new TextCharacter('R'));
        textExpected.add(new TextCharacter('E'));
        textExpected.add(new TextCharacter(':'));
        textExpected.add(new TextCharacter(' '));

        assertEquals(filename, sptopscore.getFilename());
        assertEquals(textExpected, sptopscore.getText());

    }

    @Test
    public void testSetScore() {
        SinglePlayerTopScore sptopscore = new SinglePlayerTopScore();
        sptopscore.setScore(3);
        assertEquals(3, sptopscore.getScore());
    }

    @Test
    public void testSetPrintableScore() {
        SinglePlayerTopScore sptopscore = new SinglePlayerTopScore();
        List<TextCharacter> textExpected=new ArrayList<>();
        textExpected.add(new TextCharacter('O'));
        textExpected.add(new TextCharacter('L'));
        textExpected.add(new TextCharacter('A'));
        sptopscore.setPrintableScore(textExpected);
        assertEquals(textExpected, sptopscore.getPrintableScore());
    }

}
