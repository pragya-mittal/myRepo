import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// https://www.geeksforgeeks.org/evaluation-of-expression-tree/
public class Expression {
    private static String parseJsonExpression(String jsonExpression) throws Exception {
        if (jsonExpression.split(",").length == 1 ) {
            return jsonExpression;
        }

        JSONParser parser = new JSONParser();
        Object object = parser.parse(jsonExpression);
        JSONObject jsonObject = (JSONObject) object;
        if (jsonObject.size() == 3) { // op, lhs, rhs
            String operand = (String) jsonObject.get("op");
            String lhs = jsonObject.get("lhs").toString();
            String rhs = jsonObject.get("rhs").toString();
            return "(" + parseJsonExpression(lhs) + ")" + operand + "(" + parseJsonExpression(rhs) + ")";
        } else {
            throw new RuntimeException ("Invalid json : " + jsonExpression);
        }
    }

    public static void main(String args[]) throws Exception {
        String expression = "{ \"op\": \"equal\", \"rhs\": \"21\", \"lhs\": { \"op\": \"add\", \"lhs\": \"1\", \"rhs\": { \"op\": \"multiply\", \"lhs\": \"x\", \"rhs\": \"10\" } } }";
        System.out.println(parseJsonExpression(expression));

    }
}