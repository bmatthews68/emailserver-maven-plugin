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

import com.btmatthews.maven.plugins.emailserver.AbstractMailServer;
import com.btmatthews.utils.monitor.Logger;

/**
 * Encapsulates the <a href="http://code.google.com/p/subethasmtp/">SubEtha
 * SMTP</a> server allowing it to be controlled by a monitor.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public final class SubEthaSMTPMailServer extends AbstractMailServer {

    /**
     * Used to control the SubEtha SMTP server.
     */
    private SMTPServer server;

    /**
     * Start the mail server.
     * 
     * @param logger
     *            Used to log error messages.
     */
    public void start(final Logger logger) {
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.subethasmtp.starting");
	final MessageHandlerFactory messageHandlerFactory = new SimpleMessageHandlerFactory();
	server = new SMTPServer(messageHandlerFactory);
	if (isUseSSL()) {
	    server.setPort(DEFAULT_SMTPS_PORT + getPortOffset());
	    server.setEnableTLS(true);
	    server.setRequireTLS(true);
	} else {
	    server.setPort(DEFAULT_SMTP_PORT + getPortOffset());
	}
	server.start();
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.subethasmtp.started");
    }

    /**
     * Stop the mail server.
     * 
     * @param logger
     *            Used to log error messages.
     */
    public void stop(final Logger logger) {
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.subethasmtp.stopping");
	if (server != null) {
	    server.stop();
	    server = null;
	}
	logInfo(logger, "com.btmatthews.maven.plugin.emailserver.subethasmtp.stopped");
    }
}
