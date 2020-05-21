package data;

import com.googlecode.lanterna.TextCharacter;
import model.SinglePlayerScore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

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
        spscore.setPrintableScore(textExpected);
        assertEquals(textExpected, spscore.getPrintableScore());
    }

    @Test
    public void testIncrementScore() {
        SinglePlayerScore spScore = new SinglePlayerScore();
        spScore.incrementScore(spScore);
        assertEquals(1, spScore.getScore());
    }

    @Test
    public void updatePrintableTest() {
        SinglePlayerScore score = Mockito.mock(SinglePlayerScore.class);
        Mockito.when(score.getScore()).thenReturn(3);

        List<TextCharacter> textaux=new ArrayList<>();
        textaux.add(new TextCharacter('S'));
        textaux.add(new TextCharacter('C'));
        textaux.add(new TextCharacter('O'));
        textaux.add(new TextCharacter('R'));
        textaux.add(new TextCharacter('E'));
        textaux.add(new TextCharacter(':'));
        textaux.add(new TextCharacter(' '));
        Mockito.when(score.getText()).thenReturn(textaux);

        List<TextCharacter> actual = score.updatePrintable(score);

        List<TextCharacter> expected=new ArrayList<>();
        expected.add(new TextCharacter('S'));
        expected.add(new TextCharacter('C'));
        expected.add(new TextCharacter('O'));
        expected.add(new TextCharacter('R'));
        expected.add(new TextCharacter('E'));
        expected.add(new TextCharacter(':'));
        expected.add(new TextCharacter(' '));
        expected.add(new TextCharacter('3'));

//        assertEquals(expected, actual);
    }

    @Test
    public void testFileReader(){
        //Perguntar ao prof

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFileWriter(){
        //Perguntar ao prof

    }
}
