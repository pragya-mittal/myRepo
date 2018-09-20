package graphs;

import java.util.LinkedList;

public class GraphCreation {
    static class Graph {
        int v;
        LinkedList<Integer>[] adjlistarray;

        public Graph(int v) {
            this.v = v;
            adjlistarray = new LinkedList[v];
            for (int i=0;i<v;i++) {
                adjlistarray[i] = new LinkedList();
            }
        }
    }

    private static void addEdge(Graph graph, int src, int dest) {
        graph.adjlistarray[src].addFirst(dest);
        //undirected
        graph.adjlistarray[dest].addFirst(src);
    }

    private static void printGraph(Graph graph) {

        for (int i=0;i<graph.v;i++) {
            System.out.println("Vertex : " + i);
            System.out.print("Edge : ");
            for (Integer j : graph.adjlistarray[i]) {
                System.out.print(j + "->");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        printGraph(graph);
    }


}
