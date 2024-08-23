package calculator.operators;

import calculator.evaluator.Operand;

public class MultiplyOperator extends Operator {
    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int multiResult = operandOne.getValue() * operandTwo.getValue();
        return new Operand(multiResult);
    }
}