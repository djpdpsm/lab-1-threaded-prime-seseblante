package lab1;

import java.util.*;

public class ThreadedPrime {
    public static void main(String[] args) {

        // User input for number of threads and maximum number to check for being prime
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of threads: ");
        int numberOfThreads = scanner.nextInt();

        System.out.print("Enter the maximum number to check: ");
        int maxNumber = scanner.nextInt();

        // Creating a threaded prime object to print the list of primes
        ThreadedPrime threadedPrime = new ThreadedPrime();
        List<Integer> primes = threadedPrime.getListOfPrimes(numberOfThreads, maxNumber);
        threadedPrime.printListOfPrimes(primes);
    }

    private List<Integer> getListOfPrimes(int numberOfThreads, Integer maxNumber) {

        // Initialization
        List<Integer> primes = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        int numbersPerThread = maxNumber / numberOfThreads;

        // Output when the user inputs an integer less than 2
        if (maxNumber < 2) {
            System.out.println("There are no prime numbers less than 1.");
        }

        // Declares the division of tasks among the number of threads
        for (int i = 0; i < numberOfThreads; i++) {
            final int start = (i * numbersPerThread) + 1;
            int end;
            if (i == numberOfThreads - 1) {
                end = maxNumber;
            } else {
                end = (i + 1) * numbersPerThread;
            }

            // Logic for synchronizing adding prime numbers into the primes list
            Thread thread = new Thread(() -> {
                for (int j = start; j <= end; j++) {
                    if (isPrime(j)) {
                        synchronized (primes) {
                            primes.add(j);
                        }
                    }
                }
            });

            // Adding each thread into the list of threads
            threads.add(thread);
            thread.start();
        }

        // Iterates through each thread and waits for it to be added to the threads list
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(primes);

        return primes;
    }

    private void printListOfPrimes(List<Integer> primes) {
        // Formats the printing of prime numbers
        for (int i = 0; i < primes.size(); i++) {
            System.out.print(primes.get(i));
            if (i < primes.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    // Logic for checking if the number being checked currently is prime
    private boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}