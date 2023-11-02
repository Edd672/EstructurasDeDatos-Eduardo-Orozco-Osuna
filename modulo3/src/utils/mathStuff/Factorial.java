package utils.mathStuff;

public class Factorial {

    public static int getFactorialOfRecursive(int num){
        if(num == 1){
            return 1;
        }
        return num * getFactorialOfRecursive(num - 1);
    }

    public static int getFactorialOfIterative(int num){
        for(int i = num - 1; i > 1; --i){
            num *= i;
        }

        return num;
    }
}