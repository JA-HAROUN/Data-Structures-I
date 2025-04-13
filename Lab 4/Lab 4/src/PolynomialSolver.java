public class PolynomialSolver implements IPolynomialSolver {

    // create the linked list of the polynomials
    // their elements are array of 2 element: 1. coefficient, 2. exponent
    public DoubleLinkedList A;
    public DoubleLinkedList B;
    public DoubleLinkedList C;
    public DoubleLinkedList R;


    // character to poly
    public DoubleLinkedList charToPoly(char poly){
        switch(poly){
            case 'A':
                return A;

            case 'B':
                return B;
                
            case 'C':
                return C;
                
            case 'R':
                return R;
                
            default:
                throw new IllegalArgumentException("Invalid polynomial name"); 
        }
    }

    // check if the polynomial is empty
    public boolean isPolyEmpty(char poly) {
        DoubleLinkedList polynomial = charToPoly(poly);

        return polynomial.isEmpty();
    }

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

        DoubleLinkedList polynomial = charToPoly(poly);

        // check if the polynomial is empty
        if (!isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is not empty");
        }

        // set the polynomial

        // set the coefficients and exponents
        for (int i = 0; i < terms[0].length; i++) {
            polynomial.add(new int[] { terms[0][i], terms[0].length - i - 1 });
        }

    }

    // Done [Maybe I'm not sure]
    @Override
    public String print(char poly) {
        /*
         * I should print it in a way like:
         * 27x^2+x-1
         */

        // check if the polynomial is valid
        DoubleLinkedList polynomial = charToPoly(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        // create the string to return
        String resultOutput = "";

        DoubleLinkedList coefficients = null;
        DoubleLinkedList exponents = null;

        // put the Polynomial data in the linked list

        // get the coefficients and exponents with for loop
        for (int i = 0; i < polynomial.size(); i++) {
            int[] term = (int[]) polynomial.get(i);
            coefficients.add(term[0]);
            exponents.add(term[1]);
        }
        // loop through the coefficients
        for (int i = 0; i < coefficients.size(); i++) {
            // get the coefficient
            int coefficient = (int) coefficients.get(i);
            // get the exponent
            int exponent = (int) exponents.get(i);

            // for loop to get the result
            for (int j = 0; j < coefficients.size(); j++) {
                // put the coefficient

                // check if the coefficient is positive
                if (coefficient > 0 && j != 0) {
                    resultOutput += "+";
                }

                // check if the coefficient is negative
                else if (coefficient < 0) {
                    resultOutput += "-";
                    coefficient = -coefficient;
                }

                // put the exponents
                // check if the exponent is 0
                if (exponent == 0) {
                    resultOutput += coefficient;
                }

                // check if the exponent is 1
                else if (exponent == 1) {
                    resultOutput += coefficient + "x";
                }

                // check if the exponent is greater than 1
                else {
                    resultOutput += coefficient + "x^" + exponent;
                }

            }
        }

        return resultOutput;
    }

    // Done
    @Override
    public void clearPolynomial(char poly) {
        DoubleLinkedList polynomial = charToPoly(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        // clear the polynomial
        polynomial.clear();

    }

    // Done
    @Override
    public float evaluatePolynomial(char poly, float value) {
        DoubleLinkedList polynomial = charToPoly(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        float result = 0;

        // get the polynomial result
        for (int i = 0; i < polynomial.size(); i++) {
            int[] term = (int[]) polynomial.get(i);
            result += term[0] * Math.pow(value, term[1]);
        }

        return result;
    }

    // in the add function after adding you put the result in 2d array which you use
    // to set the polynomial R then we use the print

    // Done
    @Override
    public int[][] add(char poly1, char poly2) {

        DoubleLinkedList polynomial1 = charToPoly(poly1);
        DoubleLinkedList polynomial2 = charToPoly(poly2);

        // check if the polynomials are empty
        if (polynomial1.isEmpty() || polynomial1.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // add the polynomials
        // we add by going from right to left (from trailer to header of the two linked
        // lists) and add them and put them in the 2d array and return it

        // create the result 2d array
        int[][] result = new int[2][Math.max(polynomial1.size(), polynomial2.size())];

        // we should loop with the bigger but stop adding when the smaller ends and
        // after that just put the rest of the bigger

        int maxSize = Math.max(polynomial1.size(), polynomial2.size());



        // edit the addition and subtraction



        // loop through the coefficients , it should start from the tail to the head
        for (int i = maxSize - 1; i >= 0; i--) {
            // get the coefficient
            int coefficient1 = 0;
            int coefficient2 = 0;

            // if i is bigger than the first polynomial size then keep it zero else take the value
            if (i < polynomial1.size()) {
                int[] term1 = (int[]) polynomial1.get(i);
                coefficient1 = term1[0];
            }

            // if i is bigger than the second polynomial size then keep it zero else take the value
            if (i < polynomial2.size()) {
                int[] term2 = (int[]) polynomial2.get(i);
                coefficient2 = term2[0];
            }

            // add the coefficients
            result[0][i] = coefficient1 + coefficient2;

            // as we go from the right we should put the exponent in the left in the result
            // array

            result[1][i] = maxSize - i - 1;
        }

        return result;
    }

    // Done
    @Override
    public int[][] subtract(char poly1, char poly2) {
        DoubleLinkedList polynomial1 = charToPoly(poly1);
        DoubleLinkedList polynomial2 = charToPoly(poly2);

        // check if the polynomials are empty
        if (polynomial1.isEmpty() || polynomial1.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // subtract the polynomials
        // we should loop with the bigger but stop subtracting when the smaller ends and
        // after that just put the rest of the bigger

        int maxSize = Math.max(polynomial1.size(), polynomial2.size());

        // create the result 2d array
        int[][] result = new int[2][maxSize];

        // loop through the coefficients
        for (int i = maxSize; i >= 0; i--) {
            // get the coefficient
            int coefficient1 = 0;
            int coefficient2 = 0;

            // check if the first polynomial is empty
            if (i < polynomial1.size()) {
                int[] term1 = (int[]) A.get(i);
                coefficient1 = term1[0];
            }

            // check if the second polynomial is empty
            if (i < polynomial2.size()) {
                int[] term2 = (int[]) B.get(i);
                coefficient2 = term2[0];
            }

            // subtract the coefficients
            result[0][i] = coefficient1 - coefficient2;

            // as we go from the right we should put the exponent in the left in the result
            // array
            result[1][i] = maxSize - i - 1;
        }

        return result;
    }

    // Done [Maybe I'm not sure]
    @Override
    public int[][] multiply(char poly1, char poly2) {

        // check if the polynomial is valid
        DoubleLinkedList polynomial1 = charToPoly(poly1);
        DoubleLinkedList polynomial2 = charToPoly(poly2);

        // check if the polynomials are empty
        if (polynomial1.isEmpty() || polynomial2.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // check the biggest exponent in the two polynomials
        int maxExponentPoly1 = polynomial1.size() - 1;
        int maxExponentPoly2 = polynomial2.size() - 1;

        // get the max exponent of the result
        int maxExponent = maxExponentPoly1 + maxExponentPoly2;
     
        // accumulation Array
        int[][] accumulation = new int[2][maxExponent + 1];
        int counterOfValidExponents = 0;

        // initialize the accumulation array with 0's in coefficients and -1 in exponents
        for (int i = 0; i < accumulation[0].length; i++) {
            accumulation[0][i] = 0;
            accumulation[1][i] = -1;
        }

        // build the accumulator multiplication

        // multiply the two polynomials
        for (int i = 0; i < polynomial1.size(); i++) {
            // looping through each element
            int[] term1 = (int[]) polynomial1.get(i);
            // get the coefficient and exponent
            int coefficient1 = term1[0];
            int exponent1 = term1[1];

            // loop through the second polynomial
            for (int j = 0; j < polynomial2.size(); j++) {
                // get the coefficient and exponent
                int[] term2 = (int[]) polynomial2.get(j);
                int coefficient2 = term2[0];
                int exponent2 = term2[1];

                // multiply the coefficients and add the exponents
                int coefficientResult = coefficient1 * coefficient2;
                int exponentResult = exponent1 + exponent2;

                // if exponent doesn't exist just initialize , else add
                if (accumulation[1][exponentResult] == -1) {
                    accumulation[0][exponentResult] = coefficientResult;
                    accumulation[1][exponentResult] = exponentResult;
                    counterOfValidExponents++;
                }
                else {
                    accumulation[0][exponentResult] += coefficientResult;
                    accumulation[1][exponentResult] = exponentResult;

                }
            }
        }
        

        // change accumulator to R
        int[][] result = new int[2][counterOfValidExponents];
        int index = 0;
        
        for (int i = 0; i < accumulation[0].length; i++) {
            // check if the exponent is valid
            if (accumulation[1][i] != -1) {
                result[0][index] = accumulation[0][i];
                result[1][index] = accumulation[1][i];
                index++;
            }
        }


        // clear R for print
        R.clear();
 
        return result;

    }
    
}