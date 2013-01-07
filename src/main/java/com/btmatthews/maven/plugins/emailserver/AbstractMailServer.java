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

package com.btmatthews.maven.plugins.emailserver;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.btmatthews.maven.plugins.emailserver.mojo.Mailbox;
import com.btmatthews.utils.monitor.AbstractServer;
import com.btmatthews.utils.monitor.Logger;

/**
 * Abstract base class for mail servers.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public abstract class AbstractMailServer extends AbstractServer implements MailServer {

    private ResourceBundle bundle = ResourceBundle
            .getBundle("com.btmatthews.maven.plugins.emailserver.messages");

    /**
     * The port offset applied to the standard mail protocol ports.
     */
    private int portOffset = 0;

    /**
     * Used to indicate whether the transports should be secured with SSL/TLS.
     */
    private boolean useSSL;

    /**
     * The mailboxes that will be configured in the mail server.
     *
     * @since 1.1.0
     */
    private Mailbox[] mailboxes;

    /**
     * Configure the mail server.
     *
     * @param name   The property name.
     * @param value  The property value.
     * @param logger Used to log error messages.
     * @see com.btmatthews.utils.monitor.Server#configure(String, Object, com.btmatthews.utils.monitor.Logger)
     */
    public void configure(final String name, final Object value, final Logger logger) {
        if ("portOffset".equals(name)) {
            portOffset = (Integer)value;
        } else if ("useSSL".equals(name)) {
            useSSL = (Boolean)value;
        } else if ("mailboxes".equals(name)) {
            mailboxes = (Mailbox[])value;
        }
    }

    /**
     * Gets the offset that will be applied to the standard port numbers for the
     * mail protocols.
     *
     * @return The port offset.
     */
    protected final int getPortOffset() {
        return portOffset;
    }

    /**
     * Indicates whether the mail protocols should be secured using SSL/TLS.
     *
     * @return <ul>
     *         <li>{@code true} - SSL/TLS will be used.</li>
     *         <li>{@code false} - SSL/TLS will not be used.</li>
     *         </ul>
     */
    protected final boolean isUseSSL() {
        return useSSL;
    }

    /**
     * Get the mailboxes that will be configured in the mail server.
     *
     * @return An array of {@link Mailbox} objects.
     * @since 1.1.0
     */
    protected final Mailbox[] getMailboxes() {
        return mailboxes;
    }

    /**
     * Write a message to the log bundle.
     *
     * @param logger The logger.
     * @param key    The message key.
     */
    protected final void logInfo(final Logger logger, final String key) {
        logInfo(logger, key, new Object[0]);
    }

    /**
     * Write a parameterised message to the log bundle.
     *
     * @param logger The logger.
     * @param key    The message key.
     * @param params The message parameters.
     */
    protected final void logInfo(final Logger logger, final String key,
                                 final Object[] params) {
        final String format = bundle.getString(key);
        final String message = MessageFormat.format(format, params);
        logger.logInfo(message);
    }
}