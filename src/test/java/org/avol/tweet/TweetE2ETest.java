package org.avol.tweet;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.avol.tweet.api.Tweet;
import org.avol.tweet.api.TweetResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Durga on 9/14/2015.
 *
 * TweetE2ETest class implementation using Dropwizard's client libraries.
 *
 * ClassRule starts the server with specified yml configuration.
 */
public class TweetE2ETest {

    @ClassRule
    public static final DropwizardAppRule<TweetConfiguration> APP_RULE = new DropwizardAppRule<TweetConfiguration>(
      TweetService.class, resourceFilePath("tweet.yml"));

    private Client client = null;

    private String baseURL = "http://localhost:%d/tweet";

    @Before
    public void setUp() {
        client =  new JerseyClientBuilder(APP_RULE.getEnvironment()).build("TestClient");
    }

    @Test
    public void testServiceHealth(){
        Response rs = client.target(String.format("http://localhost:%d/healthcheck",
                APP_RULE.getAdminPort())).request().get();
        assertTrue(rs.readEntity(String.class).contains("true"));
        assertEquals(Response.Status.OK.getStatusCode(), rs.getStatus());

    }

    @Test
    public void testTweetPost() {
        //Post Tweet
        TweetResponse tweetResponse = client.target(String.format(baseURL, APP_RULE.getLocalPort())).request()
                .post(Entity.json(getTweet("TweetPost test")), TweetResponse.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), tweetResponse.getStatusCode());

        //Get List of Tweets
        tweetResponse = client.target(String.format(baseURL + "/list", APP_RULE.getLocalPort())).request().get(TweetResponse.class);
        assertEquals(Response.Status.OK.getStatusCode(), tweetResponse.getStatusCode());
        assertTrue(tweetResponse.getTweets().size() > 0);
    }

    @Test
    public void testTweetPostGetDelete() {
        //Post Tweet.
        TweetResponse tweetResponse = client.target(String.format(baseURL, APP_RULE.getLocalPort())).request()
                .post(Entity.json(getTweet("TweetPostForGet")), TweetResponse.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), tweetResponse.getStatusCode());

        int tweetId = tweetResponse.getTweets().get(0).getId();
        //Get Tweet.
        tweetResponse = client.target(String.format(baseURL + "/%d", APP_RULE.getLocalPort(),tweetId))
                .request()
                .get(TweetResponse.class);
        assertEquals(Response.Status.OK.getStatusCode(), tweetResponse.getStatusCode());
        assertEquals("TweetPostForGet", tweetResponse.getTweets().get(0).getMessage());
        //Delete Tweet.
        tweetResponse = client.target(String.format(baseURL + "/%d", APP_RULE.getLocalPort(),tweetId))
                 .request()
                .delete(TweetResponse.class);

        assertEquals(Response.Status.OK.getStatusCode(), tweetResponse.getStatusCode());

        //Get Tweet.
        tweetResponse = client.target(String.format(baseURL + "/%d", APP_RULE.getLocalPort(),tweetId))
                .request()
                .get(TweetResponse.class);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), tweetResponse.getStatusCode());
        assertNull(tweetResponse.getTweets());
    }

    private Tweet getTweet(String message) {
        Tweet tweet = new Tweet();
        tweet.setMessage(message);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tweet.setDate(sdf.format(new Date()).toString());
        return tweet;
    }

    @After
    public void tearDown() {
        client.close();
    }
}
