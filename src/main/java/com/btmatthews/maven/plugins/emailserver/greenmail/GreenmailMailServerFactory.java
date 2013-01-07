/*
 * Copyright 2011-2013 Brian Matthews
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

package com.btmatthews.maven.plugins.emailserver.greenmail;

import com.btmatthews.utils.monitor.Server;
import com.btmatthews.utils.monitor.ServerFactory;

/**
 * Server factory used to create Greenmail mail servers.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public final class GreenmailMailServerFactory implements ServerFactory {

    /**
     * The server name for the Greenmail mail server.
     */
    private static final String GREENMAIL = "greenmail";

    /**
     * The server name for the Greenmail mail server.
     *
     * @return {@link GreenmailMailServerFactory#GREENMAIL}.
     * @see com.btmatthews.utils.monitor.ServerFactory#getServerName()
     */
    public String getServerName() {
        return GREENMAIL;
    }

    /**
     * Create the server object for a Greenmail mail server.
     *
     * @return The newly created server object.
     * @see com.btmatthews.utils.monitor.ServerFactory#createServer()
     */
    public Server createServer() {
        return new GreenmailMailServer();
    }
}
