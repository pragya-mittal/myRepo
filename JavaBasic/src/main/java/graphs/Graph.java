package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph<T>
{
    private class QueueNode
    {
        GraphNode graphNode;
        int level;

        // this constructor is used while doing breadth first traversal
        public QueueNode(GraphNode node, int level)
        {
            this.graphNode = node;
            this.level = level;
        }
    }

    private class GraphNode
    {
        T nodeId;
        GraphNode next;
        int parentDist;

        GraphNode(T id)
        {
            nodeId = id;
            next = null;
        }

        GraphNode(T id, int dist)
        {
            nodeId = id;
            next = null;
            parentDist = dist;
        }
    }

    ArrayList<GraphNode> nodeList;

    public Graph()
    {
        nodeList = new ArrayList<GraphNode>();
    }


    private void addNode(T id)
    {
        GraphNode node = new GraphNode(id);
        nodeList.add(node);
    }

    private void addEdge(T id1, T id2, int dist)
    {
        int i = 0;

        for (i = 0; i < nodeList.size(); i++)
        {
            if (nodeList.get(i).nodeId == id1)
            {
                break;
            }
        }

        if (i == nodeList.size())
        {
            return;
        }

        GraphNode node1 = nodeList.get(i);
        GraphNode node2 = new GraphNode(id2, dist);

        node2.next = node1.next;
        node1.next = node2;
    }

    // find the node with required nodeId in graph's node list.
    private GraphNode findGraphNode(T nodeId)
    {
        for(int i = 0; i < nodeList.size(); i++)
        {
            if(nodeList.get(i).nodeId == nodeId)
            {
                return nodeList.get(i);
            }
        }

        return null;
    }

    public void printGraph()
    {
        for (int i = 0; i < nodeList.size(); i++)
        {
            GraphNode curr = nodeList.get(i);

            while (curr != null)
            {
                System.out.print(curr.nodeId+"("+curr.parentDist+")"+"->");
                curr = curr.next;
            }
            System.out.print("Null");
            System.out.println();
        }
    }

    /*
     * Does a breadth first traversal of a given graph starting from node with id = srcId.
     */
    public boolean breadthFirstSearch(T srcId, T destId)
    {
        if (nodeList.isEmpty())
        {
            System.out.println("Empty graph");
            return false;
        }

        // queue used during the traversal
        LinkedList<QueueNode> queue = new LinkedList();

        // keeps track of node which are visited and added into the queue
        HashMap<T, Integer> visited = new HashMap();

        // find srcNode with id = srcId in graph
        GraphNode srcNode = null;
        for (int i = 0; i < nodeList.size(); i++)
        {
            if (nodeList.get(i).nodeId == srcId)
            {
                srcNode = nodeList.get(i);
                break;
            }
        }

        // if srcNode is not there in graph, breadth first traversal which starts at srcNode cannot be done
        if (srcNode == null)
        {
            System.out.println("Source vertex not found");
            return false;
        }
        boolean destNodeFound = false;

        int maxLevelVisited = -1;

        // add srcNode in queue, mark it as visited
        queue.add(new QueueNode(srcNode, 0));
        visited.put(srcNode.nodeId, 1);

        while (!queue.isEmpty())
        {
            QueueNode currentNode = queue.remove();

            // if we are now visiting a new level, add new line, update maxLevel visited
            if (currentNode.level > maxLevelVisited)
            {
                System.out.print("\nlevel " + currentNode.level+ "-");
                maxLevelVisited = currentNode.level;
            }

            // print current graph node
            System.out.print(currentNode.graphNode.nodeId + "  ");
            if (currentNode.graphNode.nodeId == destId)
            {
                destNodeFound = true;
            }

            // first neighbor of current graph node
            GraphNode neighbor = currentNode.graphNode.next;

            // add all neighbors of current graph node into the queue
            while (neighbor != null)
            {
                // if this neighbor is not visited earlier, mark it as visited
                // add it to the queue at appropriate level
                if (visited.get(neighbor.nodeId) == null)
                {
                    visited.put(neighbor.nodeId, 1);
                    queue.add(new QueueNode(findGraphNode(neighbor.nodeId), currentNode.level + 1));
                }
                neighbor = neighbor.next;
            }
        }

        return destNodeFound;
    }

    public static void createGraph(Graph graph)
    {
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);

        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 4, 3);

        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 1);
    }

    public static void main(String[] args)
    {
        Graph<Integer> graph = new Graph();

        createGraph(graph);

        // prints adjacency list representation of this graph.
        // graph.printGraph();

        int srcNodeId = 0, destNodeId = 5;

        // search destination node from source node using breadth first search
        System.out.print("\n\nIf path exists between source and destination node:\n" + graph.breadthFirstSearch(srcNodeId, destNodeId));
    }
}
