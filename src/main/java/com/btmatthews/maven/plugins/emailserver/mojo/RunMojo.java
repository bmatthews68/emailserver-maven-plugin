/*
 * Copyright 2011-2012 Brian Matthews
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btmatthews.maven.plugins.emailserver.mojo;

import java.util.HashMap;
import java.util.Map;

import com.btmatthews.maven.plugins.emailserver.MailServer;
import com.btmatthews.utils.monitor.ServerFactory;
import com.btmatthews.utils.monitor.ServerFactoryLocator;
import com.btmatthews.utils.monitor.mojo.AbstractRunMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Run the e-mail server.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class RunMojo extends AbstractRunMojo {

    /**
     * The name of the server:
     * <ul>
     * <li>greenmail</li>
     * <li>dumbster</li>
     * <li>subethasmtp
     * <li>
     * </ul>
     */
    @Parameter(property = "emailserver.serverName", defaultValue = "greenmail")
    private String serverName;

    /**
     * The offset applied to the standard protocol ports.
     */
    @Parameter(property = "emailserver.portOffset", defaultValue = "0")
    private int portOffset = 0;

    /**
     * If {@code true} then mail servers are run using secure transports.
     */
    @Parameter(property = "emailServer.useSSL", defaultValue = "false")
    private boolean useSSL = false;

    /**
     * Return the identifier for the server type.
     *
     * @return The value of {@link #serverName}.
     */
    @Override
    public String getServerType() {
        return serverName;
    }

    /**
     * Return the server configuration which consists of the settings for the port offset and the SSL flag.
     *
     * @return The server configuration.
     */
    @Override
    public Map<String, Object> getServerConfig() {
        final Map<String, Object> config = new HashMap<String, Object>();
        config.put("portOffset", Integer.valueOf(portOffset));
        config.put("useSSL", Boolean.valueOf(useSSL));
        return config;
    }
}