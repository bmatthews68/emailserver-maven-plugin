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
     * The default port for the POP3 protocol.
     */
    int DEFAULT_POP3_PORT = 110;

    /**
     * The default port for the IMAP protocol.
     */
    int DEFAULT_IMAP_PORT = 143;

    /**
     * The default port for the SMTPS protocol.
     */
    int DEFAULT_SMTPS_PORT = 465;

    /**
     * The default port for the POP3S protocol.
     */
    int DEFAULT_POP3S_PORT = 995;

    /**
     * The default port for the IMAPS protocol.
     */
    int DEFAULT_IMAPS_PORT = 993;
}
