package calculator.operators;

import calculator.evaluator.Operand;

public class SubtractOperator extends Operator {
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int subResult = operandOne.getValue() - operandTwo.getValue();
        return new Operand(subResult);
    }
}
