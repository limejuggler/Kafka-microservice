package com.nordea.numbergenerator;

import com.nordea.pubsubapi.Publisher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String topic1 = "fib";
    public static final String topic2 = "prime";

    public static void main( String[] args ) throws InterruptedException
    {
        int amount = 1000;
        Generator gen = new Generator();
        Publisher pub = new Publisher();
        
        for(int i = 0; i < amount; i++)
        {
            if(0 == i % 40)
            {
                gen.reset();
                pub.send(topic1, "\nRESET\n" + gen.getNextFibonacciNumber());
            }
            else
            {
                pub.send(topic1, gen.getNextFibonacciNumber());
            }
            pub.send(topic2, gen.getNextPrimeNumber());
            Thread.sleep(1000);
        }
    }
}
