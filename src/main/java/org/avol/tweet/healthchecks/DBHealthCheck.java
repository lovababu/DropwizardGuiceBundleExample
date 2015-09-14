package org.avol.tweet.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import java.sql.Connection;

/**
 * Created by Durga on 9/11/2015.
 */
public class DBHealthCheck extends HealthCheck {

    private Connection connection;

    public DBHealthCheck(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Result check() throws Exception {
        boolean flag =  connection.isValid(100);
        return flag ? Result.healthy() : Result.unhealthy("Connection not valid.");
    }
}
