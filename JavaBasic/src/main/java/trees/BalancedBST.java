package trees;

import java.util.ArrayList;
import java.util.List;

public class BalancedBST {
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node root;

    void createBST(int[] arr) {
        if (arr.length==0)
            return;
        root = new Node(arr[0]);
        for (int i=1; i<arr.length; i++) {
            root = insertBST(root, arr[i]);
        }
    }

    Node insertBST(Node node, int key) {
        if (node==null) {
            node = new Node(key);
            return node;
        }
        if (key==node.key)  // No duplicates allowed
            return node;
        if (key<node.key)
            node.left = insertBST(node.left, key);
        if (key>node.key)
            node.right = insertBST(node.right, key);
        return node;
    }

    void printInorder(Node node) {
        if (node==null)
            return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }


    int getHeight(Node node) {
        if (node==null)
            return 0;
        int height = 1;
        int nodeHeight = height + Math.max(getHeight(node.left), getHeight(node.right));
        return nodeHeight;
    }


    boolean checkBalancedBST(Node node) {
        if (node == null)
            return true;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (Math.abs(leftHeight-rightHeight)==1 || (leftHeight-rightHeight)==0) {
            if(checkBalancedBST(node.left) && checkBalancedBST(node.right))
            return true;
        }
        return false;
    }

    int getBalancedFactor(Node node) {
        if (node == null)
            return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return leftHeight-rightHeight;
    }

    Node convertBSTtoBalancedBST(Node node) {
        if (node==null|| checkBalancedBST(node))
            return null;

        List nodeList = new ArrayList();
        createList(node, nodeList);

        Node nodeBalance = createBalanceBST(nodeList, 0, nodeList.size()-1);
        return nodeBalance;

    }

    private Node createBalanceBST(List nodeList, int start, int last) {
        if (start>last)
            return null;

        int mid = (start+last)/2;
        Node node = (Node) nodeList.get(mid);
        node.left = createBalanceBST(nodeList, start, mid-1);
        node.right = createBalanceBST(nodeList, mid+1, last);
        return node;
    }

    private void createList(Node node, List nodeList) {
        if (node==null)
            return;
        createList(node.left, nodeList);
        nodeList.add(node);
        createList(node.right, nodeList);

    }


    public static void main(String[] args) {
        int arr[] = {9,2,6,8,11,23,21,33,1,4,5,10,0};

        BalancedBST balancedBST1 = new BalancedBST();
        balancedBST1.createBST(arr);
        balancedBST1.printInorder(balancedBST1.root);
        System.out.println("\n Balance : " + balancedBST1.checkBalancedBST(balancedBST1.root));

        int arr1[] = {9,2,6,8,11,23,21,33,1,4,5,10};
        BalancedBST balancedBST = new BalancedBST();
        balancedBST.createBST(arr1);
        balancedBST.printInorder(balancedBST1.root);
        System.out.println("\n Balance : " + balancedBST.checkBalancedBST(balancedBST.root));
        Node node = balancedBST.convertBSTtoBalancedBST(balancedBST.root);
        System.out.println("\n Balance : " + balancedBST.checkBalancedBST(node));

    }


}
