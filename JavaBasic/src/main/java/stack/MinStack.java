package stack;

import java.util.Stack;

public class MinStack {
    static int min;

    static int getMin(Stack stack) {
        if (stack.isEmpty()) {
            return -1;
        }
        int x = (Integer) stack.pop();
        min =  getMin(stack);
        if (min > x) {
            min = x;
        }
        stack.push(x);
        return min;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add(18);
        stack.add(19);
        stack.add(29);
        stack.add(15);
        stack.add(16);
        System.out.println(getMin(stack));
        System.out.println(stack.pop());
    }
}
