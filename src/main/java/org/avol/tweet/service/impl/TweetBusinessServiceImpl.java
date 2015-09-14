package org.avol.tweet.service.impl;

import org.avol.tweet.api.Tweet;
import org.avol.tweet.dao.TweetDao;
import org.avol.tweet.service.TweetBusinessService;

import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Durga on 9/11/2015.
 */
public class TweetBusinessServiceImpl implements TweetBusinessService {

    @Inject
    private TweetDao tweetDao;

    @Override
    public Serializable create(Tweet tweet) throws Exception {
        return tweetDao.create(tweet);
    }

    @Override
    public Tweet find(int id) throws SQLException {
        return tweetDao.find(id);
    }

    @Override
    public List<Tweet> findAll() throws SQLException {
        return tweetDao.findAll();
    }

    @Override
    public Tweet update(Tweet tweet) throws Exception {
        return tweetDao.update(tweet);
    }

    @Override
    public void delete(int id) throws SQLException {
        tweetDao.delete(id);
    }
}
