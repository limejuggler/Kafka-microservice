package com.nordea.twitterlistener;

import com.nordea.pubsubapi.Publisher;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String topic = "tweets";
    public static void main( String[] args ) throws InterruptedException
    {
        Publisher pub = new Publisher();
        Listener l = new Listener();
        ArrayList<String> tweets = l.listenForTag();
        for(int i = 0; i < tweets.size(); ++i)
        {
            String resultString = tweets.get(i).toString().replaceAll("/", "");
            pub.send(topic, resultString);           
            Thread.sleep(1000);
        }
    }
}
