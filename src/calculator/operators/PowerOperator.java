package calculator.operators;
import calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int base = operandOne.getValue();
        int exponent = operandTwo.getValue();
        int powResult = (int) Math.pow(base, exponent);
        return new Operand(powResult);
    }
}