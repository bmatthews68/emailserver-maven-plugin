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

import com.btmatthews.utils.monitor.Server;

/**
 * The interface declaring the methods that must be defined in the mail server
 * implementations.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public interface MailServer extends Server {

    /**
     * The default port for the SMTP protocol.
     */
    int DEFAULT_SMTP_PORT = 25;

    /**
     * The default port for the SMTPS protocol.
     */
    int DEFAULT_SMTPS_PORT = 465;

    /**
     * Sets the offset that will be applied to the standard port numbers for the
     * mail protocols.
     * 
     * @param offset
     *            The port offset.
     */
    void setPortOffset(int offset);

    /**
     * Indicates whether the mail protocols should be secured using SSL/TLS.
     * 
     * @param useSSL
     *            <ul>
     *            <li>{@code true} - SSL/TLS will be used.</li>
     *            <li>{@code false} - SSL/TLS will not be used.</li>
     *            </ul>
     */
    void setUseSSL(boolean useSSL);
}
