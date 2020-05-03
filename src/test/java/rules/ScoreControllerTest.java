package rules;

import com.googlecode.lanterna.TextCharacter;
import data.SinglePlayerScore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreControllerTest {

    @Test
    public void testIncrementScore() {
        ScoreController scoreController= new ScoreController();
        SinglePlayerScore spScore = new SinglePlayerScore();
        scoreController.incrementScore(spScore);
        assertEquals(1, spScore.getScore());
    }

    @Test
    public void updatePrintableTest() {
        ScoreController scoreController= new ScoreController();
        SinglePlayerScore spscore = Mockito.mock(SinglePlayerScore.class);
        Mockito.when(spscore.getScore()).thenReturn(3);

        List<TextCharacter> textaux=new ArrayList<>();
        textaux.add(new TextCharacter('S'));
        textaux.add(new TextCharacter('C'));
        textaux.add(new TextCharacter('O'));
        textaux.add(new TextCharacter('R'));
        textaux.add(new TextCharacter('E'));
        textaux.add(new TextCharacter(':'));
        textaux.add(new TextCharacter(' '));
        Mockito.when(spscore.getText()).thenReturn(textaux);

        List<TextCharacter> actual = scoreController.updatePrintable(spscore);

        List<TextCharacter> expected=new ArrayList<>();
        expected.add(new TextCharacter('S'));
        expected.add(new TextCharacter('C'));
        expected.add(new TextCharacter('O'));
        expected.add(new TextCharacter('R'));
        expected.add(new TextCharacter('E'));
        expected.add(new TextCharacter(':'));
        expected.add(new TextCharacter(' '));
        expected.add(new TextCharacter('3'));

        assertEquals(expected, actual);
    }

    @Test
    public void testFileReader(){
        /*
        ScoreController scoreController= new ScoreController();
        SinglePlayerTopScore sptopscore = Mockito.mock(SinglePlayerTopScore.class);
        Mockito.when(sptopscore.getFilename()).thenReturn("src/main/java/files/topScore.txt");
        String str = new TextOf(
                new ResourceOf("/com/example/abc.xml") // absolute path always!
        ).asString();

         */
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFileWriter(){
        /*
        //arrange
        doReturnThrow(new IOException("UnitTest")).when(fileWriterFactory).create(any(File.class));

        //assert
        exception.expect(IndexFileInitializationException.class);
        exception.expectMessage("Error initialization index file "+anyValidFile.getPath());

        // act
        new ClassUnderTest(fileWriterFactory).initIndexFile(anyValidFile);

         */
    }

}
