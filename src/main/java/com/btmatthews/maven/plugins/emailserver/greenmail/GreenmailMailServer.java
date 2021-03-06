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

import com.btmatthews.maven.plugins.emailserver.AbstractMailServer;
import com.btmatthews.maven.plugins.emailserver.mojo.Mailbox;
import com.btmatthews.utils.monitor.Logger;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * Encapsulates the GreenMail mail servers allowing them to be controlled by a
 * monitor.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public final class GreenmailMailServer extends AbstractMailServer {

    /**
     * Used to control the GreenMail mail servers.
     */
    private GreenMail greenMail;

    /**
     * Start the GreenMail mail servers.
     *
     * @param logger Used to log error messages.
     */
    @Override
    public void start(final Logger logger) {
        logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.starting");
        final ServerSetup[] serverSetups = getServerSetups();
        greenMail = new GreenMail(serverSetups);
        final Mailbox[] mailboxes = getMailboxes();
        if (mailboxes != null) {
            for (final Mailbox mailbox : getMailboxes()) {
                if (mailbox.isValid()) {
                    greenMail.setUser(mailbox.getEmail(), mailbox.getLogin(), mailbox.getPassword());
                }
            }
        }
        greenMail.start();
        logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.started");
    }

    /**
     * Stop the GreenMail mail servers.
     *
     * @param logger Used to log error messages.
     */
    @Override
    public void stop(final Logger logger) {
        logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.stopping");
        if (greenMail != null) {
            greenMail.stop();
        }
        logInfo(logger, "com.btmatthews.maven.plugin.emailserver.greenmail.stopped");
    }

    @Override
    public boolean isStarted(final Logger logger) {
        return greenMail.getSmtp().isRunning();
    }

    @Override
    public boolean isStopped(final Logger logger) {
        return !greenMail.getSmtp().isRunning();
    }

    /**
     * Create the {@link ServerSetup} objects used to configure the embedded Greenmail server to listen for SMTP,
     * POP3 and IMAP traffic. If the {@link #isUseSSL()} returns {@code true} the server will be configured for SMTPS,
     * POP3S and IMAP3S instead. {@link #getPortOffset()} is used to offset the start port addresses to avoid
     * conflicts.
     *
     * @return An array of {@link ServerSetup} used to configure the embedded Greenmail server.
     */
    private ServerSetup[] getServerSetups() {
        if (isUseSSL()) {
            return new ServerSetup[]{
                    new ServerSetup(DEFAULT_SMTPS_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_SMTPS),
                    new ServerSetup(DEFAULT_POP3S_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_POP3S),
                    new ServerSetup(DEFAULT_IMAPS_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_IMAPS)
            };
        } else {
            return new ServerSetup[]{
                    new ServerSetup(DEFAULT_SMTP_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_SMTP),
                    new ServerSetup(DEFAULT_POP3_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_POP3),
                    new ServerSetup(DEFAULT_IMAP_PORT + getPortOffset(), null, ServerSetup.PROTOCOL_IMAP)
            };
        }
    }
}