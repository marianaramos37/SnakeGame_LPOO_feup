package com.g44.model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SPTopScoreTest {
    @Test
    public void testConstructor() throws IOException {
        SinglePlayerTopScore sptopscore = new SinglePlayerTopScore();

        String filename= "src/main/java/com/g44/files/topScore.txt";

        assertEquals(filename, sptopscore.getFilename());
    }

    @Test
    public void testFileReaderAndGet() throws IOException {
        SinglePlayerTopScore spScore = new SinglePlayerTopScore();

        spScore.setFilename("src/main/java/com/g44/files/testTopScore.txt");

        spScore.fileReader();

        assertEquals(89, spScore.getScore());
    }

    @Test
    public void testSetScore(){
        SinglePlayerTopScore spScore = new SinglePlayerTopScore();
        spScore.setScore(90);
        assertEquals(90,spScore.getScore());
    }

    @Test
    public void testIncrementScore() {
        SinglePlayerTopScore spScore = new SinglePlayerTopScore();
        spScore.incrementScore();
        assertEquals(1, spScore.getScore());
    }

    @Test
    public void testFileWritter() throws IOException {
        SinglePlayerTopScore spScore = new SinglePlayerTopScore();
        spScore.setFilename("src/main/java/com/g44/files/testTopScoreWritter");
        spScore.setScore(8888);

        spScore.fileWriter();

        spScore.fileReader();

        assertEquals(8888,spScore.getScore());
    }
}
