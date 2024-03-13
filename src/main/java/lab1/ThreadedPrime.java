package lab1;

import java.util.*;

public class ThreadedPrime {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ThreadedPrime threadedPrime = new ThreadedPrime();
        List<Integer> primes = threadedPrime.getListOfPrimes(0,null);
        threadedPrime.printListOfPrimes(primes);
    }

    private List<Integer> getListOfPrimes(int numberOfThreads, Integer maxNumber) {

        return null;
    }

    private void printListOfPrimes(List<Integer> primes) {

    }}