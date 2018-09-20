package trees;

import java.util.ArrayList;
import java.util.List;

public class NumberOfUniqueBST {
    static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            left=right=null;
        }

        private static int getUniqueBST(int k, int[] sols) {
            if ((k==0) || (k==1))
                return 1;

            int bsts = 0;

            for (int i=1;i<=k;i++) {
                if (sols[i-1]==-1)
                    sols[i-1] = getUniqueBST(i, sols);
                if (sols[k-i]==-1)
                    sols[k-i] = getUniqueBST(i, sols);
                bsts = bsts + sols[i-1]*sols[k-i];
            }

//            int leftBst=0, rightBST=0;
//            for (int i=1;i<=k;i++) {
//
//                int root = i;
//                List left = new ArrayList(),right = new ArrayList();
//
//                for (int j=1;j<root;j++) {
//                    left.add(j);
//                }
//                for (int j=root+1;j<=k;j++) {
//                    right.add(j);
//                }
//
//                if (left.size()!=0) {
//                    for (int l=0;l<left.size();l++) {
//                        leftBst = leftBst + getUniqueBST(l+1);
//                    }
//                }
//
//                if (right.size()!=0) {
//                    for (int r=0;r<right.size(); r++) {
//                        rightBST = rightBST + getUniqueBST(r+1);
//                    }
//                }
//
//
//                if (leftBst!=0&&rightBST!=0)
//                    bsts = bsts + leftBst*rightBST;
//                else
//                    bsts =  bsts + ((leftBst ==0) ? rightBST : leftBst);
//
////                bsts = bsts + getUniqueBST(i, k);
//            }
            return bsts;
        }

        public static int numTrees(int k)
        {
            int[] solutions = new int[k+1];

            for (int i = 0; i <=k; i++)
                solutions[i] = -1;

            return getUniqueBST(k, solutions);
        }


        public static void main(String[] args) {
            int k = 5;
            System.out.println("Bsts : " + numTrees(k));
        }


    }
}
