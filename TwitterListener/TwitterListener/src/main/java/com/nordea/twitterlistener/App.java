package com.nordea.twitterlistener;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Listener l = new Listener();
        l.listenForTag();
        
        System.out.println( "Hello World!" );
    }
}
