import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */
    public String infixToPostfix(String expression);

    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * 
     * @param expression postfix expression
     * @return the expression evaluated value
     */
    public int evaluate(String expression);
}

public class Evaluator implements IExpressionEvaluator {

    // Totally Done
    class MyStack {

        // Single Linked List Node
        class SLLNode {

            private Object element;
            private SLLNode next;

            // Constructor
            public SLLNode(Object element) {
                this.element = element;
                this.next = null;
            }

            // Getters and Setters
            public Object getElement() {
                return element;
            }

            public void setElement(Object element) {
                this.element = element;
            }

            public SLLNode getNext() {
                return next;
            }

            public void setNext(SLLNode next) {
                this.next = next;
            }

        }

        // Single Linked List
        class SLL {

            private SLLNode header;
            private SLLNode trailer;
            private int size;

            // Constructor
            public SLL() {
                this.header = new SLLNode(null);
                this.trailer = new SLLNode(null);
                this.size = 0;
                this.header.setNext(trailer);
            }

            // Getters
            public SLLNode getHead() {
                return header.getNext();
            }

            // Setters
            public void setHead(SLLNode head) {
                this.header.setNext(head);
            }

            public SLLNode getTrailer() {
                return trailer;
            }

            // Add a new node at the head of the list
            public void addFirst(Object element) {
                SLLNode newNode = new SLLNode(element);
                newNode.setNext(header.getNext());
                setHead(newNode);
                size++;
            }

            // remove the first node
            public Object removeFirst() {
                if (size == 0) {
                    throw new IllegalStateException("List is empty");
                }
                SLLNode firstNode = header.getNext();
                header.setNext(firstNode.getNext());
                size--;
                Object element = firstNode.getElement();
                firstNode.setNext(null);
                firstNode.setElement(null);
                return element;
            }

            // Get the size of the list
            public int getSize() {
                return size;
            }

            public void clear() {
                header.setNext(trailer);
                size = 0;
            }

        }

        private SLL stackList;

        // Constructor
        public MyStack() {
            this.stackList = new SLL();
        }

        public void push(Object element) {
            if (element == null) {
                throw new IllegalArgumentException("Element cannot be null");
            }
            stackList.addFirst(element);
        }

        public Object pop() {
            if (stackList.getSize() == 0) {
                throw new IllegalStateException("Stack Underflow");
            }
            Object element = stackList.getHead().getElement();
            stackList.removeFirst();
            return element;
        }

        public Object peek() {
            if (stackList.getSize() == 0) {
                return null;
            }

            return stackList.getHead().getElement();
        }

        public boolean isEmpty() {
            return stackList.getSize() == 0;
        }

        public int size() {
            return stackList.getSize();
        }

        public void clear() {
            stackList.clear();
        }

        // String to stack
        public MyStack stringToStack(String stackString) {
            MyStack stack = new MyStack();

            for (int i = stackString.length() - 1; i >= 0; i--) {
                stack.push(stackString.charAt(i));
            }

            return stack;
        }

        // Stack to string
        public String stackToString() {
            // 1. reverse the stack to make the top on right
            MyStack reversedStack = new MyStack();
            while (stackList.getSize() != 0) {
                reversedStack.push(stackList.removeFirst());
            }

            // 2. convert the stack to string
            StringBuilder sb = new StringBuilder();
            while (!reversedStack.isEmpty()) {
                Object element = reversedStack.pop();
                // push back to get stackList as normal
                stackList.addFirst(element);
                sb.append(element);
            }

            return sb.toString();
        }

    }

    // stack for the operators and parentheses
    private MyStack operators;

    // stack for the operands
    private MyStack postfixStack;

    // priority of operators
    private char[] levelOne = { '+', '-' };
    private char[] levelTwo = { '*', '/' };
    private char[] levelThree = { '^' };
    private char[] levelFour = { '(', ')' };
    private char[] variables = { 'a', 'b', 'c' };

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

            /*
             * All cases:
             * 1. if it is a number -> check if 1 or 2 negatives before it
             * 1. a. if 1 negative -> push to stack [-number]
             * 1. b. if 2 negatives -> push to stack [number]
             *
             * 2. if it is an operator -> check the precedence
             * note : order of precedence is important
             *
             * 3. you should push the last result (containing operands and operators) to the
             * postfixStack
             *
             */

            char currentChar = expression.charAt(i);

            // if Variable
            if (isVariable(currentChar)) {

                // check the negative cases
                if (!operators.isEmpty() && operators.peek().equals('-')) {
                    operators.pop();

                    // check the case --a => a
                    if (!operators.isEmpty() && operators.peek().equals('-')) {
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
                throw new IllegalArgumentException("invalid operator");
            }

            else {
                // put the operator in the stack

                // if ( or )
                if (currentLevel == 4) {

                    // if it is a left parenthesis, push it to the stack
                    if (currentChar == '(') {
                        operators.push(currentChar);
                    }

                    // if it is a right parenthesis, pop from the stack until the left parenthesis
                    // is found
                    else {

                        while (!operators.isEmpty() && (char) operators.peek() != '(') {
                            postfixStack.push(operators.pop());
                        }

                        // pop the left parenthesis
                        operators.pop();

                    }

                }

                // if ^ operator , note: there's nothing higher than it
                else if (currentLevel == 3) {

                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) >= currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }
                    

                    operators.push(currentChar);

                }

                // if * or /
                else if (currentLevel == 2) {
                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) >= currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }

                // if + or -
                else if (currentLevel == 1) {
                    // if higher level is down, pop the stack
                    while (!operators.isEmpty() && getLevel((char) operators.peek()) >= currentLevel) {
                        char operator = (char) operators.pop();
                        postfixStack.push(operator);
                    }

                    // push the operator to the stack
                    operators.push(currentChar);

                }

            }

        }

        // add the remaining operators to the postfix expression
        while (!operators.isEmpty()) {
            char operator = (char) operators.pop();
            if (operator == '(') {
                throw new IllegalArgumentException("Open bracket left");
            }
            postfixStack.push(operator);
        }

        // return the postfix expression
        return postfixStack.stackToString();

    }

    @Override
    public int evaluate(String expression) {
        // expression is like => postfix,valueA,valueB,valueC
        String postfixExression = expression.substring(0, expression.indexOf(','));
        expression = expression.substring(expression.indexOf(',') + 1);

        // Get A,B, and C values
        int A = Integer.parseInt(expression.substring(0, expression.indexOf(',')));
        expression = expression.substring(expression.indexOf(',') + 1);
        int B = Integer.parseInt(expression.substring(0, expression.indexOf(',')));
        expression = expression.substring(expression.indexOf(',') + 1);
        int C = Integer.parseInt(expression.substring(0));

        postfixStack.clear();

        for (int i = 0; i < postfixExression.length(); i++) {
            char currentChar = postfixExression.charAt(i);

            // if it is a variable
            if (isVariable(currentChar)) {

                if (currentChar == 'a') {
                    postfixStack.push(A);
                } else if (currentChar == 'b') {
                    postfixStack.push(B);
                } else if (currentChar == 'c') {
                    postfixStack.push(C);
                }

            }

            // if it is operator
            else {

                int first, second;

                switch (currentChar) {

                    case '+': {
                        if (postfixStack.size() < 2) {
                            throw new IllegalArgumentException("Invalid expression");
                        }
                        second = (int) postfixStack.pop();
                        first = (int) postfixStack.pop();
                        postfixStack.push(first + second);
                        break;
                    }

                    case '-': {
                        if (postfixStack.size() == 2) {
                            second = (int) postfixStack.pop();
                            first = (int) postfixStack.pop();
                            postfixStack.push(first - second);
                        } else if (postfixStack.size() == 1) {
                            first = (int) postfixStack.pop();
                            postfixStack.push(-first);
                        } else {
                            throw new IllegalArgumentException("Invalid expression");
                        }
                        break;
                    }

                    case '*': {
                        if (postfixStack.size() < 2) {
                            throw new IllegalArgumentException("Invalid expression");
                        }
                        second = (int) postfixStack.pop();
                        first = (int) postfixStack.pop();
                        postfixStack.push(first * second);
                        break;
                    }

                    case '/': {
                        if (postfixStack.size() < 2) {
                            throw new IllegalArgumentException("Invalid expression");
                        }
                        second = (int) postfixStack.pop();
                        first = (int) postfixStack.pop();
                        if (first == 0) {
                            throw new IllegalArgumentException("Division by zero");
                        }
                        postfixStack.push(first / second);
                        break;
                    }

                    case '^': {
                        if (postfixStack.size() < 2) {
                            throw new IllegalArgumentException("Invalid expression");
                        }
                        second = (int) postfixStack.pop();
                        first = (int) postfixStack.pop();
                        postfixStack.push((int) Math.pow(first, second));
                        break;
                    }

                    default: {
                        throw new IllegalArgumentException("Invalid operatorInsideEvaluation");
                    }

                }

            }

        }

        if (postfixStack.size() == 1) {
            return (int) postfixStack.pop();
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }

    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            String infix = scanner.nextLine().trim();

            String strA = scanner.nextLine().trim();
            String strB = scanner.nextLine().trim();
            String strC = scanner.nextLine().trim();

            int A = Integer.parseInt(strA.substring(strA.indexOf('=') + 1));

            int B = Integer.parseInt(strB.substring(strB.indexOf('=') + 1));

            int C = Integer.parseInt(strC.substring(strC.indexOf('=') + 1));

            // Use the Evaluator class
            // to postfix
            Evaluator evaluator = new Evaluator();
            String postfix = evaluator.infixToPostfix(infix);
            System.out.println(postfix);

            // to evaluate

            String evaluationExpression = String.format("%s,%d,%d,%d",postfix,A, B, C);

            int result = evaluator.evaluate(evaluationExpression);
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}