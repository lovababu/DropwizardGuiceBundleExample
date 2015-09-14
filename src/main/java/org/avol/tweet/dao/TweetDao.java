package org.avol.tweet.dao;

import org.avol.tweet.api.Tweet;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Durga on 9/11/2015.
 */
public interface TweetDao {

    Serializable create(Tweet tweet) throws Exception;

    Tweet find(int id) throws SQLException;

    List<Tweet> findAll() throws SQLException;

    Tweet update(Tweet tweet) throws Exception;

    void delete(int id) throws SQLException;
}
