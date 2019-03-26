package com.example.recursive;

/**
 * 斐波那契数列(递归求和)
 *
 * @author DINGJUN
 * @date 2019.03.25
 */
public class Feibonaqishulie {

    public static void main(String[] args) {
        Feibonaqishulie fb = new Feibonaqishulie();
        long l1 = System.currentTimeMillis();
        int sum = fb.sum(40);
        long l2 = System.currentTimeMillis();
        System.out.println("fib1:" + sum + " time:" + (l2 -l1));
        System.out.println("----");

        l1 = System.currentTimeMillis();
        int fib = fb.fib(40);
        l2 = System.currentTimeMillis();
        System.out.println("fib2:" + fib  + " time:" + (l2 -l1));
        System.out.println("----");

        l1 = System.currentTimeMillis();
        int i = fb.fib2(40);
        l2 = System.currentTimeMillis();
        System.out.println("fib3:" + i  + " time:" + (l2 -l1));

        /**
         * fib1:102334155 time:1052
         * ----
         * fib2:102334155 time:789
         * ----
         * fib3:102334155 time:0
         */
    }

    int sum(int number) {
        if(number <= 1) {
            return number;
        } else {
            return sum(number -1) + sum(number - 2);
        }
    }

    int fib(int n) {
        if(n == 1 || n == 2){
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }

    }

    int fib2(int n){
        if(n == 1 || n == 2) {
            return 1;
        }
        int f1 = 0;
        int f2 = 1;
        int sum = 0;

        for(int i = 0; i < n - 1; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }
}
