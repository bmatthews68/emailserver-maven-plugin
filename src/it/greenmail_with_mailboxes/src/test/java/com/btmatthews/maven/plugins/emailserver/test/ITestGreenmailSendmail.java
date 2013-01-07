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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import java.util.Properties;

import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Test;

/**
 * Integration tests for the embedded Greenmail e-mail server.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.1
 */
public class ITestGreenmailSendmail {

    /**
     * Verify that the embedded Greenmail server is working properly when pre-configured with a mailbox.
     */
    @Test
    public void testSendmail() throws Exception {

        // Send an e-mail to the pre-configured mailbox

        GreenMailUtil.sendTextEmail(
                "brian@btmatthews.com",
                "bmatthews68@gmail.com",
                "Testing",
                "One Two Three ...",
                ServerSetup.SMTP);

        // Connect to the inbox for the pre-configured mailbox and verify
        // that it contains the e-mail we just sent

        final Properties props = new Properties();
        final Session session = Session.getInstance(props);
        final URLName urlName = new URLName("imap", "localhost", 143, null, "brian", "everclear");
        final Store store = session.getStore(urlName);
        store.connect();
        try {
            final Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            try {
                final Message[] messages = folder.getMessages();
                assertNotNull(messages);
                assertThat(1, equalTo(messages.length));
                assertEquals("Testing", messages[0].getSubject());
                assertTrue(String.valueOf(messages[0].getContent()).contains("One Two Three ..."));
                assertEquals("bmatthews68@gmail.com", messages[0].getFrom()[0].toString());
            } finally {
                folder.close(true);
            }
        } finally {
            store.close();
        }
    }
}