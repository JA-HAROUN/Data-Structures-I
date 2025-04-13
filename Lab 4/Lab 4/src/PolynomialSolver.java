public class PolynomialSolver implements IPolynomialSolver {

    // create the linked list of the polynomials
    // their elements are array of 2 element: 1. coefficient, 2. exponent
    public DoubleLinkedList A;
    public DoubleLinkedList B;
    public DoubleLinkedList C;
    public DoubleLinkedList R;

/*     // function add with accumulator to R
    public void addToR(DoubleLinkedList list) {
        // check if the list is empty
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        // if R is empty then just add the list to R
        if (R.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                int[] term = (int[]) list.get(i);
                R.add(term);
            }
        }

        // else : AKA R has elements then accumulate
        // just add two elements if and only if the exponents are equal, there is just
        // one node with the needed exponent
        // the input list is ordered descending
        // i should loop in the R with these options
        // 1- if i found it just add
        // 2- if i the exponent doesnot exist i should put it in the right place as follow:
        // as the list is descending if it is less than in R skip
        // if it is equal add
        // if it is greater than in R then put it in the right place
        else {
            for (int i = 0; i < list.size(); i++) {
                int[] term = (int[]) list.get(i);
                // check if the exponent is already in R
                for (int j = 0; j < R.size(); j++) {
                    int[] termR = (int[]) R.get(j);
                    // check if the exponent is equal
                    if (term[1] == termR[1]) {
                        // add the coefficients
                        termR[0] += term[0];
                        break;
                    }
                    // if R is smaller then use the add(index, element) function
                    else if (term[1] > termR[1]) {
                        // add the term to the right place
                        R.add(j, term);
                        break;
                    }

                }
            }
        }

    }
 */
    // check if valid polynomial
    public void checkPolynomial(char poly) {
        if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
            throw new IllegalArgumentException("Invalid polynomial name");
        }
    }

    // check if the polynomial is empty
    public boolean isPolyEmpty(char poly) {
        if (poly == 'A') {
            return A.isEmpty();
        } else if (poly == 'B') {
            return B.isEmpty();
        } else if (poly == 'C') {
            return C.isEmpty();
        } else if (poly == 'R') {
            return R.isEmpty();
        } else {
            throw new IllegalArgumentException("Invalid polynomial name");
        }
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

        // check if the polynomial is valid
        checkPolynomial(poly);

        // check if the terms are valid
        if (terms == null) {
            throw new IllegalArgumentException("Invalid terms");
        }

        // check if the polynomial is empty
        if (!isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is not empty");
        }

        // set the polynomial

        if (poly == 'A') {
            // set the coefficients and exponents
            for (int i = 0; i < terms[0].length; i++) {
                A.add(new int[] { terms[0][i], terms[0].length - i - 1 });
            }
        }

        else if (poly == 'B') {
            // set the coefficients and exponents
            for (int i = 0; i < terms[0].length; i++) {
                B.add(new int[] { terms[0][i], terms[0].length - i - 1 });
            }
        }

        else if (poly == 'C') {
            // set the coefficients and exponents
            for (int i = 0; i < terms[0].length; i++) {
                C.add(new int[] { terms[0][i], terms[0].length - i - 1 });
            }

        }

        // R is just set by the programmer
        else if (poly == 'R') {
            // set the coefficients and exponents
            for (int i = 0; i < terms[0].length; i++) {
                R.add(new int[] { terms[0][i], terms[0].length - i - 1 });
            }

        } else {
            throw new IllegalArgumentException("Invalid polynomial name");
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
        checkPolynomial(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        // create the string to return
        String resultOutput = "";

        DoubleLinkedList coefficients = null;
        DoubleLinkedList exponents = null;

        // put the Polynomial data in the linked list
        if (poly == 'A') {
            // get the coefficients and exponents with for loop
            for (int i = 0; i < A.size(); i++) {
                int[] term = (int[]) A.get(i);
                coefficients.add(term[0]);
                exponents.add(term[1]);
            }
        } else if (poly == 'B') {
            // get the coefficients and exponents with for loop
            for (int i = 0; i < B.size(); i++) {
                int[] term = (int[]) B.get(i);
                coefficients.add(term[0]);
                exponents.add(term[1]);
            }
        } else if (poly == 'C') {
            // get the coefficients and exponents with for loop
            for (int i = 0; i < C.size(); i++) {
                int[] term = (int[]) C.get(i);
                coefficients.add(term[0]);
                exponents.add(term[1]);
            }
        } else if (poly == 'R') {
            // get the coefficients and exponents with for loop
            for (int i = 0; i < R.size(); i++) {
                int[] term = (int[]) R.get(i);
                coefficients.add(term[0]);
                exponents.add(term[1]);
            }
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
        // check if the polynomial is valid
        checkPolynomial(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        // clear the polynomial
        if (poly == 'A') {
            A.clear();
        } else if (poly == 'B') {
            B.clear();
        } else if (poly == 'C') {
            C.clear();
        } else if (poly == 'R') {
            R.clear();
        }
    }

    // Done
    @Override
    public float evaluatePolynomial(char poly, float value) {
        // check if the polynomial is valid
        checkPolynomial(poly);

        // check if the polynomial is empty
        if (isPolyEmpty(poly)) {
            throw new IllegalArgumentException("Polynomial is empty");
        }

        float result = 0;

        // get the polynomial result
        if (poly == 'A') {
            for (int i = 0; i < A.size(); i++) {
                int[] term = (int[]) A.get(i);
                result += term[0] * Math.pow(value, term[1]);
            }
        } else if (poly == 'B') {
            for (int i = 0; i < B.size(); i++) {
                int[] term = (int[]) B.get(i);
                result += term[0] * Math.pow(value, term[1]);
            }
        } else if (poly == 'C') {
            for (int i = 0; i < C.size(); i++) {
                int[] term = (int[]) C.get(i);
                result += term[0] * Math.pow(value, term[1]);
            }
        } else if (poly == 'R') {
            for (int i = 0; i < R.size(); i++) {
                int[] term = (int[]) R.get(i);
                result += term[0] * Math.pow(value, term[1]);
            }
        }

        return result;
    }

    // in the add function after adding you put the result in 2d array which you use
    // to set the polynomial R then we use the print

    // Done
    @Override
    public int[][] add(char poly1, char poly2) {
        // check if the polynomial is valid
        checkPolynomial(poly1);
        checkPolynomial(poly2);

        // check if the polynomials are empty
        if (A.isEmpty() || B.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // add the polynomials
        // we add by going from right to left (from trailer to header of the two linked
        // lists) and add them and put them in the 2d array and return it

        // create the result 2d array
        int[][] result = new int[2][Math.max(A.size(), B.size())];

        // we should loop with the bigger but stop adding when the smaller ends and
        // after that just put the rest of the bigger

        // loop through the coefficients
        for (int i = 0; i < Math.max(A.size(), B.size()); i++) {
            // get the coefficient
            int coefficient1 = 0;
            int coefficient2 = 0;

            // check if the first polynomial is empty
            if (i < A.size()) {
                int[] term1 = (int[]) A.get(i);
                coefficient1 = term1[0];
            }

            // check if the second polynomial is empty
            if (i < B.size()) {
                int[] term2 = (int[]) B.get(i);
                coefficient2 = term2[0];
            }

            // add the coefficients
            result[0][i] = coefficient1 + coefficient2;

            // as we go from the right we should put the exponent in the left in the result
            // array

            result[1][i] = Math.max(A.size() - i - 1, B.size() - i - 1);
        }

        return result;
    }

    // Done
    @Override
    public int[][] subtract(char poly1, char poly2) {
        // check if the polynomial is valid
        checkPolynomial(poly1);
        checkPolynomial(poly2);

        // check if the polynomials are empty
        if (A.isEmpty() || B.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // subtract the polynomials
        // we should loop with the bigger but stop subtracting when the smaller ends and
        // after that just put the rest of the bigger

        // create the result 2d array
        int[][] result = new int[2][Math.max(A.size(), B.size())];

        // loop through the coefficients
        for (int i = 0; i < Math.max(A.size(), B.size()); i++) {
            // get the coefficient
            int coefficient1 = 0;
            int coefficient2 = 0;

            // check if the first polynomial is empty
            if (i < A.size()) {
                int[] term1 = (int[]) A.get(i);
                coefficient1 = term1[0];
            }

            // check if the second polynomial is empty
            if (i < B.size()) {
                int[] term2 = (int[]) B.get(i);
                coefficient2 = term2[0];
            }

            // subtract the coefficients
            result[0][i] = coefficient1 - coefficient2;

            // as we go from the right we should put the exponent in the left in the result
            // array
            result[1][i] = Math.max(A.size() - i - 1, B.size() - i - 1);
        }

        return result;
    }

    // Done [Maybe I'm not sure]
    @Override
    public int[][] multiply(char poly1, char poly2) {

        // check if the polynomial is valid
        checkPolynomial(poly1);
        checkPolynomial(poly2);

        // check if the polynomials are empty
        if (A.isEmpty() || B.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // check the biggest exponent in the two polynomials
        int maxExponentPoly1 = A.size() - 1;
        int maxExponentPoly2 = B.size() - 1;

        // get the max exponent of the result
        int maxExponent = maxExponentPoly1 + maxExponentPoly2;
/* 
        // create the result 2d array
        int[][] result = new int[2][maxExponent + 1];

        // create the linked list which will pass to addToR
        DoubleLinkedList passToR = new DoubleLinkedList();
 */
        // do the multiplication as I didn't do it Ahmed
        /*
         * How did I multiply two polynomials?
         * 1- I multiply on term from the first polynomial with all the terms in the
         * second polynomial
         * 2- I add the result to the R array
         * 3- I repeat the process for all the terms in the first polynomial
         * 4- I put the R array in the result array
         * 5- I return the result array
         * 
         * once the adding is done put the R list in the result array and order it with
         * the fastest algorithm
         */
/* 
        // check which two polynomials to multiply
        // A * B
        if ((poly1 == 'A' && poly2 == 'B') || (poly1 == 'B' && poly2 == 'A')) {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];
                
                // loop through the second polynomial
                for (int j = 0; j < B.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) B.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to passToR
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }
        
        // A * C
        else if ((poly1 == 'A' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'A')) {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to passToR
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }

        // B * C
        else if ((poly1 == 'B' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'B')) {
            // multiply the two polynomials
            for (int i = 0; i < B.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) B.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to pass
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }

        // A * A
        else if (poly1 == 'A' && poly2 == 'A') {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < A.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) A.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to passToR
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }

        // B * B
        else if (poly1 == 'B' && poly2 == 'B') {
            // multiply the two polynomials
            for (int i = 0; i < B.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) B.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < B.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) B.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to passToR
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }

        // C * C
        else if (poly1 == 'C' && poly2 == 'C') {
            // multiply the two polynomials
            for (int i = 0; i < C.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) C.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
                    int coefficient2 = term2[0];
                    int exponent2 = term2[1];

                    // multiply the coefficients and add the exponents
                    int coefficientResult = coefficient1 * coefficient2;
                    int exponentResult = exponent1 + exponent2;
                    int [] termResult = new int[] { coefficientResult, exponentResult };

                    // add to passToR
                    passToR.add(termResult);
                }

                // accumulate in the R list
                addToR(passToR);

                // clear the passToR list
                passToR.clear();
            }
        }

        // put the R list in the result array
        for (int i = 0; i < R.size(); i++) {
            int[] term = (int[]) R.get(i);
            result[0][i] = term[0];
            result[1][i] = term[1];
        }
 */
        
        // accumulation Array
        int[][] accumulation = new int[2][maxExponent + 1];
        int counterOfValidExponents = 0;

        // initialize the accumulation array with 0's in coefficients and -1 in exponents
        for (int i = 0; i < accumulation[0].length; i++) {
            accumulation[0][i] = 0;
            accumulation[1][i] = -1;
        }

        // build the accumulator multiplication

        // A * B
        if ((poly1 == 'A' && poly2 == 'B') || (poly1 == 'B' && poly2 == 'A')) {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < B.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) B.get(j);
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
        }
        
        // A * C
        else if ((poly1 == 'A' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'A')) {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
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
        }
        
        // B * C
        else if ((poly1 == 'B' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'B')) {
            // multiply the two polynomials
            for (int i = 0; i < B.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) B.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
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
        }
        
        // A * A
        else if (poly1 == 'A' && poly2 == 'A') {
            // multiply the two polynomials
            for (int i = 0; i < A.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) A.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < A.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) A.get(j);
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
        }

        // B * B
        else if (poly1 == 'B' && poly2 == 'B') {
            // multiply the two polynomials
            for (int i = 0; i < B.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) B.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < B.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) B.get(j);
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
        }
        
        // C * C
        else if (poly1 == 'C' && poly2 == 'C') {
            // multiply the two polynomials
            for (int i = 0; i < C.size(); i++) {
                // looping through each element
                int[] term1 = (int[]) C.get(i);
                // get the coefficient and exponent
                int coefficient1 = term1[0];
                int exponent1 = term1[1];

                // loop through the second polynomial
                for (int j = 0; j < C.size(); j++) {
                    // get the coefficient and exponent
                    int[] term2 = (int[]) C.get(j);
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
        }


        // change accumulator to R
        int[][] result = new int[2][counterOfValidExponents];
        
        for (int i = 0, j = 0; i < accumulation[0].length; i++) {
            if (accumulation[1][i] != -1) {
                result[0][j] = accumulation[0][i];
                result[1][j] = accumulation[1][i];
                j++;
            }
        }
 
        return result;

    }


    
}