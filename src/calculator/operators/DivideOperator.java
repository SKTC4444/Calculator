package calculator.operators;

import calculator.evaluator.Operand;

public class DivideOperator extends Operator {
    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        if (operandTwo.getValue() == 0) {
            throw new ArithmeticException("Division by zero");
        }

        int result = operandOne.getValue() / operandTwo.getValue();
        return new Operand(result);
    }

    @Override
    public int priority() {
        return 2;
    }
}