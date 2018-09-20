package graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversal {
    static class Graph {
        int v;
        LinkedList<Integer>[] adjacentList;

        public Graph(int v) {
            this.v = v;
            adjacentList = new LinkedList[v];
            for (int i=0;i<v; i++) {
                adjacentList[i] = new LinkedList();
            }
        }

    }

    public static void addEdge(Graph graph,int src, int dest) {
        graph.adjacentList[src].addFirst(dest);
        //undirected
//        graph.adjacentList[dest].addFirst(src);
    }

    public static void printGraph(Graph graph) {
        for (int i=0;i<graph.v; i++) {
            System.out.println("Vertex : " + i);
            for (Integer j : graph.adjacentList[i]) {
                System.out.print(j + "->");
            }
            System.out.println();
        }
    }

    public static void DFS(Graph graph) {
        boolean[] visited=new boolean[graph.v];
        for (int i=0;i<graph.v;i++) {
            if(!visited[i])
                DFSUtil(graph, i, visited);

        }
    }

    private static void DFSUtil(Graph graph, int i, boolean[] visited) {
        visited[i]=true;
        System.out.print(i + " ");
        for (int j : graph.adjacentList[i]) {
            if (!visited[j])
                DFSUtil(graph,j, visited);
        }
    }

    private static void BFS(Graph graph,int src) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited=new boolean[graph.v];

        queue.add(src);
        visited[src]=true;
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            System.out.print(vertex + " ");
            for (Integer node : graph.adjacentList[vertex]) {
                if(!visited[node]) {
                    visited[node]=true;
                    queue.add(node);
                }
            }
        }
    }

    private static Stack<Integer> topologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        Stack<Integer> stack = new Stack();
        for (int i=0;i<graph.v;i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        return stack;
    }

    private static void topologicalSortUtil(Graph graph, int i, boolean[] visited, Stack<Integer> stack) {
        visited[i]=true;
        for (Integer j : graph.adjacentList[i]) {
            if (!visited[j])
                topologicalSortUtil(graph, j, visited, stack);
        }
        stack.push(i);
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
        System.out.println("DFS");
        DFS(graph);
        System.out.println();
        System.out.println("BFS");
        BFS(graph, 0);

//        printGraph(graph);

        Graph g = new Graph(6);
        addEdge(g, 5, 2);
        addEdge(g, 5, 0);
        addEdge(g, 4, 0);
        addEdge(g, 4, 1);
        addEdge(g, 2, 3);
        addEdge(g, 3, 1);
        System.out.println();
        System.out.println("Topological : ");
        topologicalSort(g);

    }

}
