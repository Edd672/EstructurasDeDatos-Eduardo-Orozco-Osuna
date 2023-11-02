package utils.mathStuff;

import java.util.Scanner;

public class Fibonacci {

    public static int getFibonacciOfRecursive(int num){
        if(num == 0 || num == 1){
            return 1;
        }
        return getFibonacciOfRecursive(num - 1) + getFibonacciOfRecursive(num - 2);
    }

    public static int getFibonacciOfIterative(int num){
        int a = 1, b = 1, c = 0;

        for (int i=0; i<num; i++){
            c = a + b;
            a = b;
            b = c;
        }

        return a;
    }
}