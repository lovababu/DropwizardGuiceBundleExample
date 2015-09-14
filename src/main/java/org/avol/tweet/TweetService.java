package org.avol.tweet;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.avol.tweet.conf.TweetModule;
import org.avol.tweet.filters.RequestTraxnIdFilter;
import org.avol.tweet.healthchecks.DBHealthCheck;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by Durga on 9/14/2015.
 *
 * Explains how to use simple dropwizard extension for integrating Guice via bundle.
 */
public class TweetService extends Application<TweetConfiguration> {

    private GuiceBundle<TweetConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new TweetService().run(args);
    }

    @Override
    public String getName() {
        return "avol-tweet";
    }

    @Override
    public void initialize(Bootstrap<TweetConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.< TweetConfiguration>newBuilder()
                .addModule(new TweetModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(TweetConfiguration.class)
                .build(Stage.DEVELOPMENT); // must be development OR Use Governator's @LazySingleton
        bootstrap.addBundle(guiceBundle);
        //super.initialize(bootstrap);
    }

    @Override
    public void run(TweetConfiguration configuration, Environment environment) throws Exception {

        //Uncomment if enableAutoConfig is not set through GuiceBundle.
        //environment.jersey().register(TweetResource.class);

        //Adding servlet filter, you can also register jersey filter too.
        environment.servlets().addFilter("TxnFilter", new RequestTraxnIdFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        //Health check configuration.
        environment.healthChecks().register("DbHealthCheck",
                guiceBundle.getInjector().getInstance(DBHealthCheck.class));
    }
}
