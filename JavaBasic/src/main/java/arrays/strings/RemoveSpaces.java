package arrays.strings;

public class RemoveSpaces {
    public static void main(String[] args) {
        String val = "This is a string";
        System.out.println(removeSpaces(val.toCharArray()));
    }

    private static char[] removeSpaces(char[] chars) {
        int whiteSpace=0;
        for (int i=0;i<chars.length;i++) {
            if (chars[i]== ' ')
                whiteSpace++;
            else
                chars[i-whiteSpace] = chars[i];
        }
        return chars;
    }
}
