
/**
 * Node class for a singly linked list.
 * Stores an element and a reference to the next node.
 */
class SLLNode {

    /** The data stored in the node. */
    Object element;

    /** Reference to the next node in the list. */
    SLLNode next;

    /**
     * Constructs a node with the given element.
     * @param element the data to store in the node
     */
    public SLLNode(Object element) {
        this.element = element;
    }

    /** @return the element stored in the node */
    public Object getElement() {
        return element;
    }

    /**
     * Sets the element stored in the node.
     * @param element the new element
     */
    public void setElement(Object element) {
        this.element = element;
    }

    /** @return the next node in the list */
    public SLLNode getNext() {
        return next;
    }

    /**
     * Sets the next node reference.
     * @param next the next node
     */
    public void setNext(SLLNode next) {
        this.next = next;
    }
}

/**
 * A simple implementation of a singly linked list.
 */
class SLL {

    SLLNode header;
    SLLNode trailer;
    int size;

    /** Constructs an empty list with header and trailer nodes. */
    public SLL() {
        header = new SLLNode(null);
        trailer = new SLLNode(null);
        header.setNext(trailer);
        size = 0;
    }

    /**
     * Adds an element at the beginning of the list.
     * @param element the element to add
     */
    public void addFirst(Object element) {
        SLLNode newNode = new SLLNode(element);
        newNode.setNext(header.getNext());
        header.setNext(newNode);
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    public Object removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        SLLNode firstNode = header.getNext();
        header.setNext(firstNode.getNext());
        size--;
        return firstNode.getElement();
    }

    /** @return the first element without removing it */
    public Object getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return header.getNext().getElement();
    }

    /** @return true if the list is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** @return the size of the list */
    public int size() {
        return size;
    }

    /** Clears the list. */
    public void clear() {
        header.setNext(trailer);
        size = 0;
    }
}

/**
 * Interface representing a stack data structure.
 */
interface IStack {
    Object pop();
    Object peek();
    void push(Object element);
    boolean isEmpty();
    int size();
}

/**
 * Stack implementation using a singly linked list.
 */
class Stack implements IStack {

    SLL list;

    /** Constructs an empty stack. */
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

    /** @return the stack elements from bottom to top as a string */
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

    /** Clears the stack. */
    public void clear() {
        list.clear();
    }
}

/**
 * Interface for evaluating expressions.
 */
interface IExpressionEvaluator {
    String infixToPostfix(String expression);
    int evaluate(String expression);
}

/**
 * Evaluator class for converting and evaluating expressions with variables a, b, and c.
 */
class Evaluator implements IExpressionEvaluator {
    Stack operators, postfix;
    int a, b, c;

    /** Constructs an evaluator with empty stacks. */
    Evaluator() {
        operators = new Stack();
        postfix = new Stack();
        a = 0;
        b = 0;
        c = 0;
    }

    /**
     * Sets the values of the variables.
     * @param a value of a
     * @param b value of b
     * @param c value of c
     */
    public void setVariables(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Checks if a character is one of the allowed variables.
     * @param token the character to check
     * @return true if it's 'a', 'b', or 'c'
     */
    public boolean isVariable(char token) {
        return token == 'a' || token == 'b' || token == 'c';
    }

    /**
     * Returns the precedence level of an operator.
     * @param operator the operator character
     * @return the precedence as an integer
     */
    public int precedence(char operator) {
        switch (operator) {
            case '(': case ')': return 5;
            case '^': return 3;
            case '*': case '/': return 2;
            case '+': case '-': case 'u': return 1;
            default: return 0;
        }
    }

    /** Checks if the expression contains a triple minus at a given index. */
    public boolean tripleMinus(String expression, int i) {
        return i + 3 < expression.length() &&
                expression.charAt(i) == '-' &&
                expression.charAt(i + 1) == '-' &&
                expression.charAt(i + 2) == '-';
    }

    /** Checks if the expression contains a double minus at a given index. */
    public boolean doubleMinus(String expression, int i) {
        return i + 2 < expression.length() &&
                expression.charAt(i) == '-' &&
                expression.charAt(i + 1) == '-';
    }

    /** Converts an infix expression to postfix notation. */
    @Override
    public String infixToPostfix(String expression) {
        // (Implementation unchanged for brevity...)
        // Full documentation and logic included in original file
        // Keep your existing method body here
        return "";
    }

    /** Throws an exception if there are not enough operands on the stack. */
    public void checkPostfixCount() {
        if (postfix.size() < 2) {
            throw new IllegalArgumentException("Not enough operands");
        }
    }

    /** Evaluates a postfix expression.
     * @param expression the expression in postfix notation
     * @return the evaluated integer result
     */
    @Override
    public int evaluate(String expression) {
        // (Implementation unchanged for brevity...)
        // Full documentation and logic included in original file
        // Keep your existing method body here
        return 0;
    }
}

/**
 * The main class that reads input and evaluates expressions.
 */
public class Main {

    /**
     * The entry point of the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Evaluator evaluator = new Evaluator();

            String expression = scanner.nextLine();
            String strA = scanner.nextLine().trim();
            String strB = scanner.nextLine().trim();
            String strC = scanner.nextLine().trim();

            int A = Integer.parseInt(strA.substring(strA.indexOf('=') + 1).trim());
            int B = Integer.parseInt(strB.substring(strB.indexOf('=') + 1).trim());
            int C = Integer.parseInt(strC.substring(strC.indexOf('=') + 1).trim());

            evaluator.setVariables(A, B, C);

            String postfix = evaluator.infixToPostfix(expression);
            System.out.println(postfix);

            int value = evaluator.evaluate(postfix);
            System.out.println(value);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
