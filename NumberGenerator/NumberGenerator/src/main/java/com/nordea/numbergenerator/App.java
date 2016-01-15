package com.nordea.numbergenerator;

import com.nordea.pubsubapi.Publisher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String topic1 = "Fibnumbers";
    public static final String topic2 = "Primenumbers";

    public static void main( String[] args ) throws InterruptedException
    {
        int amount = 100;
        Generator gen = new Generator();
        Publisher pub = new Publisher();
        
        for(int i = 0; i < amount; i++)
        {
            pub.send(topic1, gen.getNextFibonacciNumber());
            pub.send(topic2, gen.getNextPrimeNumber());
            Thread.sleep(1000);
        }
    }
}
