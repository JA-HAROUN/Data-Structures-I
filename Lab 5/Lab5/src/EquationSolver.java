public class EquationSolver implements IExpressionEvaluator {
    // stack for the operators and parentheses
    private Stack operators;

    // stack for the operands
    private Stack postfixStack;

    // final postfix expression
    private String postfixExpression;

    // priority of operators
    private char[] levelOne = {'+', '-'};
    private char[] levelTwo = {'*', '/'};
    private char[] levelThree = {'^'};
    private char[] levelFour = {'(', ')'};

    // function return the level of the operand
    private int getLevel(char operator) {
        for (int i = 0; i < levelOne.length; i++) {
            if (operator == levelOne[i]) {
                return 1;
            }
        }
        for (int i = 0; i < levelTwo.length; i++) {
            if (operator == levelTwo[i]) {
                return 2;
            }
        }
        for (int i = 0; i < levelThree.length; i++) {
            if (operator == levelThree[i]) {
                return 3;
            }
        }
        for (int i = 0; i < levelFour.length; i++) {
            if (operator == levelFour[i]) {
                return 4;
            }
        }
        return -1;
    }

    // Constructor
    // Initializes the stacks and postfix expression
    public EquationSolver() {
        this.operators = new Stack();
        this.postfixStack = new Stack();
        this.postfixExpression = "";
    }


    @Override
    public String infixToPostfix(String expression) {
        // Implementation of infix to postfix conversion
        
        // loop in the array
        for (int i = 0; i < expression.length(); i++) {

            char currentChar = expression.charAt(i);

            // if operand is found insert to postfix
            if (Character.isDigit(currentChar)) {
                postfixExpression += currentChar;
            }

            // else add to operators or parentheses
            else {
                // level of it
                int level = getLevel(currentChar);

                // level one
                if (level == 1) {

                    // if the top of operands is level 2 or 3
                    while (!operators.isEmpty() && (char) operators.peek() != '(' && getLevel((char) operators.peek()) >= level) {
                        postfixExpression += operators.pop();
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }

                // level two
                else if (level == 2) {
                    // if the top of operands is level 3
                    while (!operators.isEmpty() && (char) operators.peek() != '(' && getLevel((char) operators.peek()) >= level) {
                        postfixExpression += operators.pop();
                    }
                    
                }

                // level three
                else if (level == 3) {
                    // if the top of operands is level 3
                    while (!operators.isEmpty() && (char) operators.peek() != '(' && getLevel((char) operators.peek()) >= level) {
                        postfixExpression += operators.pop();
                    }
                }

                // level four
                else if (level == 4) {

                    // if left parentheses push to stack
                    if (currentChar == '(') {
                        operators.push(currentChar);
                    }

                    // else if right parentheses pop from stack until left parentheses
                    else if (currentChar == ')') {
                        while (!operators.isEmpty() && (char) operators.peek() != '(') {
                            postfixExpression += operators.pop();
                        }
                        operators.pop();
                    }

                }

                // else unknown character
                else {
                    throw new IllegalArgumentException("Unknown character: " + currentChar);
                }

            }


        }

        // add the remaining operators to the postfix expression
        while (!operators.isEmpty()) {
            char operator = (char) operators.pop();
            if (operator == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            postfixExpression += operator;
        }

        // return the postfix expression
        return postfixExpression;

    }

    @Override
    public int evaluate(String expression) {
        // Implementation of postfix evaluation

        // if postfixExpression is empty => either error or use infixToPostfix
        if (postfixExpression.isEmpty()) {
            throw new IllegalArgumentException("Postfix expression is empty");
        }


        // loop through the postfix expression
        for (int i = 0; i < postfixExpression.length(); i++) {

            char currentChar = postfixExpression.charAt(i);

            // if operand is found push to stack
            if (Character.isDigit(currentChar)) {
                postfixStack.push(currentChar);
            }

            // else pop two operands and apply the operator
            else {
                float operand2 = (float) postfixStack.pop();
                float operand1 = (float) postfixStack.pop();
                float result = 0;

                // apply the operator
                switch (currentChar) {
                    // plus
                    case '+':
                        result = operand1 + operand2;
                        break;
                    // minus
                    case '-':
                        result = operand1 - operand2;
                        break;
                    // multiply
                    case '*':
                        result = operand1 * operand2;
                        break;
                    // divide
                    case '/':
                        result = operand1 / operand2;
                        break;
                    // power
                    case '^':
                        result = (float) Math.pow(operand1, operand2);
                        break;
                    // unknown operator
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + currentChar);
                }

                // push the result to the stack
                postfixStack.push(result);
            }

        }


        // return the result
        if (!postfixStack.isEmpty()) {
            return (int) postfixStack.pop();
        } else {
            throw new IllegalArgumentException("Postfix stack is empty");
        }
    }
    
}
