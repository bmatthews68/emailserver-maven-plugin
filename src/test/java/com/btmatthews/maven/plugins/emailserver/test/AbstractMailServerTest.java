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

package com.btmatthews.maven.plugins.emailserver.test;

import static org.mockito.MockitoAnnotations.initMocks;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.btmatthews.maven.plugins.emailserver.MailServer;
import com.btmatthews.utils.monitor.Logger;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Abstract base class implementing mail server tests.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public abstract class AbstractMailServerTest {

    /**
     * Mock logger object.
     */
    @Mock
    private Logger logger;

    /**
     * The mail server implementation being tested.
     */
    private MailServer mailServer;

    /**
     * The server setup used to send the test e-mail.
     */
    private ServerSetup serverSetup;

    /**
     * Configure the mail server and pick the server setup used to send the test
     * e-mail.
     *
     * @param server     The mail server.
     * @param portOffset The port offset.
     * @param useSSL     If we are to test using SSL/TLS.
     */
    protected AbstractMailServerTest(final MailServer server,
                                     final int portOffset,
                                     final boolean useSSL) {
        mailServer = server;
        mailServer.configure("portOffset", Integer.valueOf(portOffset), null);
        mailServer.configure("useSSL", Boolean.valueOf(useSSL), null);
        if (useSSL) {
            serverSetup = new ServerSetup(465 + portOffset, null, ServerSetup.PROTOCOL_SMTPS);
        } else {
            serverSetup = new ServerSetup(25 + portOffset, null, ServerSetup.PROTOCOL_SMTP);
        }
    }

    /**
     * Initialise mock objects and start the mail server before test execution.
     */
    @Before
    public void setUp() {
        initMocks(this);
        mailServer.start(logger);
    }

    /**
     * Stop the mail server after test execution.
     */
    @After
    public void tearDown() {
        mailServer.stop(logger);
    }

    /**
     * Send an e-mail to test that the SMTP server is working. The test case
     * uses
     * {@link GreenMailUtil#sendTextEmail(String, String, String, String, ServerSetup)}
     * to send the test e-mail.
     *
     * @throws AddressException   If the to and/or from e-mail addresses were malformed.
     * @throws MessagingException If there was any other problem sending the e-mail.
     */
    @Test
    public void testSend() throws AddressException, MessagingException {
        GreenMailUtil.sendTextEmail("bmatthews68@gmail.com",
                "brian@btmatthews.com", "Testing", "One Two Three ...",
                serverSetup);
    }
}
