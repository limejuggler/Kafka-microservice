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
        int amount = 100;
        Generator gen = new Generator();
        
        System.out.println("The first " + amount + " numbers of the Fiboncaci sequence are:\n");
        for(int i = 0; i < amount; i++)
        {
            System.out.println(gen.getNextFibonacciNumber());
        }
        
        System.out.println("The first " + amount + " prime numbers are:\n");
        for(int i = 0; i < amount; i++)
        {
            System.out.println(gen.getPrimeNumbers());
        }
    }
}
