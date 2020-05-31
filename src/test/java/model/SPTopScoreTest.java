package model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SPTopScoreTest {
    @Test
    public void testConstructor() throws IOException {
        SinglePlayerTopScore sptopscore = new SinglePlayerTopScore();

        String filename= "src/main/java/files/topScore.txt";

        assertEquals(filename, sptopscore.getFilename());

    }


    @Test
    public void testIncrementScore() {
        SinglePlayerTopScore spScore = new SinglePlayerTopScore();
        spScore.incrementScore();
        assertEquals(1, spScore.getScore());
    }

}
