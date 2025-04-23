public class Evaluator implements IExpressionEvaluator {
    // stack for the operators and parentheses
    private MyStack operators = new MyStack();

    // stack for the operands
    private MyStack postfixStack = new MyStack();

    // priority of operators
    private char[] levelOne = {'+', '-'};
    private char[] levelTwo = {'*', '/'};
    private char[] levelThree = {'^'};
    private char[] levelFour = {'(', ')'};
    private char[] variables = {'a', 'b', 'c'};

    // function return the level of the operand
    public int getLevel(char operator) {
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

    public boolean isVariable(char operator) {
        for (int i = 0; i < variables.length; i++) {
            if (operator == variables[i]) {
                return true;
            }
        }
        return false;
    }

    // Constructor
    // Initializes the stacks and postfix expression
    public Evaluator() {
        this.operators = new MyStack();
        this.postfixStack = new MyStack();
    }


    @Override
    public String infixToPostfix(String expression) {
        // Implementation of infix to postfix conversion
        
        // loop in the array
        for (int i = 0; i < expression.length(); i++) {

            char currentChar = expression.charAt(i);

            /*
             * All cases: 
             * 1. if it is a number -> check if 1 or 2 negatives before it
             * 1. a. if 1 negative -> push to stack [-number]
             * 1. b. if 2 negatives -> push to stack [number]
             * 
             * 2. if it is an operator -> check the precedence 
             * note : order of precedence is important
             * 
             * 3. you should push the last result (containing operands and operators) to the postfixStack
             * 
             */

            // if the current character is a digit
            if (isVariable(currentChar)) {
                
                // check the negative cases 
                if (operators.peek().equals('-')) {
                    operators.pop();
                    // check the case --a => a
                    if (operators.peek().equals('-')) {
                        operators.pop();
                        postfixStack.push(currentChar);
                    }
                    // check the case -a => a- (in the postfixExpression)
                    else {
                        postfixStack.push(currentChar);
                        postfixStack.push('-');
                    }
                }

                else {
                    postfixStack.push(currentChar);
                }

            }

            // if the current character is an operator
            int currentLevel = getLevel(currentChar);
            if (currentLevel == -1) {
                throw new IllegalArgumentException("");
            }
            else {
                // put the operator in the stack
                // if + or -
                if (currentLevel == 1){
                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) > currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }
                // if * or /
                else if (currentLevel == 2) {
                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) > currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }
                // if ^ operator
                else if (currentLevel == 3) {

                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) > currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }
                // if ( or )
                else if (currentLevel == 4) {
                    // if it is a left parenthesis, push it to the stack
                    if (currentChar == '(') {
                        operators.push(currentChar);
                    }
                    // if it is a right parenthesis, pop from the stack until the left parenthesis is found
                    else {
                        while (!operators.isEmpty() && (char) operators.peek() != '(') {
                            postfixStack.push((char) operators.pop());
                        }
                        // pop the left parenthesis
                        if (!operators.isEmpty()) {
                            operators.pop();
                        }
                    }
                }

            }

        }

        // add the remaining operators to the postfix expression
        while (!operators.isEmpty()) {
            char operator = (char) operators.pop();
            if (operator == '(') {
                throw new IllegalArgumentException("");
            }
            postfixStack.push(operator);
        }

        // return the postfix expression
        return postfixStack.toString();

    }

    @Override
    public int evaluate(String expression) {
        // expression is like 5,2,-3

        int[] variables = new int[3];

        // get the values
        int varIndex = 0;
        while (varIndex < 3) {
            // get the variable
            String var = expression.substring(0, expression.indexOf(','));
            expression = expression.substring(expression.indexOf(',') + 1);
            variables[varIndex] = Integer.parseInt(var);
            varIndex++;
        }

        MyStack reversedStack = new MyStack();
        // reverse the stack
        while (!postfixStack.isEmpty()) {
            reversedStack.push(postfixStack.pop());
        }

        MyStack runningStack = new MyStack();
        while (!reversedStack.isEmpty()){
            // get the element
            char element = (char) reversedStack.pop();
            // check if it is a number
            if (isVariable(element)) {
                if (element == 'a') {
                    runningStack.push(variables[0]);
                }
                else if (element == 'b') {
                    runningStack.push(variables[1]);
                }
                else if (element == 'c') {
                    runningStack.push(variables[2]);
                }
            }
            else {
                int first;
                int second;
                // do the operation
                switch (element) {
                    case '+':
                        if (runningStack.size() < 2) {
                            throw new IllegalArgumentException("");
                        }
                        first = (int) runningStack.pop();
                        second = (int) runningStack.pop();
                        runningStack.push(first + second);
                        break;
                    case '-':
                        if (runningStack.size() == 2) {
                            first = (int) runningStack.pop();
                            second = (int) runningStack.pop();
                            runningStack.push(first - second);
                        }
                        else if (runningStack.size() == 1) {
                            first = (int) runningStack.pop();
                            runningStack.push(-first);
                        }
                        else {
                            throw new IllegalArgumentException("");
                        }
                        break;
                    case '*':
                        if (runningStack.size() < 2) {
                            throw new IllegalArgumentException("");
                        }
                        first = (int) runningStack.pop();
                        second = (int) runningStack.pop();
                        runningStack.push(first * second);
                        break;
                    case '/':
                        if (runningStack.size() < 2) {
                            throw new IllegalArgumentException("");
                        }
                        first = (int) runningStack.pop();
                        second = (int) runningStack.pop();
                        runningStack.push(first / second);
                        break;
                    case '^':
                        if (runningStack.size() < 2) {
                            throw new IllegalArgumentException("");
                        }
                        first = (int) runningStack.pop();
                        second = (int) runningStack.pop();
                        runningStack.push((int) Math.pow(first, second));
                        break;
                }
            }

            
        }
        // then remains just one element
        if (runningStack.size() == 1) {
            return (int) runningStack.pop();
        }
        else {
            throw new IllegalArgumentException("");
        }
    }
    
}
