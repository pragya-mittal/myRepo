package trees;

public class BinarySearchTree {

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

    void pretOrder(Node node) {
        if (node==null)
            return;
        System.out.print(node.key + " ");
        pretOrder(node.left);
        pretOrder(node.right);
    }

    void postOrder(Node node) {
        if (node==null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.key + " ");
    }

    Node searchNode(Node node, int key) {
        if (node==null)
            return null;
        if (key==node.key)
            return node;
        else if (key < node.key)
            return searchNode(node.left, key);
        else //if (key > node.key)
            return searchNode(node.right, key);

    }


    // wrong
     void checkBST(Node node) {
        if (node==null)
            return;
        if (node.left!=null && node.left.key < node.key) {
            checkBST(node.left);
        } else if (node.right!=null && node.right.key > node.key) {
            checkBST(node.right);
        } else if(node.left!=null && node.left.key > node.key) {
            System.out.println("Tree not bst");
            return;
        } else if(node.left!=null && node.right.key < node.key) {
            System.out.println("Tree not bst");
            return;
        }
    }

    Node deleteNode(Node node, int data) {
        if (node==null)
            return null;
        if (data<node.key)
            node.left = deleteNode(node.left, data);
        else if (data>node.key)
            node.right = deleteNode(node.right, data);

        //(node.key == data)
        else {
            if (node.left==null)
                node = node.right;
            else if (node.right==null)
                node=node.left;
            else {
                int min = findMinRight(node.right, node.right.key);
                node.key=min;
                node.right = deleteNode(node.right, min);
            }
        }

        return node;

    }


    // Find next successor in inorder
    int findMinRight(Node node, int min) {
        if (node==null)
            return min;
        if (min > node.key && node.left==null) {
            min=node.key;
        }
        return findMinRight(node.left, min);
    }

    Node removeOutOfRangeNodes(Node node, int min, int max) {
        if (node==null)
            return null;
        node.left = removeOutOfRangeNodes(node.left, min, max);
        node.right = removeOutOfRangeNodes(node.right, min, max);
        if (node.key < min || node.key > max) {
            node = deleteNode(node, node.key);
        }
        return node;
    }

    Node removeOutOfRangeNodesNew(Node node, int min, int max) {
        if (node==null)
            return null;
        node.left = removeOutOfRangeNodes(node.left, min, max);
        node.right = removeOutOfRangeNodes(node.right, min, max);
        if (node.key < min)
            return node.right;
        if (node.key > max)
            return node.left;
        return node;
    }

    Node removeMin (Node node, int min) {
        if (node == null)
            return null;
        if (node.key > min) {
            node.left = removeMin(node.left, min);
        } else { // (node.key < min)
            node = deleteNode(node, node.key);
            return removeMin(node, min);
        }
        return node;
    }

    Node removeMax (Node node, int max) {
        if (node==null)
            return null;
        if (node.key < max)
            node.right = removeMax(node.right, max);
        else {
            node = deleteNode(node, node.key);
            return removeMax(node, max);
        }
        return node;
    }


    Node getParent(Node node, int childData) {
        if (node == null)
            return null;
        if (node.left!=null && node.left.key==childData)
            return node;
        else if (node.right!=null && node.right.key==childData)
            return node;
        else if (node.key == childData) // when node is root
            return node;
        else if(node.left!=null && childData<node.key){
            return getParent(node.left, childData);
        } else {
            return getParent(node.right, childData);
        }
    }

    Node getLowestCommonAncestor(Node node, int data1, int data2) {
        if (node==null)
            return null;
        // Check whether node exists in tree
        Node node1 = searchNode(node, data1);
        Node node2 = searchNode(node, data2);

        return getLowestCommonAncestor(node, node1, node2);
    }

    Node getLowestCommonAncestor(Node node, Node node1, Node node2) {
        if (node==null || node1==null || node2==null)
            return null;
        Node parentNode1 = getParent(node, node1.key);
        Node parentNode2 = getParent(node, node2.key);

        if (parentNode1==null || parentNode2==null)
            return null;
        else if (parentNode1==parentNode2)
            return parentNode1;
        else if (parentNode1==node2)
            return parentNode1;
        else if (parentNode2 == node1)
            return parentNode2;
        else
            return getLowestCommonAncestor(node, parentNode1, parentNode2);
    }

    int getHeight(Node node) {
        if (node==null)
            return 0;
        int height = 1;
        int nodeHeight = height + Math.max(getHeight(node.left), getHeight(node.right));
        return nodeHeight;
    }


    public static void main(String[] args) {
        int[] arr = {9,2,6,8,11,23,21,33,1,4,5,10};
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        // Create tree
        binarySearchTree.createBST(arr);
        System.out.println("Old : ");
        System.out.println("Inorder - ");
        binarySearchTree.printInorder(binarySearchTree.root);
        System.out.println("\nPreorder - ");
        binarySearchTree.pretOrder(binarySearchTree.root);
        System.out.println("\nPostorder - ");
        binarySearchTree.postOrder(binarySearchTree.root);

        // Add element
        binarySearchTree.insertBST(binarySearchTree.root, 3);
        System.out.println("\nNew inorder : ");
        binarySearchTree.printInorder(binarySearchTree.root);

        // Search element
        Node node = binarySearchTree.searchNode(binarySearchTree.root, 23);
        if (node!=null)
            System.out.println("\nNode is : " + node.key + " , left : " + node.left.key + " , right : " + node.right.key);
        node = binarySearchTree.searchNode(binarySearchTree.root, 56);
        if (node!=null)
            System.out.println("\nNode is : " + node.key + " , left : " + node.left.key + " , right : " + node.right.key);
        else
            System.out.println("Node not present");

        System.out.println("\nDelete node ");
        Node newNode = binarySearchTree.deleteNode(binarySearchTree.root, 21);

        System.out.println("Inorder - ");
        binarySearchTree.printInorder(newNode);

        System.out.println("\nDelete node ");
        newNode = binarySearchTree.deleteNode(binarySearchTree.root, 2);

        System.out.println("Inorder - ");
        binarySearchTree.printInorder(newNode);

        System.out.println("\nDelete node 9");
        newNode = binarySearchTree.deleteNode(binarySearchTree.root, 9);

        System.out.println("Inorder - ");
        binarySearchTree.printInorder(newNode);

        System.out.println("\nDelete node 8");
        binarySearchTree.deleteNode(binarySearchTree.root, 8);

        System.out.println("Inorder - ");
        binarySearchTree.printInorder(newNode);

        binarySearchTree.insertBST(binarySearchTree.root, 21);
        binarySearchTree.insertBST(binarySearchTree.root, 2);

        System.out.println("\nInsertion inorder - ");
        binarySearchTree.printInorder(binarySearchTree.root);

        // Parent
        Node parentnode = binarySearchTree.getParent(binarySearchTree.root, 4);
        if (parentnode !=null)
            System.out.println("\nParent " + parentnode.key);
        else
            System.out.println("\nNode not present");

        parentnode = binarySearchTree.getParent(binarySearchTree.root, 10);
        if (parentnode !=null)
            System.out.println("\nParent " + parentnode.key);
        else
            System.out.println("\nNode not present");

        parentnode = binarySearchTree.getParent(binarySearchTree.root, 50);
        if (parentnode !=null)
            System.out.println("\nParent " + parentnode.key);
        else
            System.out.println("\nNode not present");

        // Lowest common ancestor
        Node lcsNode = binarySearchTree.getLowestCommonAncestor(binarySearchTree.root, 2, 21);
        if (lcsNode !=null)
            System.out.println("\nLowest common ancestor " + lcsNode.key);
        else
            System.out.println("\nLCS Node not present");

        lcsNode = binarySearchTree.getLowestCommonAncestor(binarySearchTree.root, 2, 20);
        if (lcsNode !=null)
            System.out.println("\nLowest common ancestor " + lcsNode.key);
        else
            System.out.println("\nLCS Node not present");

        lcsNode = binarySearchTree.getLowestCommonAncestor(binarySearchTree.root, 33, 23);
        if (lcsNode !=null)
            System.out.println("\nLowest common ancestor " + lcsNode.key);
        else
            System.out.println("\nLCS Node not present");

//        binarySearchTree.removeMin(binarySearchTree.root, 3);
//        System.out.println("\nMin Inorder - ");
//        binarySearchTree.printInorder(binarySearchTree.root);
//
//        binarySearchTree.removeMax(binarySearchTree.root, 20);
//        System.out.println("\nMax Inorder - ");
//        binarySearchTree.printInorder(binarySearchTree.root);
//
//        binarySearchTree.removeOutOfRangeNodes(binarySearchTree.root, 3, 20);
//        System.out.println("\nRange Inorder - ");
//        binarySearchTree.printInorder(binarySearchTree.root);

        binarySearchTree.removeOutOfRangeNodesNew(binarySearchTree.root, 3, 20);
        System.out.println("\nNew Range Inorder - ");
        binarySearchTree.printInorder(binarySearchTree.root);


//        // Check bst
        binarySearchTree.checkBST(binarySearchTree.root);

        System.out.println("Check");
        BinarySearchTree treeHeight1 = new BinarySearchTree();
        treeHeight1.root = new Node(3);
        treeHeight1.root.right = new Node(4);
        treeHeight1.root.left = new Node(1);
//        treeHeight1.root.left.left = new Node(1);
//        treeHeight1.root.left.right = new Node(4);


    }
}
