package data;

import com.googlecode.lanterna.TextCharacter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SPTopScoreTest {
    @Test
    public void testConstructor() throws IOException {
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

        List<TextCharacter> textExpected2=new ArrayList<>();
        textExpected2.add(new TextCharacter('3'));

        List<TextCharacter> textExpected3=new ArrayList<>();
        textExpected3.add(new TextCharacter('T'));
        textExpected3.add(new TextCharacter('O'));
        textExpected3.add(new TextCharacter('P'));
        textExpected3.add(new TextCharacter(' '));
        textExpected3.add(new TextCharacter('S'));
        textExpected3.add(new TextCharacter('C'));
        textExpected3.add(new TextCharacter('O'));
        textExpected3.add(new TextCharacter('R'));
        textExpected3.add(new TextCharacter('E'));
        textExpected3.add(new TextCharacter(':'));
        textExpected3.add(new TextCharacter(' '));
        textExpected3.add(new TextCharacter('3'));

        SinglePlayerTopScore sptscore=new SinglePlayerTopScore();

        SinglePlayerTopScore sptscore1 = mock(SinglePlayerTopScore.class);
        when(sptscore1.fileReader(filename)).thenReturn(textExpected2);

        assertEquals(filename, sptscore.getFilename());
        //assertEquals(textExpected3, sptscore.getPrintable());
        assertEquals(3, sptscore1.getTopScore());
    }

    @Test
    public void testSetScore() {
        SinglePlayerScore spscore = new SinglePlayerScore();
        spscore.setScore(3);
        assertEquals(3, spscore.getScore());
    }

    @Test
    public void testIncrementScore() {
        SinglePlayerScore spscore = new SinglePlayerScore();
        spscore.incrementScore();
        assertEquals(1, spscore.getScore());
    }

    @Test
    public void updatePrintableTest() {
        SinglePlayerScore spscore = new SinglePlayerScore();
        spscore.incrementScore();
        spscore.updatePrintable();
        List<TextCharacter> expected=new ArrayList<>();
        expected.add(new TextCharacter('S'));
        expected.add(new TextCharacter('C'));
        expected.add(new TextCharacter('O'));
        expected.add(new TextCharacter('R'));
        expected.add(new TextCharacter('E'));
        expected.add(new TextCharacter(':'));
        expected.add(new TextCharacter(' '));
        expected.add(new TextCharacter('1'));

        assertEquals(expected, spscore.getPrintableScore());
    }
}
