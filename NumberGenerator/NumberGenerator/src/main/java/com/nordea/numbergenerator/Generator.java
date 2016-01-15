/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.numbergenerator;

/**
 *
 * @author Emil
 */
public class Generator 
{
    private int fibNext = 1, fibCur = 0, fibPrev = 0;
    private int nextPrime = 1;
    public String getNextFibonacciNumber()
    {
        if(0 == fibCur)
        {
            fibCur = fibNext;
            return "0";
        }
        if(1 == fibNext)
        {
            fibCur = fibNext;
            fibNext = fibCur + fibPrev;
            fibPrev = fibCur;
            return fibCur + "";
        }
        
        fibNext = fibCur + fibPrev;
        fibPrev = fibCur;
        fibCur = fibNext;
        
        return fibCur + "";
    }
    
    public void reset()
    {
        fibNext = 1;
        fibCur = 0;
        fibPrev = 0;
    }
    
    public String getNextPrimeNumber()
    {
        boolean potentialPrimeNumber;
        while(true)
        {
            nextPrime++;
            potentialPrimeNumber = true;
            
            if(1 == nextPrime || 2 == nextPrime || 3 == nextPrime)
            {
                break;
            }
            if(nextPrime % 2 == 0)
            {
                nextPrime++;
            }
            
            for(int i = 3; i < Math.floor(nextPrime / 2) && potentialPrimeNumber; i++)
            {
                if(0 == nextPrime % i)
                    potentialPrimeNumber = false;
            }
            if(potentialPrimeNumber)
                break;   
        }
        
        return nextPrime + "";
    }
}
