package stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    public static class Stack {
        Queue queue1;
        Queue queue2;
    }


    static void push(Stack stack, int data) {
        if (stack.queue1.isEmpty()) {
            stack.queue1.add(data);
        } else {
            stack.queue2.add(data);
            while (!stack.queue1.isEmpty()) {
                stack.queue2.add(pop(stack));
            }
            stack.queue1 = stack.queue2;
            stack.queue2 = new LinkedList();
        }

    }

    static int pop(Stack stack) {
        if (stack.queue1.isEmpty()) {
            System.exit(0);
        }
        return (Integer) stack.queue1.remove();
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.queue1 = new LinkedList();
        stack.queue2 = new LinkedList();

        push(stack, 1);
        push(stack, 2);
        push(stack, 3);


        System.out.println(pop(stack));
        System.out.println(pop(stack));
        System.out.println(pop(stack));

    }
}
