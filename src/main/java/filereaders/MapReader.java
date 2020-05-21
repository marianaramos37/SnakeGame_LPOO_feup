package filereaders;

import model.Wall;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    private String filename;
    private List<Wall> wallsRead = new ArrayList<>();

    public MapReader(String filename) throws IOException {
        this.filename=filename;
        Charset encoding = Charset.defaultCharset();
        File file = new File(filename);
        fileReader(file, encoding);
    }

    private void fileReader(File file, Charset encoding) throws IOException {
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in, encoding);
        Reader buffer = new BufferedReader(reader);
        handler(buffer);
    }

    private void handler(Reader reader) throws IOException {
        int r;
        int x = 0, y = 0;

        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            if(ch == '#'){
                this.wallsRead.add(new Wall(x,y));
                x++;
                continue;
            }
            if(ch == '\n'){
                y++;
                x=0;
                continue;
            }
            x++;
        }
    }

    public List<Wall> getWallsRead() {
        return wallsRead;
    }
}
