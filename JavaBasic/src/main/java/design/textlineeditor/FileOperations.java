package design.textlineeditor;

import java.io.File;
import java.io.IOException;

public interface FileOperations {
    void readFile(File file, FileUtil fu) throws TextEditorException, IOException;
    void writeInFile(File file ,String[] data, FileUtil fu) throws TextEditorException, IOException;
    void appendInFile(File file, String[] data, int index, FileUtil fu) throws TextEditorException;
    void deleteFromFile(File file, int index, FileUtil fu);
}
