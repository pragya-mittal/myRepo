package arrays;

public class MagicNumber {
//    A number that can be expressed as sum of power of 5

    static int magicNumber(int index) {

        int count = 0;
        for (int i=0; count<index; i++) {
            for (int j=0; j<=i; j++) {
                int number = 5^i;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int index = 1;
        System.out.println(magicNumber(index));
    }
}
