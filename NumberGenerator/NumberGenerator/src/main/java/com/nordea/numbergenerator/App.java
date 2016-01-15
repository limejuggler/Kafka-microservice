package com.nordea.numbergenerator;
/**
 * Hello world!
 *
 */
public class App 
{
    public static final String topic1 = "Fibnumbers";
    public static final String topic2 = "Primenumbers";

    public static void main( String[] args )
    {
        int amount = 10;
        Generator gen = new Generator();
        
        System.out.println("The first 10 numbers of the Fiboncaci sequence are:\n");
        for(int i = 0; i < 10; i++)
        {
            System.out.println(gen.getNextFibonacciNumber());
        }
        
        System.out.println("The first 10 prime numbers are:\n");
        for(int i = 0; i < 20; i++)
        {
            System.out.println(gen.getPrimeNumbers());
        }
    }
}
