package calculator.operators;

import calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {

    private static final Map<String, Operator> operators = new HashMap<>();


    static {
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        operators.put("(", new ParenthesisOperator());
        operators.put(")", new ParenthesisOperator());
    }

    public abstract int priority();

    public abstract Operand execute(Operand operandOne, Operand operandTwo);


    public static Operator getOperator(String token) {
        if (operators.containsKey(token)) {
            return operators.get(token);
        }
        throw new IllegalArgumentException("Invalid operator: " + token);
    }


    public static boolean check(String token) {
        return operators.containsKey(token);
    }
}