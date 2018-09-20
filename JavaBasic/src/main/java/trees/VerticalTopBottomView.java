package trees;

import java.util.ArrayList;
import java.util.List;

public class VerticalTopBottomView {

    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    class Value {
        int min, max;
        public Value() {
            min=0; max=0;
        }
    }

    void getVerticalView(Node node) {
        if(node==null)
            return;
        Value value = getMinMax(node, new Value(), 0);
        for (int i=value.min; i<=value.max; i++) {
            traverseVertical(node, 0, i);
            System.out.println();
        }
    }

    void traverseVertical(Node node, int level, int printLevel) {
        if (node==null)
            return;
        if (level==printLevel)
            System.out.print(node.key + " ");
        traverseVertical(node.left, level-1, printLevel);
        traverseVertical(node.right, level+1, printLevel);
    }

    void getTopView(Node node) {
        if(node==null)
            return;
        Value value = getMinMax(node, new Value(), 0);
        for (int i=value.min; i<=value.max; i++) {
            List arrayList = traverseTree(node, 0, i, new ArrayList());
            System.out.print(arrayList.get(0) + " ");
        }
    }

    void getBottomView(Node node) {
        if(node==null)
            return;
        Value value = getMinMax(node, new Value(), 0);
        for (int i=value.min; i<=value.max; i++) {
            List arrayList = traverseTree(node, 0, i, new ArrayList());
            System.out.print(arrayList.get(arrayList.size()-1) + " ");
        }
    }

    List traverseTree(Node node, int level, int printLevel, ArrayList arrayList) {
        if (node==null)
            return null;
        if (level==printLevel) {
            arrayList.add(node.key);
        }
        traverseTree(node.left, level-1, printLevel, arrayList);
        traverseTree(node.right, level+1, printLevel, arrayList);
        return arrayList;
    }



    Value getMinMax(Node node, Value val, int level) {
        if (node==null)
            return null;
        if (level<val.min)
            val.min=level;
        if(level>val.max)
            val.max=level;
        getMinMax(node.left,val,level-1);
        getMinMax(node.right,val,level+1);
        return val;
    }

    public static void main(String[] args) {
        VerticalTopBottomView treeHeight = new VerticalTopBottomView();
        treeHeight.root = new Node(1);
        treeHeight.root.left = new Node(2);
        treeHeight.root.left.left = new Node(4);
        treeHeight.root.left.left.left = new Node(8);
        treeHeight.root.left.right = new Node(5);
        treeHeight.root.right = new Node(3);
        treeHeight.root.right.left = new Node(6);
        treeHeight.root.right.left.left = new Node(9);
        treeHeight.root.right.right = new Node(7);
        treeHeight.root.right.right.right = new Node(10);
        treeHeight.root.right.right.right.left = new Node(11);

        System.out.println("Vertical View : ");
        treeHeight.getVerticalView(treeHeight.root);
        System.out.println("\nTop view : ");
        treeHeight.getTopView(treeHeight.root);
        System.out.println("\nBottom view : ");
        treeHeight.getBottomView(treeHeight.root);


    }
}
