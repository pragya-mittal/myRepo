package design.textlineeditor;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class FileUtilImpl implements FileOperations {

    File file;
    BufferedReader br;
    BufferedWriter wr;

    public FileUtilImpl(String path) throws IOException {
        file = new File(path);
        br = new BufferedReader(new FileReader(file));
        wr = new BufferedWriter(new FileWriter(file, true)); // true is saying to append
    }

    @Override
    public void readFile(File file, FileUtil fu) throws TextEditorException, IOException {
        if (!file.exists())
            throw new TextEditorException("File not present");

        System.out.println("Reading file from path : " + file.getAbsolutePath().toString());
        String line;

        while((line=br.readLine())!=null) {
            System.out.println(line);
        }

        System.out.println("Read full file");

    }

    @Override
    public void writeInFile(File file, String[] data, FileUtil fu) throws TextEditorException {

//        FileUtils.write();
        try {
            for (String content : data) {
                wr.write(content);
            }
        } catch (Exception e) {
            throw new TextEditorException("Not able to write : " + file);
        }
    }

    @Override
    public void appendInFile(File file, String[] data, int index, FileUtil fu) throws TextEditorException {
        try {
            for (String content : data) {
//                wr.write(content, index);
            }
        } catch (Exception e) {
            throw new TextEditorException("Not able to write : " + file);
        }
    }

    @Override
    public void deleteFromFile(File file, int index, FileUtil fu) {


    }
}
