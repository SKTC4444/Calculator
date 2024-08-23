package calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private JTextField expressionTextField = new JTextField();
    private JPanel buttonPanel = new JPanel();
    private boolean calculationComplete = false;

    private static final String[] buttonText = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "(", "0", ")", "/",
            "C", "CE", "=", "^"
    };

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        expressionTextField.setPreferredSize(new Dimension(600, 50));
        expressionTextField.setFont(new Font("Courier", Font.BOLD, 24));
        expressionTextField.setHorizontalAlignment(JTextField.CENTER);
        expressionTextField.setEditable(false);
        add(expressionTextField, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5, 4));
        for (String s : buttonText) {
            JButton jb = new JButton(s);
            jb.setFont(new Font("Courier", Font.BOLD, 24));
            jb.addActionListener(this);
            buttonPanel.add(jb);
        }
        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEventObject) {
        String command = actionEventObject.getActionCommand();

        if (command.equals("C")) {
            expressionTextField.setText("");
            calculationComplete = false;
        } else if (command.equals("CE")) {
            clearLastEntry();
            calculationComplete = false;
        } else if (command.equals("=")) {
            evaluateExpression();
            calculationComplete = true;
        } else {
            if (calculationComplete) {
                expressionTextField.setText("");
                calculationComplete = false;
            }
            if ("+-*/^".contains(command) && "+-*/^".contains(lastCharacter())) {
                replaceLastOperator(command);
            } else {
                expressionTextField.setText(expressionTextField.getText() + command);
            }
        }
    }

    private void clearLastEntry() {
        String currentText = expressionTextField.getText();
        if (!currentText.isEmpty()) {
            String modifiedText = currentText.replaceAll("([\\d]+|[*+\\-/^])\\z", "");
            expressionTextField.setText(modifiedText);
        }
    }

    private void evaluateExpression() {
        Evaluator evaluator = new Evaluator();
        try {
            String result = String.valueOf(evaluator.evaluateExpression(expressionTextField.getText()));
            expressionTextField.setText(result);
        } catch (InvalidTokenException e) {
            expressionTextField.setText("Invalid Expression");
        } catch (ArithmeticException e) {
            expressionTextField.setText("Error");
        }
    }
    private String lastCharacter() {
        String text = expressionTextField.getText();
        return text.isEmpty() ? "" : text.substring(text.length() - 1);
    }

    private void replaceLastOperator(String newOperator) {
        String text = expressionTextField.getText();
        if (!text.isEmpty() && "+-*/^".contains(lastCharacter())) {
            text = text.substring(0, text.length() - 1);
        }
        expressionTextField.setText(text + newOperator);
    }

    public static void main(String[] args) {
        new EvaluatorUI();
    }
}
