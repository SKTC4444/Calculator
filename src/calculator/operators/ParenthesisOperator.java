package calculator.operators;

import calculator.evaluator.Operand;

public class ParenthesisOperator extends Operator {

    public ParenthesisOperator() {}

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        throw new UnsupportedOperationException("Parentheses do not support direct execution.");
    }
}