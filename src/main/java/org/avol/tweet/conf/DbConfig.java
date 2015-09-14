package org.avol.tweet.conf;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 9/11/2015.
 *
 * This class instantiated by DropWizard with YAML configuration.
 */

@Setter @Getter
public class DbConfig {

    private String driverClass;

    private String connURL;

    private String userName;

    private String password;

}
