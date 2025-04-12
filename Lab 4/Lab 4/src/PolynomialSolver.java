public class PolynomialSolver implements IPolynomialSolver {

    // create the linked lists for the polynomials
    public SingleLinkedList A;
    public SingleLinkedList B;
    public SingleLinkedList C;
    public SingleLinkedList R;

    // create the linked list for the coefficients
    public SingleLinkedList coefficients_A;
    public SingleLinkedList coefficients_B;
    public SingleLinkedList coefficients_C;
    public SingleLinkedList coefficients_R;

    // create the linked list for the exponents
    public SingleLinkedList exponents_A;
    public SingleLinkedList exponents_B;
    public SingleLinkedList exponents_C;
    public SingleLinkedList exponents_R;


    // Done
    @Override
    public void setPolynomial(char poly, int[][] terms) {

        /*  
         * terms is a 2D array with two rows
         * the first one is the coefficients
         * the second one is the exponents
         * we just get the coefficients
         * the exponents are descending order
         */

        // check if the polynomial is valid
        if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
            throw new IllegalArgumentException("Invalid polynomial name");
        }

        // check if the terms are valid
        if (terms == null || terms.length != 2 || terms[0].length != terms[1].length) {
            throw new IllegalArgumentException("Invalid terms");
        }

        // set the polynomial

        if (poly == 'A') {
            // check if coefficients are already set
            if (coefficients_A.head.next != coefficients_A.tail) {
                throw new IllegalArgumentException("");
            }

            // set the coefficients and exponents
            coefficients_A = new SingleLinkedList();
            for (int i = 0; i < terms[0].length; i++) {
                coefficients_A.add(terms[0][i]);
                exponents_A.add(terms[1][i]);
            }
        }

        else if (poly == 'B') {
            // check if coefficients are already set
            if (coefficients_B.head.next != coefficients_B.tail) {
                throw new IllegalArgumentException();
            }

            // set the coefficients and exponents
            coefficients_B = new SingleLinkedList();
            for (int i = 0; i < terms[0].length; i++) {
                coefficients_B.add(terms[0][i]);
                exponents_B.add(terms[1][i]);
            }
        }

        else if (poly == 'C') {
            // check if coefficients are already set
            if (coefficients_C.head.next != coefficients_C.tail) {
                throw new IllegalArgumentException();
            }

            // set the coefficients and exponents
            coefficients_C = new SingleLinkedList();
            for (int i = 0; i < terms[0].length; i++) {
                coefficients_C.add(terms[0][i]);
                exponents_C.add(terms[1][i]);
            }

        }

        // R is not edited
        else if (poly == 'R') {
            // set the coefficients
            coefficients_R = new SingleLinkedList();
            for (int i = 0; i < terms[0].length; i++) {
                coefficients_R.add(terms[0][i]);
            }

            // set the exponents
            exponents_R = new SingleLinkedList();
            for (int i = 0; i < terms[1].length; i++) {
                exponents_R.add(terms[0].length - i - 1);
            }
        }
            throw new IllegalArgumentException("Invalid polynomial name");
    }

    // Done
    @Override
    public String print(char poly) {
        /*
         * I should print it in a way like:
         * 27x^2+x-1
         */

        // check if the polynomial is valid
        if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
            throw new IllegalArgumentException("Invalid polynomial name");
        }
        // check if the polynomial is empty
        if (poly == 'A' && coefficients_A == null) {
            return "0";
        }
        else if (poly == 'B' && coefficients_B == null) {
            return "0";
        }
        else if (poly == 'C' && coefficients_C == null) {
            return "0";
        }
        else if (poly == 'R' && coefficients_R == null) {
            return "0";
        }

        // create the string to return
        String result = "";

        SingleLinkedList coefficients = null;
        SingleLinkedList exponents = null;

        // get the terms
        if (poly == 'A'){
            coefficients = coefficients_A;
            exponents = exponents_A;
        }
        else if (poly == 'B'){
            coefficients = coefficients_B;
            exponents = exponents_B;
        }
        else if (poly == 'C'){
            coefficients = coefficients_C;
            exponents = exponents_C;
        }
        else if (poly == 'R'){
            coefficients = coefficients_R;
            exponents = exponents_R;
        }

        // loop through the coefficients
        for (int i = 0; i < coefficients.size(); i++) {
            // get the coefficient
            int coefficient = (int) coefficients.get(i);
            // get the exponent
            int exponent = (int) exponents.get(i);

            // check if the coefficient is positive
            if (coefficient > 0 && i != 0) {
                result += "+";
            }
            // check if the coefficient is negative
            else if (coefficient < 0) {
                result += "-";
                coefficient = -coefficient;
            }

            // check if the exponent is 0
            if (exponent == 0) {
                result += coefficient;
            }
            // check if the exponent is 1
            else if (exponent == 1) {
                result += coefficient + "x";
            }
            // check if the exponent is greater than 1
            else {
                result += coefficient + "x^" + exponent;
            }
        }

        return result;
    }

    @Override
    public void clearPolynomial(char poly) {
        // check if the polynomial is valid
        if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
            throw new IllegalArgumentException("Invalid polynomial name");
        }

        // clear the polynomial
        if (poly == 'A') {
            coefficients_A.clear();
        }
        else if (poly == 'B') {
            coefficients_B.clear();
        }
        else if (poly == 'C') {
            coefficients_C.clear();
        }
        else if (poly == 'R') {
            coefficients_R.clear();
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    // in the add function after adding you put the result in 2d array which you use to set the polynomial R then we use the print
    @Override
    public int[][] add(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }

}
