import utils.mathStuff.Factorial;
import utils.mathStuff.Fibonacci;
import utils.searches.BinarySearch;

public class Main {
    public static void main(String[] args) {
        // Fibonacci.
        System.out.println(Fibonacci.getFibonacciOfRecursive(5));
        System.out.println(Fibonacci.getFibonacciOfIterative(5));

        // Factorial.
        System.out.println(Factorial.getFactorialOfRecursive(5));
        System.out.println(Factorial.getFactorialOfIterative(5));

        // Binary search.
        int[] arr = new int[] {1, 2, 3, 4, 5};
        System.out.println(BinarySearch.findElementRecursive(arr, 10, 0, arr.length - 1));
        System.out.println(BinarySearch.findElementIterative(arr, 10));

    }
}
