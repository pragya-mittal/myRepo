package queue;

import java.util.Stack;

public class QueueUsingStack {

    static class Queue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

    }
//
//    int pop(Stack<Integer> stackTop) {
//        if(stackTop.empty()) {
//            System.out.println("Empty");
//            System.exit(0);
//        }
//        return stackTop.pop();
//    }
//
//    void push(Stack<Integer> stackTop, int data) {
//        stackTop.push(data);
//    }

    static int dequeue(Queue queue) {
        if (queue.stack2.empty()) {
            while (!queue.stack1.empty()) {
                queue.stack2.push(queue.stack1.pop());
            }
        }
        return queue.stack2.pop();
    }

    static void enqueue(Queue queue, int data) {
        queue.stack1.push(data);

    }


    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.stack1 = new Stack<Integer>();
        queue.stack2 = new Stack<Integer>();

        enqueue(queue, 1);
        enqueue(queue, 2);
        System.out.println(dequeue(queue));
        System.out.println(dequeue(queue));



    }
}
