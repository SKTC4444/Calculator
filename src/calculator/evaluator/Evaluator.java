package calculator.evaluator;

import calculator.operators.Operator;
import calculator.operators.ParenthesisOperator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private final Stack<Operand> operandStack;
  private final Stack<Operator> operatorStack;

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression) throws InvalidTokenException {
    if (expression == null || expression.trim().isEmpty()) {
      throw new IllegalArgumentException("Expression cannot be empty.");
    }


    expression = insertImplicitMultiplication(expression);

    StringTokenizer expressionTokenizer = new StringTokenizer(expression, " +/*-^()", true);

    while (expressionTokenizer.hasMoreTokens()) {
      String expressionToken = expressionTokenizer.nextToken().trim();

      if (expressionToken.isEmpty() || expressionToken.equals(" ")) {
        continue;
      }

      if (Operand.check(expressionToken)) {
        operandStack.push(new Operand(expressionToken));
      } else if (expressionToken.equals("(")) {
        operatorStack.push(new ParenthesisOperator());
      } else if (expressionToken.equals(")")) {

        while (!(operatorStack.peek() instanceof ParenthesisOperator)) {
          processOperator();
        }
        operatorStack.pop();
      } else if (Operator.check(expressionToken)) {
        Operator newOperator = Operator.getOperator(expressionToken);

        while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof ParenthesisOperator) && operatorStack.peek().priority() >= newOperator.priority()) {
          processOperator();
        }
        operatorStack.push(newOperator);
      } else {
        throw new InvalidTokenException("Invalid token: " + expressionToken);
      }
    }


    while (!operatorStack.isEmpty()) {
      processOperator();
    }


    if (!operandStack.isEmpty()) {
      return operandStack.pop().getValue();
    } else {
      throw new IllegalStateException("Evaluation error: Stack is empty after processing.");
    }
  }

  private void processOperator() {
    if (operatorStack.isEmpty() || operatorStack.peek() instanceof ParenthesisOperator) {
      return;
    }

    if (operandStack.size() < 2) {
      throw new IllegalStateException("Insufficient operands for the operation.");
    }

    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop();
    Operand operandOne = operandStack.pop();
    Operand result = operatorFromStack.execute(operandOne, operandTwo);
    operandStack.push(result);
  }

  private String insertImplicitMultiplication(String expression) {
    expression = expression.replaceAll("(\\d)\\(", "$1*(");
    expression = expression.replaceAll("\\)(\\d)", ")*$1");
    return expression;
  }

  public static void main(String[] args) throws InvalidTokenException {
    if (args.length == 1) {
      Evaluator e = new Evaluator();
      System.out.println(e.evaluateExpression(args[0]));
    } else {
      System.err.println("Invalid arguments or No expression given");
    }
  }
}