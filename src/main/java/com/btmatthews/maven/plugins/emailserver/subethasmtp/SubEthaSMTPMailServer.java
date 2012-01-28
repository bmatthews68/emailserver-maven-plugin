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

package com.btmatthews.maven.plugins.emailserver.subethasmtp;

import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

import com.btmatthews.utils.monitor.Logger;
import com.btmatthews.utils.monitor.Server;

/**
 * Encapsulates the SubEtha SMTP server allowing it to be controlled by a
 * monitor.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class SubEthaSMTPMailServer implements Server {

    /**
     * Used to control the SubEtha SMTP server.
     */
    private SMTPServer server;

    /**
     * Start the GreenMail mail servers.
     */
    public void start(final Logger logger) {
	logger.logInfo("Starting SubEtha SMTP server...");
	final MessageHandlerFactory messageHandlerFactory = new SimpleMessageHandlerFactory();
	server = new SMTPServer(messageHandlerFactory);
	server.setPort(25);
	server.start();
	logger.logInfo("Started SubEtha SMTP server");
    }

    /**
     * Stop the GreenMail mail servers.
     */
    public void stop(final Logger logger) {
	logger.logInfo("Stopping SubEtha SMTP server...");
	if (server != null) {
	    server.stop();
	    server = null;
	}
	logger.logInfo("Stopped SubEtha SMTP server");
    }
}
