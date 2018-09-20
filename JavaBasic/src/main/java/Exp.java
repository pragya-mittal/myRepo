//import com.oracle.javafx.jmx.json.JSONReader;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Exp {
    Node1 root;
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("/Users/pragya.mittal/workspace/inmobi/orgcommonArithmeticSolver/src/main/resources/val.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
            Exp exp = new Exp();
            exp.createTree(jsonObject);
            exp.printPreorder(exp.root);
            exp.simplifyTree();
            exp.printPreorder(exp.root);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Node1 addNode(Object obj) {

        JSONObject jObj = null;
        if (obj instanceof JSONObject) {
            jObj = (JSONObject)obj;
        } else {
            if (obj instanceof String) {
                return new Node1((String)obj);
            } else if (obj instanceof Long) {
                return new Node1(Integer.parseInt(obj.toString()));
            }
        }
        String op = (String)jObj.get("op");

        Object lhs = jObj.get("lhs");
        Object rhs =  jObj.get("rhs");
        Node1 node1 = new Node1(op);
        node1.left = addNode(lhs);
        node1.right = addNode(rhs);

        return node1;
    }
    public void createTree(JSONObject obj) {
        root = addNode(obj);
        System.out.println("sss");
    }


    public boolean fillAtLeft(Node1 node) {
        if (node == null) {
            return false;
        }

        if (node.data == "x") {
            return true;
        }

        node.xAtLeft = fillAtLeft(node.left);
        node.xAtLeft = !fillAtLeft(node.right);

        return node.xAtLeft;
    }

    public void simplifyTree() {
        fillAtLeft(root);
        Node1 node1 = root.left;
        while (node1.data != "x") {
            if (node1.xAtLeft) {
                root.left = node1.left;
            } else {
                root.left = node1.right;
            }

            Node1 nodeR = root.right;
            node1.right = node1.left;
            node1.left = root.right;
            root.right = node1;

            node1 = root.left;
        }
    }
    void printPreorder(Node1 node)
    {
        if (node == null)
            return;

        System.out.println(node.data + " " +  node.dataVal);
        printPreorder(node.left);
        printPreorder(node.right);
    }

}

class Node1 {
    Node1(String data) {
        this.data = data;
    }

    Node1(int data) {
        this.dataVal = data;
    }

    String data;
    int dataVal;
    boolean xAtLeft;
    Node1 left;
    Node1 right;
}