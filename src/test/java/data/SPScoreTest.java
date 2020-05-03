package data;

import com.googlecode.lanterna.TextCharacter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SPScoreTest {

    @Test
    public void testConstructor() {
        SinglePlayerScore spscore = new SinglePlayerScore();

        List<TextCharacter> textExpected=new ArrayList<>();
        textExpected.add(new TextCharacter('S'));
        textExpected.add(new TextCharacter('C'));
        textExpected.add(new TextCharacter('O'));
        textExpected.add(new TextCharacter('R'));
        textExpected.add(new TextCharacter('E'));
        textExpected.add(new TextCharacter(':'));
        textExpected.add(new TextCharacter(' '));

        assertEquals(0, spscore.getScore());
        assertEquals(textExpected, spscore.getText());
    }

    @Test
    public void testSetScore() {
        SinglePlayerScore spscore = new SinglePlayerScore();
        spscore.setScore(3);
        assertEquals(3, spscore.getScore());
    }

    @Test
    public void testSetPrintableScore() {
        SinglePlayerScore spscore = new SinglePlayerScore();
        List<TextCharacter> textExpected=new ArrayList<>();
        textExpected.add(new TextCharacter('O'));
        textExpected.add(new TextCharacter('L'));
        textExpected.add(new TextCharacter('A'));
        spscore.setPrintableScore(textExpected);
        assertEquals(textExpected, spscore.getPrintableScore());
    }


}
