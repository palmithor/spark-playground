package com.palmithor.sparkplayground.http;

import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;

/**
 * App configurations - authentication clients initialized etc.
 *
 * @author palmithor
 * @since 13.1.2017.
 */
public class AuthConfig implements ConfigFactory {


    @Override
    public Config build() {
        final DirectBasicAuthClient directBasicAuthClient = new DirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());

        final Config config = new Config(directBasicAuthClient);
        config.setHttpActionAdapter(new ApiActionAdapter());


        return config;
    }
}
