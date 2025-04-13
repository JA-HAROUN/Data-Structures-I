class PolynomialSolver implements IPolynomialSolver {
    public DoubleLinkedList A = new DoubleLinkedList();
    public DoubleLinkedList B = new DoubleLinkedList();
    public DoubleLinkedList C = new DoubleLinkedList();
    public DoubleLinkedList R = new DoubleLinkedList();

    public DoubleLinkedList charToPoly(char poly) {
        switch (poly) {
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

    public boolean isPolyEmpty(char poly) {
        return charToPoly(poly).isEmpty();
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        DoubleLinkedList polynomial = charToPoly(poly);
        if (!polynomial.isEmpty()) {
            polynomial.clear();
        }

        for (int i = 0; i < terms[0].length; i++) {
            polynomial.add(new int[] { terms[0][i], terms[0].length - i - 1 });
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

        for (int i = 0; i < polynomial.size(); i++) {
            int[] term = (int[]) polynomial.get(i);
            int coefficient = term[0];
            int exponent = term[1];

            if (coefficient == 0)
                continue;

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

        return result.toString();
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
            result[0][maxSize - 1 - i] = coeff1 - coeff2;
            result[1][maxSize - 1 - i] = i;
        }

        return result;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        // Get the polynomial lists
        DoubleLinkedList polyList1 = charToPoly(poly1);
        DoubleLinkedList polyList2 = charToPoly(poly2);

        // Check for empty polynomials
        if (polyList1.isEmpty() || polyList2.isEmpty()) {
            throw new IllegalArgumentException("Polynomials are empty");
        }

        // Calculate maximum exponents based on polynomial sizes
        int size1 = polyList1.size();
        int size2 = polyList2.size();
        int maxExp = (size1 - 1) + (size2 - 1);

        // Create accumulation array for coefficients
        int[] coeffAccumulator = new int[maxExp + 1];

        // Multiply all term combinations
        for (int i = 0; i < size1; i++) {
            int[] term1 = (int[]) polyList1.get(i);
            int coeff1 = term1[0];
            int exp1 = size1 - 1 - i; // Calculate exponent based on position

            for (int j = 0; j < size2; j++) {
                int[] term2 = (int[]) polyList2.get(j);
                int coeff2 = term2[0];
                int exp2 = size2 - 1 - j; // Calculate exponent based on position

                int resultExp = exp1 + exp2;
                coeffAccumulator[resultExp] += coeff1 * coeff2;
            }
        }

        // Prepare final result array
        int[][] result = new int[2][maxExp + 1];
        for (int exp = maxExp; exp >= 0; exp--) {
            result[0][maxExp - exp] = coeffAccumulator[exp];
            result[1][maxExp - exp] = exp;
        }

        return result;

    }
}