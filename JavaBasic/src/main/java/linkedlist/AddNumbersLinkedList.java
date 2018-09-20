package linkedlist;

import java.util.ArrayList;
import java.util.Stack;

public class AddNumbersLinkedList {
    LinkNode head;
    LinkNode tail;

    static class LinkNode {
        int data;
        LinkNode next;
        public LinkNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    LinkNode createLinkList(int[] arrayInt) {
        if(head != null) {
            head = null;
        }
        for (int i : arrayInt) {
            if (head == null) {
                head = new LinkNode(i);
                tail = head;
            } else {
                LinkNode linkNode = new LinkNode(i);
                tail.next = linkNode;
                tail = linkNode;
            }

        }
        return head;
    }

    Stack<Integer> addList (LinkNode list1, LinkNode list2) {
        int carrier = 0;

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        Stack<Integer> stack3 = new Stack<Integer>();

        while(list1!=null) {
            stack1.add(list1.data);
            list1 = list1.next;
        }

        while(list2!=null) {
            stack2.push(list2.data);
            list2 = list2.next;
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + carrier;
            carrier = sum/10;
            int val = sum%10;
            stack3.push(val);
        }
        if (!stack1.isEmpty()) {
            while(!stack1.isEmpty()) {
                int sum = stack1.pop() + carrier;
                int val = sum%10;
                carrier = sum/10;
                stack3.push(val);
            }
        }
        if (!stack2.isEmpty()) {
            while (!stack2.isEmpty()) {
                int sum = stack2.pop() + carrier;
                int val = sum%10;
                carrier = sum/10;
                stack3.push(val);
            }
        }
        if (carrier!=0) {
            stack3.push(carrier);
        }
        return stack3;
    }

    public static void main(String[] args) {
        int[] firstNumber  = {9,9,9,7,1}; // number: 99971
        int[] secondNumber = {9,9,8};
        AddNumbersLinkedList soltion = new AddNumbersLinkedList();
        LinkNode list1 = soltion.createLinkList(firstNumber);
        LinkNode list2 = soltion.createLinkList(secondNumber);
        Stack sum = soltion.addList(list1, list2);
//        100969
        while (!sum.isEmpty()) {
            System.out.println(sum.pop());
        }

    }
}
