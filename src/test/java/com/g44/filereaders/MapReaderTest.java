package com.g44.filereaders;

import com.g44.model.Wall;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MapReaderTest {

    @Test
    public void mapReaderTest() throws IOException {
        MapReader mapReader = new MapReader("src/main/java/com/g44/files/testMapReaderFile");

        List<Wall> wallsRead = new ArrayList<>();
        wallsRead = mapReader.getWallsRead();


        //check for wall position calculation and if right number of walls were read
        assertEquals(wallsRead.size(),3);
        assertEquals(wallsRead.get(0).getPosition().getX(),4);
        assertEquals(wallsRead.get(0).getPosition().getY(),2);
        assertEquals(wallsRead.get(1).getPosition().getX(),5);
        assertEquals(wallsRead.get(1).getPosition().getY(),2);
        assertEquals(wallsRead.get(2).getPosition().getX(),6);
        assertEquals(wallsRead.get(2).getPosition().getY(),2);
    }
}
