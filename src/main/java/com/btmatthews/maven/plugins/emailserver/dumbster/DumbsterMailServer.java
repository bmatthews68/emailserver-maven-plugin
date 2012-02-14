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

package com.btmatthews.maven.plugins.emailserver.dumbster;

import com.btmatthews.maven.plugins.emailserver.AbstractMailServer;
import com.btmatthews.utils.monitor.Logger;
import com.dumbster.smtp.SimpleSmtpServer;

/**
 * Encapsulates the <a href="http://quintanasoft.com/dumbster/">Dumbster</a>
 * fake SMTP server allowing it to be controlled by a monitor.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public final class DumbsterMailServer extends AbstractMailServer {

    /**
     * Used to control the Dumbster fake SMTP server.
     */
    private SimpleSmtpServer server;

    /**
     * Start the Dumbster mail server.
     * 
     * @param logger
     *            Used to log error messages.
     */
    public void start(final Logger logger) {
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.dumbster.starting");
	server = SimpleSmtpServer.start(DEFAULT_SMTP_PORT + getPortOffset());
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.dumbster.started");
    }

    /**
     * Stop the Dumbster mail server.
     * 
     * @param logger
     *            Used to log error messages.
     */
    public void stop(final Logger logger) {
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.stopping");
	if (server != null) {
	    server.stop();
	}
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.stopped");
    }
}
