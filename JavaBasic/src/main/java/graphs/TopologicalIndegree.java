package graphs;

import java.util.ArrayList;
import java.util.Hashtable;

public class  TopologicalIndegree
{
    static private class GraphNode
    {
        int nodeId;
        GraphNode next;

        public GraphNode(int id)
        {
            this.nodeId = id;
        }
    }

    ArrayList<GraphNode> nodeList;

    public TopologicalIndegree()
    {
        nodeList = new ArrayList<GraphNode>();
    }

    public GraphNode addNode(int id)
    {
        GraphNode node = new GraphNode(id);
        nodeList.add(0, node);

        return nodeList.get(0);
    }

    // adding edge from id1 to id2
    // if either of these does not exist then create and add
    public void addEdge(int id1, int id2)
    {
        // search for id1 and id2
        boolean node1Found = false, node2Found = false;
        GraphNode node1  = null, node2 = null;

        for (int i = 0; i < nodeList.size(); i++)
        {
            if (nodeList.get(i).nodeId == id1)
            {
                node1Found = true;
                node1 = nodeList.get(i);
            }
            if (nodeList.get(i).nodeId == id2)
            {
                node2Found = true;
                node2 = nodeList.get(i);
            }
            if (node1Found && node2Found) break;
        }


        if (!node1Found)
        {
            node1 = this.addNode(id1);
        }

        if (!node2Found)
        {
            node2 = this.addNode(id2);
        }

        GraphNode temp = new GraphNode(id2);
        temp.next = node1.next;
        node1.next = temp;

        return;
    }


    public GraphNode getNode(int id)
    {
        for (int i = 0; i < nodeList.size(); i++)
        {
            if (id == nodeList.get(i).nodeId)
            {
                return nodeList.get(i);
            }

        }

        return null;
    }


    public void printTopoSortedNodes()
    {
        Hashtable inDegrees = new Hashtable <Integer, Integer>();
        ArrayList<GraphNode> zeroDegreeList  = new ArrayList<GraphNode>();
        ArrayList<GraphNode> nodes = this.nodeList;

        // this for loop counts the in-degrees for all nodes.
        // adds nodes having in-degree > 0 to in-Degrees hashtable
        for (int i = 0; i < nodes.size(); i++)
        {
            GraphNode temp = nodes.get(i);
            temp  = temp.next;
            while (temp != null)
            {
                int count = (inDegrees.get(temp.nodeId) == null) ? 0 : (int)inDegrees.get(temp.nodeId);
                inDegrees.put(temp.nodeId, count+1);
                temp = temp.next;
            }
        }

        // nodes which are not added to in-degree hashtable have in-degree = 0
        // create zeroDegree list of such nodes
        for (int i = 0; i < nodes.size(); i++)
        {
            GraphNode temp = nodes.get(i);

            // nodes with zero indegree would not be in the hashtable
            // since they are not in anyone's neighbor list
            if (inDegrees.get(temp.nodeId) == null)
            {
                zeroDegreeList.add(0, temp);
            }

        }

        // take out a node from zeroDegree list
        // print that node, remove all out-edges associated with it and update zeroDegree list.
        while (!zeroDegreeList.isEmpty())
        {
            GraphNode curr = zeroDegreeList.remove(0);

            // print topo sort: output!
            System.out.println(curr.nodeId);

            GraphNode temp = curr.next;

            // update the in-degree for all the neighbors of the current vertex
            while (temp != null)
            {
                int prevInDegree = (int) inDegrees.get(temp.nodeId);

                inDegrees.put(temp.nodeId, prevInDegree-1);
                if (prevInDegree == 1)
                {
                    zeroDegreeList.add(this.getNode(temp.nodeId));
                }
                temp = temp.next;
            }
        }

    }

    public static void main(String[] args) {

        // create a graph here
        TopologicalIndegree gr = new TopologicalIndegree();

        gr.addEdge(1,2);
        gr.addEdge(1,6);

        gr.addEdge(3,4);
        gr.addEdge(3,1);

        gr.addEdge(4,5);

        gr.addEdge(6,2);
        gr.addEdge(6,4);
        gr.addEdge(6,5);

        // print the vertices in topologically sorted order
        gr.printTopoSortedNodes();
    }
}

