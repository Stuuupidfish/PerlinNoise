package avaj;

/**
 * A class for math functions that are not in the standard Java Math class. 
 * 
 * @author Elaine Cui
 * @author Ranjit Paintal
 */
public final class Math2
{
    /**
     * Returns a random integer between min and max.
     * Inclusive of min and exclusive of max.
     * 
     * @param min minimum value (inclusive)
     * @param max maximum value (exclusive)
     * @return a random integer between min and max
     */
    public static int randomInt(int min, int max)
    {
        return (int)(Math.random() * (max - min)) + min;
    }

    /**
     * Rounds a double to a specified number of decimal places.
     * 
     * @param num the number to round
     * @param decimalPlaces the number of decimal places to round to
     * @return the rounded number
     */
    public static double round(double num, int decimalPlaces)
    {
        double multiplier = Math.pow(10, decimalPlaces);
        return Math.round(num * multiplier) / multiplier;
    }

    /**
     * Normalizes a vector (array of doubles) to have a length of 1.
     * 
     * @param vector the vector to normalize
     * @return the normalized vector
     */
    public static double[] normalize(double[] vector)
    {
        double length = 0;
        for (double component : vector)
        {
            length += component * component;
        }
        length = Math.sqrt(length);
        
        if (length == 0)
        {
            return new double[vector.length]; // Return a zero vector if the input is a zero vector
        }
        
        double[] normalizedVector = new double[vector.length];
        for (int i = 0; i < vector.length; i++)
        {
            normalizedVector[i] = vector[i] / length;
        }
        
        return normalizedVector;
    }

    /**
     * Calculates the dot product of two vectors (arrays of doubles).
     * 
     * @param vectorA the first vector
     * @param vectorB the second vector
     * @return the dot product of the two vectors
     * @throws IllegalArgumentException if the vectors are of different lengths
     */
    public static double[] dotProduct(double[] vectorA, double[] vectorB) throws IllegalArgumentException
    {
        if (vectorA.length != vectorB.length)
        {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }
        
        double[] result = new double[vectorA.length];
        for (int i = 0; i < vectorA.length; i++)
        {
            result[i] = vectorA[i] * vectorB[i];
        }
        
        return result;
    }

    /**
     * Calculates the magnitude (length) of a vector (array of doubles).
     * 
     * @param vector the vector to calculate the magnitude of
     * @return the magnitude of the vector
     */
    public static double magnitude(double[] vector)
    {
        double sum = 0;
        for (double component : vector)
        {
            sum += component * component;
        }
        return Math.sqrt(sum);
    }



    /**
     * Calculates factorial of integer n, where n &gt;= 0
     * 
     * @param n the number to calculate factorial of
     * @return factorial of n (n!)
     * @throws IllegalArgumentException if n &lt; 0
     */
    public static int factorial(int n) 
    {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial does not take input less than 0.");
        }
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    /**
     * Calculates the logarithm of a value to a specified base.
     * 
     * @param value the value to calculate the logarithm of
     * @param base the base of the logarithm
     * @return the logarithm of the value to the specified base
     */
    public static double log(double value, double base) 
    {
        return Math.log(value) / Math.log(base);
    }

    /**
     * Transposes a 2D array (matrix) of doubles. The input matrix is expected to be rectangular (all rows have the same number of columns).
     * 
     * @param matrix the 2D array to transpose
     * @param row the number of rows in the input matrix
     * @param col the number of columns in the input matrix
     * @return the transposed matrix
     * @throws IllegalArgumentException if the input matrix is not rectangular (jagged) or if row/col do not match the actual dimensions of the matrix
     */
    public static double[][] transpose(double[][] matrix, int row, int col) throws IllegalArgumentException
    {
        // Check for jagged (staggered) arrays
        for (int i = 0; i < row; i++) {
            if (matrix[i] == null || matrix[i].length != col) {
                throw new IllegalArgumentException("Input matrix is not rectangular at row " + i);
            }
        }
        double[][] transposed = new double[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
}
