package arrays.strings;

public class ReverseString {
    public static void main(String[] args) {
        String val = "This is a string";
        System.out.println(reverseString(val.toCharArray()));
    }

    private static char[] reverseString(char[] chars) {
        int start = 0;
        for (int i=0;i<chars.length;i++) {
            if (chars[i]== ' ') {
                reverse(chars, start, i-1);
                start = i+1;
            } else if (i==chars.length-1) {
                reverse(chars, start, i);
            }
        }
        reverse(chars, 0, chars.length-1);
        return chars;
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start<=end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
