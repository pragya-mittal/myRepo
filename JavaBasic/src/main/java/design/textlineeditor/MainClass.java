package design.textlineeditor;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws TextEditorException {
        Scanner scanner;
        String path = "";
        try (FileUtil fu = new FileUtil(path)) {

        } catch (TextEditorException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
