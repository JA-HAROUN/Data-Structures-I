import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    void add(int index, Object element);
    void add(Object element);
    Object get(int index);
    void set(int index, Object element);
    void clear();
    boolean isEmpty();
    void remove(int index);
    int size();
    ILinkedList sublist(int fromIndex, int toIndex);
    boolean contains(Object o);
}

class DoubleLinkedList implements ILinkedList {
    class DLnode {
        private Object element;
        private DLnode next;
        private DLnode prev;

        public DLnode() {
            element = null;
            next = null;
            prev = null;
        }

        public DLnode(Object e, DLnode n, DLnode p) {
            element = e;
            next = n;
            prev = p;
        }

        public void setElement(Object nodeContent) {
            this.element = nodeContent;
        }

        public Object getElement() {
            return this.element;
        }

        public void setnext(DLnode nodeNext) {
            this.next = nodeNext;
        }

        public DLnode getnext() {
            return this.next;
        }

        public void setprev(DLnode nodePrev) {
            this.prev = nodePrev;
        }

        public DLnode getprev() {
            return this.prev;
        }
    }

    private DLnode header;
    private DLnode trailer;

    public DoubleLinkedList() {
        header = new DLnode();
        trailer = new DLnode();
        header.setnext(trailer);
        trailer.setprev(header);
    }

    void boundsCheck(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isEmpty() {
        return header.getnext() == trailer && trailer.getprev() == header;
    }

    @Override
    public void clear() {
        header.setnext(trailer);
        trailer.setprev(header);
    }

    @Override
    public int size() {
        DLnode q = header.getnext();
        int size = 0;
        while (q != trailer) {
            size++;
            q = q.getnext();
        }
        return size;
    }

    @Override
    public boolean contains(Object o) {
        DLnode q = header.getnext();
        while (q != trailer) {
            if (q.getElement().equals(o)) {
                return true;
            }
            q = q.getnext();
        }
        return false;
    }

    @Override
    public void add(Object element) {
        DLnode addedNode = new DLnode();
        addedNode.setElement(element);

        DLnode q = trailer.getprev();
        addedNode.setprev(q);
        q.setnext(addedNode);
        addedNode.setnext(trailer);
        trailer.setprev(addedNode);
    }

    @Override
    public void add(int index, Object element) {
        DLnode addedNode = new DLnode();
        addedNode.setElement(element);

        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        DLnode w = q.getprev();
        w.setnext(addedNode);
        addedNode.setprev(w);
        addedNode.setnext(q);
        q.setprev(addedNode);
    }

    @Override
    public void remove(int index) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        DLnode n = q.getnext();
        DLnode p = q.getprev();
        p.setnext(n);
        n.setprev(p);
    }

    @Override
    public Object get(int index) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        return q.getElement();
    }

    @Override
    public void set(int index, Object element) {
        boundsCheck(index);

        DLnode q = header.getnext();
        int i = 0;
        while (i < index) {
            q = q.getnext();
            i++;
        }

        q.setElement(element);
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        boundsCheck(fromIndex);
        boundsCheck(toIndex);

        DLnode q = header.getnext();
        int i = 0;
        while (i < fromIndex) {
            q = q.getnext();
            i++;
        }

        DLnode w = header.getnext();
        i = 0;
        while (i < toIndex) {
            w = w.getnext();
            i++;
        }

        header.setnext(q);
        q.setprev(header);
        trailer.setprev(w);
        w.setnext(trailer);

        return this;
    }
}

interface IPolynomialSolver {
    void setPolynomial(char poly, int[][] terms);
    String print(char poly);
    void clearPolynomial(char poly);
    float evaluatePolynomial(char poly, float value);
    int[][] add(char poly1, char poly2);
    int[][] subtract(char poly1, char poly2);
    int[][] multiply(char poly1, char poly2);
}

class PolynomialSolver implements IPolynomialSolver {
    public DoubleLinkedList A = new DoubleLinkedList();
    public DoubleLinkedList B = new DoubleLinkedList();
    public DoubleLinkedList C = new DoubleLinkedList();
    public DoubleLinkedList R = new DoubleLinkedList();

    public DoubleLinkedList charToPoly(char poly) {
        switch(poly) {
            case 'A': return A;
            case 'B': return B;
            case 'C': return C;
            case 'R': return R;
            default: throw new IllegalArgumentException("Invalid polynomial name");
        }
    }

    public boolean isPolyEmpty(char poly) {
        return charToPoly(poly).isEmpty();
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        DoubleLinkedList polynomial = charToPoly(poly);
        if (!polynomial.isEmpty()) {
            throw new IllegalArgumentException("Polynomial is not empty");
        }

        // if not R
        if (poly != 'R') {
            for (int i = 0; i < terms[0].length; i++) {
                polynomial.add(new int[] { terms[0][i], terms[0].length - i - 1 });
            }
        }
        // else  (is R) take the exponents from the second row in terms
        else {
            for (int i = 0; i < terms[1].length; i++) {
                polynomial.add(new int[] { terms[0][i], terms[1][i] });
            }
        }
        
    }

    @Override
    public String print(char poly) {
        DoubleLinkedList polynomial = charToPoly(poly);
        if (polynomial.isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        boolean firstTerm = true;
        boolean allZero = true;

        for (int i = 0; i < polynomial.size(); i++) {
            int[] term = (int[]) polynomial.get(i);
            int coefficient = term[0];
            int exponent = term[1];

            if (coefficient == 0) continue;
            allZero = false;

            if (!firstTerm) {
                if (coefficient > 0) {
                    result.append("+");
                }
            }

            if (exponent == 0) {
                result.append(coefficient);
            } else {
                if (coefficient == 1 && exponent != 0) {
                    // Don't print 1 before x
                } else if (coefficient == -1 && exponent != 0) {
                    result.append("-");
                } else {
                    result.append(coefficient);
                }

                result.append("x");

                if (exponent != 1) {
                    result.append("^").append(exponent);
                }
            }

            firstTerm = false;
        }

        if (allZero) {
            return "0";
        }

        return result.length() == 0 ? "0" : result.toString();
    }


    @Override
    public void clearPolynomial(char poly) {
        DoubleLinkedList polynomial = charToPoly(poly);
        polynomial.clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        DoubleLinkedList polynomial = charToPoly(poly);
        if (polynomial.isEmpty()) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        float result = 0;
        for (int i = 0; i < polynomial.size(); i++) {
            int[] term = (int[]) polynomial.get(i);
            result += term[0] * Math.pow(value, term[1]);
        }
        return result;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        DoubleLinkedList p1 = charToPoly(poly1);
        DoubleLinkedList p2 = charToPoly(poly2);
        if (p1.isEmpty() || p2.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        int maxSize = Math.max(p1.size(), p2.size());
        int[][] result = new int[2][maxSize];

        for (int i = maxSize - 1; i >= 0; i--) {
            int coeff1 = 0, coeff2 = 0;
            if (i < p1.size()) {
                int[] term = (int[]) p1.get(p1.size() - 1 - i);
                coeff1 = term[0];
            }
            if (i < p2.size()) {
                int[] term = (int[]) p2.get(p2.size() - 1 - i);
                coeff2 = term[0];
            }
            result[0][maxSize - 1 - i] = coeff1 + coeff2;
            result[1][maxSize - 1 - i] = i;
        }

        return result;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
    DoubleLinkedList p1 = charToPoly(poly1);
    DoubleLinkedList p2 = charToPoly(poly2);
    
    if (p1.isEmpty() || p2.isEmpty()) {
        throw new IllegalArgumentException("Polynomials are empty");
    }

    // Find maximum exponent to determine result size
    int maxExp1 = ((int[])p1.get(0))[1]; // First term has highest exponent
    int maxExp2 = ((int[])p2.get(0))[1];
    int maxResultExp = Math.max(maxExp1, maxExp2);
    
    // Initialize result array with all possible exponents
    int[][] result = new int[2][maxResultExp + 1];
    
    // Initialize exponents in descending order
    for (int i = 0; i <= maxResultExp; i++) {
        result[1][i] = maxResultExp - i;
    }

    // Process first polynomial (add terms)
    for (int i = 0; i < p1.size(); i++) {
        int[] term = (int[]) p1.get(i);
        int exp = term[1];
        result[0][maxResultExp - exp] += term[0];
    }

    // Process second polynomial (subtract terms)
    for (int i = 0; i < p2.size(); i++) {
        int[] term = (int[]) p2.get(i);
        int exp = term[1];
        result[0][maxResultExp - exp] -= term[0];
    }

    return result;
}


    @Override
    public int[][] multiply(char poly1, char poly2) {
        DoubleLinkedList p1 = charToPoly(poly1);
        DoubleLinkedList p2 = charToPoly(poly2);
        
        if (p1.isEmpty() || p2.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }
    
        // Calculate maximum exponent based on polynomial sizes
        int maxExp = (p1.size() - 1) + (p2.size() - 1);
        int[] resultCoeffs = new int[maxExp + 1]; // Array to accumulate coefficients
    
        // Multiply all term combinations
        for (int i = 0; i < p1.size(); i++) {
            int[] term1 = (int[]) p1.get(i);
            int coeff1 = term1[0];
            int exp1 = p1.size() - 1 - i; // Calculate exponent from position
    
            for (int j = 0; j < p2.size(); j++) {
                int[] term2 = (int[]) p2.get(j);
                int coeff2 = term2[0];
                int exp2 = p2.size() - 1 - j; // Calculate exponent from position
    
                int productExp = exp1 + exp2;
                resultCoeffs[productExp] += coeff1 * coeff2;
            }
        }
    
        // Prepare the result array in descending exponent order
        int[][] result = new int[2][maxExp + 1];
        for (int exp = maxExp; exp >= 0; exp--) {
            result[0][maxExp - exp] = resultCoeffs[exp];
            result[1][maxExp - exp] = exp;
        }
    
        return result;
    }


}

public class Main {
    public static int[] readArray(String inputString) {
        inputString = inputString.substring(1, inputString.length() - 1);
        String[] values = inputString.split(",");
        int[] result = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i].trim());
        }

        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PolynomialSolver solver = new PolynomialSolver();

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                
                if (command.equals("set")) {
                    char poly = scanner.nextLine().charAt(0);
                    String termsStr = scanner.nextLine();
                    int[] coefficients = readArray(termsStr);
                    
                    int[][] terms = new int[2][coefficients.length];
                    for (int i = 0; i < coefficients.length; i++) {
                        terms[0][i] = coefficients[i];
                        terms[1][i] = coefficients.length - i - 1;
                    }
                    
                    solver.setPolynomial(poly, terms);
                } 
                else if (command.equals("print")) {
                    char poly = scanner.nextLine().charAt(0);
                    System.out.println(solver.print(poly));
                } 
                else if (command.equals("add")) {
                    char poly1 = scanner.nextLine().charAt(0);
                    char poly2 = scanner.nextLine().charAt(0);
                    int[][] result = solver.add(poly1, poly2);
                    solver.setPolynomial('R', result);
                    System.out.println(solver.print('R'));
                } 
                else if (command.equals("sub")) {
                    char poly1 = scanner.nextLine().charAt(0);
                    char poly2 = scanner.nextLine().charAt(0);
                    int[][] result = solver.subtract(poly1, poly2);
                    solver.setPolynomial('R', result);
                    System.out.println(solver.print('R'));
                } 
                else if (command.equals("mult")) {
                    char poly1 = scanner.nextLine().charAt(0);
                    char poly2 = scanner.nextLine().charAt(0);
                    int[][] result = solver.multiply(poly1, poly2);
                    solver.setPolynomial('R', result);
                    System.out.println(solver.print('R'));
                } 
                else if (command.equals("clear")) {
                    char poly = scanner.nextLine().charAt(0);
                    solver.clearPolynomial(poly);
                    System.out.println("[]");
                } 
                else if (command.equals("eval")) {
                    char poly = scanner.nextLine().charAt(0);
                    float value = Float.parseFloat(scanner.nextLine());
                    System.out.println((int)solver.evaluatePolynomial(poly, value));
                } 
                else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}