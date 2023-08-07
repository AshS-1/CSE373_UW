package problems;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - Do not add any additional imports
 * - Do not create new `int[]` objects for `toString` or `rotateRight`
 */
public class ArrayProblems {

    /**
     * Returns a `String` representation of the input array.
     * Always starts with '[' and ends with ']'; elements are separated by ',' and a space.
     */
    public static String toString(int[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (i + 1 < array.length) {
                result += ", ";
            }
        }
        result += "]";
        return result;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns a new array containing the input array's elements in reversed order.
     * Does not modify the input array.
     */
    public static int[] reverse(int[] array) {
        int size = array.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int left = array[size - i - 1];
            int right = array[i];
            result[size - i - 1] = right;
            result[i] = left;
        }
        return result;
    }

    /**
     * Rotates the values in the array to the right.
     */
    public static void rotateRight(int[] array) {
        if (array.length != 0) {
            int ending = array[array.length -1];
            for (int i = array.length -1; i >= 1; i--) {
                array[i] = array[i-1];
            }
            array[0] = ending;
        }


        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
