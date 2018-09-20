package trees;

import java.util.*;

public class VerticalTopBottomMap {
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


    void viewVertical(Node node) {
        if (node==null)
            return;
        Map<Integer, List> treeMap = new TreeMap();
        createMap(node, 0, treeMap);

        for (Map.Entry entry : treeMap.entrySet()) {
            List<Node> list = (List) entry.getValue();
            for (int i=0; i<list.size(); i++)
                System.out.print(list.get(i).key + " ");
            System.out.println();
        }
    }

    void createMap(Node node, int level, Map<Integer, List> treemap) {
        if (node==null)
            return;
        if (!treemap.containsKey(level)) {
            List list = new ArrayList();
            list.add(node);
            treemap.put(level, list);
        } else {
            List list = treemap.get(level);
            list.add(node);
            treemap.put(level, list);
        }
        createMap(node.left, level-1, treemap);
        createMap(node.right, level+1, treemap);
    }

    void viewVerticalBFS(Node node) {
        if (node==null)
            return;
        Map<Integer, List> treeMap = new TreeMap();
        createMapUsingBFS(node, 0, treeMap);

        for (Map.Entry entry : treeMap.entrySet()) {
            List<Node> list = (List) entry.getValue();
            for (int i=0; i<list.size(); i++)
                System.out.print(list.get(i).key + " ");
            System.out.println();
        }
    }

    class QueueNode {
        Node node;
        int level;
        public QueueNode(Node node, int level) {
            this.node=node;
            this.level=level;
        }
    }

    void createMapUsingBFS(Node node, int rootlevel, Map<Integer, List> treemap) {
        if (node==null)
            return;
        LinkedList<QueueNode> queue = new LinkedList();
        queue.add(new QueueNode(node, rootlevel));

        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.remove();

            if (!treemap.containsKey(queueNode.level)) {
                List list = new ArrayList();
                list.add(queueNode.node);
                treemap.put(queueNode.level, list);
            } else {
                List list = treemap.get(queueNode.level);
                list.add(queueNode.node);
                treemap.put(queueNode.level, list);
            }

            if (queueNode.node.left!=null)
                queue.add(new QueueNode(queueNode.node.left, queueNode.level-1));
            if (queueNode.node.right!=null)
                queue.add(new QueueNode(queueNode.node.right, queueNode.level+1));

        }
    }

    void viewTopBFS(Node node) {
        if (node==null)
            return;
        Map<Integer, List> treeMap = new TreeMap();
        createTopMapUsingBFS(node, 0, treeMap);

        for (Map.Entry entry : treeMap.entrySet()) {
            List<Node> list = (List) entry.getValue();
            System.out.print(list.get(0).key + " ");
        }
    }

    void createTopMapUsingBFS(Node node, int rootlevel, Map<Integer, List> treemap) {
        if (node==null)
            return;
        LinkedList<QueueNode> queue = new LinkedList();
        queue.add(new QueueNode(node, rootlevel));

        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.remove();

            if (!treemap.containsKey(queueNode.level)) {
                List list = new ArrayList();
                list.add(queueNode.node);
                treemap.put(queueNode.level, list);
            }

            if (queueNode.node.left!=null)
                queue.add(new QueueNode(queueNode.node.left, queueNode.level-1));
            if (queueNode.node.right!=null)
                queue.add(new QueueNode(queueNode.node.right, queueNode.level+1));

        }
    }


    void viewBottomBFS(Node node) {
        if (node==null)
            return;
        Map<Integer, Integer> treeMap = new TreeMap();
        createBottomMapUsingBFS(node, 0, treeMap);

        for (Map.Entry entry : treeMap.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }


    void createBottomMapUsingBFS(Node node, int rootlevel, Map<Integer, Integer> treemap) {
        if (node==null)
            return;
        LinkedList<QueueNode> queue = new LinkedList();
        queue.add(new QueueNode(node, rootlevel));

        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.remove();
            treemap.put(queueNode.level, queueNode.node.key);

            if (queueNode.node.left!=null)
                queue.add(new QueueNode(queueNode.node.left, queueNode.level-1));
            if (queueNode.node.right!=null)
                queue.add(new QueueNode(queueNode.node.right, queueNode.level+1));

        }
    }


    public static void main(String[] args) {
        VerticalTopBottomMap treeHeight = new VerticalTopBottomMap();
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
        treeHeight.viewVertical(treeHeight.root);
        System.out.println("\nVertical View BFS : ");
        treeHeight.viewVerticalBFS(treeHeight.root);
        System.out.println("\nTop View BFS : ");
        treeHeight.viewTopBFS(treeHeight.root);
        System.out.println("\nBottom View BFS : ");
        treeHeight.viewBottomBFS(treeHeight.root);


    }
}
