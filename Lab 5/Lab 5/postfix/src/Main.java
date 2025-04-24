import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class SLLNode {

    // parameters
    Object element;
    SLLNode next;

    // constructor
    public SLLNode(Object element) {
        this.element = element;
    }

    // methods
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

class SLL {

    SLLNode header;
    SLLNode trailer;
    int size;

    public SLL() {
        header = new SLLNode(null);
        trailer = new SLLNode(null);
        header.setNext(trailer);
        size = 0;
    }

    public void addFirst(Object element) {
        SLLNode newNode = new SLLNode(element);
        newNode.setNext(header.getNext());
        header.setNext(newNode);
        size++;
    }

    public Object removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        SLLNode firstNode = header.getNext();
        header.setNext(firstNode.getNext());
        size--;
        return firstNode.getElement();
    }

    public Object getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return header.getNext().getElement();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        header.setNext(trailer);
        size = 0;
    }

}

interface IStack {
    public Object pop();

    public Object peek();

    public void push(Object element);

    public boolean isEmpty();

    public int size();
}

class Stack implements IStack {

    SLL list;

    // constructor
    public Stack() {
        list = new SLL();
    }

    @Override
    public Object pop() {
        return list.removeFirst();
    }

    @Override
    public Object peek() {
        return list.getFirst();
    }

    @Override
    public void push(Object element) {
        list.addFirst(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    // more functions to use in implementation
    // print Stack from bottom to top
    public String toString() {
        SLL temp = new SLL();
        StringBuilder result = new StringBuilder();
        while (!list.isEmpty()) {
            Object element = list.removeFirst();
            temp.addFirst(element);
        }
        while (!temp.isEmpty()) {
            Object element = temp.removeFirst();
            list.addFirst(element);
            result.append(element);
        }
        return result.toString();
    }

    // clear the stack
    public void clear() {
        list.clear();
    }

}

interface IExpressionEvaluator {
    public String infixToPostfix(String expression);

    public int evaluate(String expression);
}

class Evaluator implements IExpressionEvaluator {

    Stack operators, postfix;
    int a, b, c;

    Evaluator() {
        operators = new Stack();
        postfix = new Stack();
        a = 0;
        b = 0;
        c = 0;
    }

    public void setVariables(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isVariable(char token) {
        return token == 'a' || token == 'b' || token == 'c';
    }

    public int precedence(char operator) {
        switch (operator) {
            

            case '(':
            case ')':
                return 5;


            case '^':
                return 3;

            case '*':
            case '/':
                return 2;

            case '+':
            case '-':
            case 'u':
                return 1;

            default:
                return 0;
        }
    }

    public boolean tripleMinus(String expression, int i) {
        if (i + 3 < expression.length()
                &&
                expression.charAt(i) == '-'
                &&
                expression.charAt(i + 1) == '-'
                &&
                expression.charAt(i + 2) == '-') {
            return true;
        } else {
            return false;
        }
    }

    public boolean doubleMinus(String expression, int i) {
        if (i + 2 < expression.length()
                &&
                expression.charAt(i) == '-'
                &&
                expression.charAt(i + 1) == '-') {
            return true;
        } else {
            return false;
        }
    }

    // passed the run code
    @Override
    public String infixToPostfix(String expression) {
        // clear the stacks
        postfix.clear();
        operators.clear();

        // filter the expression
        StringBuilder filteredExpression = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            // it should be as it is but just handle the -- and --- cases
            char token = expression.charAt(i);

            if (token == '-'){
                // triple
                if (tripleMinus(expression, i)) {
                    if (i == 0) {
                        if (
                            isVariable(expression.charAt(i + 3))
                            ||
                            expression.charAt(i + 3) == '('
                        ) {
                            // unary
                            filteredExpression.append('u');
                            i += 2;
                        }
                    }
                    else {
                        // minus
                        if (
                            (isVariable(expression.charAt(i - 1)) || expression.charAt(i - 1) == ')')
                            &&
                            (isVariable(expression.charAt(i + 3)) || expression.charAt(i + 3) == '(')
                        ) {
                            // just push the token and jump
                            filteredExpression.append('-');
                            i += 2;
                        }
                        else if (
                            precedence(expression.charAt(i - 1)) != 0
                            &&
                            (isVariable(expression.charAt(i + 3)) || expression.charAt(i + 3) == '(')
                        ) {
                            // just push the token and jump
                            filteredExpression.append('u');
                            i += 2;
                        }

                    }
                }
                // double
                else if (doubleMinus(expression, i)) {
                    if (i == 0) {
                        // just jump to the variable or (
                        i += 1;
                    }
                    else {
                        // if variable or ) before and after it, then put +
                        if (
                            (isVariable(expression.charAt(i - 1)) || expression.charAt(i - 1) == ')')
                            &&
                            (isVariable(expression.charAt(i + 2)) || expression.charAt(i + 2) == '(')
                        ) {
                            // just push the token and jump
                            filteredExpression.append('+');
                            i += 1;
                        }

                        else if (
                            precedence(expression.charAt(i - 1)) != 0
                            &&
                            (isVariable(expression.charAt(i + 2)) || expression.charAt(i + 2) == '(')
                        ) {
                            // just push the token and jump
                            i += 1;
                        }

                    }
                }
                // single
                else {
                    if (i == 0){
                        if (
                            i + 1 < expression.length()
                            && 
                            (
                                isVariable(expression.charAt(i + 1))
                                ||
                                expression.charAt(i + 1) == '('
                            )
                        ) {
                            // unary
                            filteredExpression.append('u');
                        }
                    }
                    else {
                        // before it variable or ) => it is minux leave it
                        if (
                            isVariable(expression.charAt(i - 1)) 
                            ||
                            expression.charAt(i - 1) == ')'
                            ) {
                            // just push the token
                            filteredExpression.append(token);

                        }
                        // before it operator => it is unary
                        else if (precedence(expression.charAt(i - 1)) != 0) {
                            // just push the token
                            filteredExpression.append('u');
                        }
                        // before it ( => it is unary
                        else if (expression.charAt(i - 1) == '(') {
                            // just push the token
                            filteredExpression.append('u');
                        }

                    }
                }
            
            }

            // check for leading +
            // check for -+ or *+ or /+ or ^+
            // just remove the +
            else if (token == '+') {
                // check for leading +
                if (i == 0) {
                    continue;
                }
                else {
                    filteredExpression.append(token);
                }

            }

            else {
                // just push the token
                filteredExpression.append(token);
            }

        }

        expression = filteredExpression.toString();

        char lastElement = expression.charAt(expression.length() - 1);
        char firstElement = expression.charAt(0);
        switch (lastElement) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '^':
            case '(':
            case 'u':
                throw new IllegalArgumentException();
        }
        switch (firstElement) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '^':
                throw new IllegalArgumentException();
        }

        // to postfix
        for (int i = 0; i < expression.length(); i++) {

            char token = expression.charAt(i);

            // case 1 => variable
            if (isVariable(token)) {
                if (!operators.isEmpty() && (char) operators.peek() == 'u') {
                    postfix.push(token);

                } else {
                    postfix.push(token);
                }

            }

            // case 2 => operator
            else if (precedence(token) != 0) {
                // check which level
                int level = precedence(token);
                
                // case 2 => ()
                if (level == 5) {
                    if (token == '(') {
                        operators.push(token);
                    } 

                    else if (token == ')') {
                        while (!operators.isEmpty()
                                &&
                                (char) operators.peek() != '('
                        )
                        {
                            postfix.push(operators.pop());
                        }
                        // remove the opening bracket
                        operators.pop();
                        
                    }
                }

                // case 2 => operator
                else {
                    while (!operators.isEmpty()
                            &&
                            precedence((char) operators.peek()) >= level
                            &&
                            (char) operators.peek() != '('
                    )
                    {
                        postfix.push(operators.pop());
                    }
                    operators.push(token);
                }

            }

            // case 3 => none of the above
            else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

        }

        // pop all the operators
        while (!operators.isEmpty()) {
            if (
                (char) operators.peek() == '('
                ||
                (char) operators.peek() == ')' 
                ) {
                throw new IllegalArgumentException();
            }
            postfix.push(operators.pop());
        }

        String result = postfix.toString();
        // if found u replace it with -
        result = result.replaceAll("u", "-");

        return result;
    }


    public void checkPostfixCount() {
        if (postfix.size() < 2) {
            throw new IllegalArgumentException("Not enough operands");
        }
    }

    @Override
    public int evaluate(String expression) {
        postfix.clear();
        String postfixExpression = expression;

        for (int i = 0; i < postfixExpression.length(); i++) {

            char token = postfixExpression.charAt(i);

            // case 1 => variable
            if (isVariable(token)) {
                if (token == 'a') {
                    postfix.push(a);
                } else if (token == 'b') {
                    postfix.push(b);
                } else if (token == 'c') {
                    postfix.push(c);
                }
            }

            // case 2 => operator
            else if (precedence(token) != 0) {

                int x, y;

                if (token == '+') {
                    // case 1 => x+y
                    checkPostfixCount();
                    y = (Integer) postfix.pop();
                    x = (Integer) postfix.pop();
                    postfix.push(x + y);
                } else if (token == '-') {
                    // case 2 => either -x or x-y
                    if (postfix.size() == 1) {
                        postfix.push(-1 * ((Integer) postfix.pop()));
                    } else {
                        checkPostfixCount();
                        y = (Integer) postfix.pop();
                        x = (Integer) postfix.pop();
                        postfix.push(x - y);
                    }
                } else if (token == '*') {
                    // case 3 => x*y
                    checkPostfixCount();
                    y = (Integer) postfix.pop();
                    x = (Integer) postfix.pop();
                    postfix.push(x * y);
                } else if (token == '/') {
                    // case 4 => x/y
                    checkPostfixCount();
                    y = (Integer) postfix.pop();
                    x = (Integer) postfix.pop();
                    if (y == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    postfix.push(x / y);
                } else if (token == '^') {
                    // case 5 => x^y
                    checkPostfixCount();
                    y = (Integer) postfix.pop();
                    x = (Integer) postfix.pop();
                    postfix.push((int) Math.pow(x, y));
                }

            }

            // case 3 => none of the above
            else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

        }

        int result = (int) postfix.pop();
        if (!postfix.isEmpty()) {
            throw new IllegalStateException("Invalid expression: stack not empty after evaluation");
        }
        return result;
    }

}

// Done
public class Main {

    // Done
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            Evaluator evaluator = new Evaluator();

            // read the expression
            String expression = scanner.nextLine();

            // read a, b, and c values
            String strA = scanner.nextLine().trim();
            String strB = scanner.nextLine().trim();
            String strC = scanner.nextLine().trim();

            // get the values
            int A = Integer.parseInt(strA.substring(strA.indexOf('=') + 1).trim());
            int B = Integer.parseInt(strB.substring(strB.indexOf('=') + 1).trim());
            int C = Integer.parseInt(strC.substring(strC.indexOf('=') + 1).trim());

            // set the values
            evaluator.setVariables(A, B, C);

            // output the postfix expression
            String postfix = evaluator.infixToPostfix(expression);
            System.out.println(postfix);

            // output the value
            int value = evaluator.evaluate(postfix);
            System.out.println(value);

        } catch (Exception e) {

            System.out.println("Error");

        }

    }

}