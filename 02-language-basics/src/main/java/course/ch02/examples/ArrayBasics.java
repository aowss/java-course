package course.ch02.examples;

import java.util.Arrays;

/**
 * Demonstrates array creation, access, iteration, and common operations.
 */
public class ArrayBasics {

    public static void main(String[] args) {
        // Declaration and initialization
        int[] zeros = new int[5];
        int[] primes = {2, 3, 5, 7, 11};

        System.out.println("zeros:  " + Arrays.toString(zeros));
        System.out.println("primes: " + Arrays.toString(primes));
        System.out.println("primes.length = " + primes.length);

        // Indexed access
        System.out.println("\nFirst prime: " + primes[0]);
        System.out.println("Last prime:  " + primes[primes.length - 1]);

        // Enhanced for loop
        System.out.println("\nAll primes (enhanced for):");
        for (int p : primes) {
            System.out.println("  " + p);
        }

        // Classic for loop with index
        System.out.println("\nAll primes with index:");
        for (int i = 0; i < primes.length; i++) {
            System.out.println("  primes[" + i + "] = " + primes[i]);
        }

        // Sorting
        int[] unsorted = {5, 1, 4, 2, 3};
        Arrays.sort(unsorted);
        System.out.println("\nSorted: " + Arrays.toString(unsorted));

        // Copying
        int[] copy = Arrays.copyOf(primes, primes.length);
        System.out.println("Copy:   " + Arrays.toString(copy));
        System.out.println("Same reference? " + (primes == copy));
        System.out.println("Same contents?  " + Arrays.equals(primes, copy));

        // Out-of-bounds — uncomment to see the exception:
        // System.out.println(primes[10]);
    }
}
