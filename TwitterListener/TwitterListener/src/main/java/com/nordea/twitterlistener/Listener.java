/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.twitterlistener;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author Emil
 */
public class Listener 
{
    public ArrayList<String> listenForTag() 
    {
        ArrayList<String> tweetList = new ArrayList<String>();
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query("@NordeaMarkets");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) 
                {
                    tweetList.add(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        
        return tweetList;
    }
}
