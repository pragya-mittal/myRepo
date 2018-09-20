package design.textlineeditor;

import java.io.*;

public class FileUtil implements AutoCloseable{
    File file;
    BufferedReader br;
    BufferedWriter wr;

    public FileUtil(String path) throws IOException, TextEditorException {
        file = new File(path);
        br = new BufferedReader(new FileReader(file));
        wr = new BufferedWriter(new FileWriter(file, true)); // true is saying to append
    }

    @Override
    public void close() throws TextEditorException, IOException {
        br.close();
        wr.close();
    }
}
