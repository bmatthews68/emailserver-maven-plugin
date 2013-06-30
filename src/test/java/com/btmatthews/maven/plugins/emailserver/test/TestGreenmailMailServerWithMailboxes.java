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

package com.btmatthews.maven.plugins.emailserver.test;

import javax.mail.AuthenticationFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import java.util.Properties;

import com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServer;
import com.btmatthews.maven.plugins.emailserver.mojo.Mailbox;
import org.junit.Test;

/**
 * Unit test {@link com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServer} after seeding it with
 * mailboxes.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.1.0
 */
public class TestGreenmailMailServerWithMailboxes extends AbstractMailServerTest {

    /**
     * Configure the test for the {@link com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServer} without a port
     * offset and using SMTP.
     */
    public TestGreenmailMailServerWithMailboxes() {
        super(new GreenmailMailServer(), 10000, false, new Mailbox[]{
                createMailbox("brian1", "everclear", "brian1@btmatthews.com"),
                createMailbox("", "everclear", "brian2@btmatthews.com"),
                createMailbox(null, "everclear", "brian3@btmatthews.com"),
                createMailbox("brian4", "", "brian4@btmatthews.com"),
                createMailbox("brian5", null, "brian5@btmatthews.com"),
                createMailbox("brian6", "everclear", ""),
                createMailbox("brian7", "everclear", null)
        });
    }

    @Test
    public void testPOPbrian1() throws Exception {
        testPOP("brian1", "everclear");
    }

    @Test
    public void testIMAPbrian1() throws Exception {
        testIMAP("brian1", "everclear");
    }

    @Test
    public void testPOPbrian2() throws Exception {
        testPOP("brian2@btmatthews.com", "everclear");
    }

    @Test
    public void testIMAPbrian2() throws Exception {
        testIMAP("brian2@btmatthews.com", "everclear");
    }

    @Test
    public void testPOPbrian3() throws Exception {
        testPOP("brian3@btmatthews.com", "everclear");
    }

    @Test
    public void testIMAPbrian3() throws Exception {
        testIMAP("brian3@btmatthews.com", "everclear");
    }

    @Test
    public void testPOPbrian4() throws Exception {
        testPOP("brian4", "brian4@btmatthews.com");
    }

    @Test
    public void testIMAPbrian4() throws Exception {
        testIMAP("brian4", "brian4@btmatthews.com");
    }

    @Test
    public void testPOPbrian5() throws Exception {
        testPOP("brian5", "brian5@btmatthews.com");
    }

    @Test
    public void testIMAPbrian5() throws Exception {
        testIMAP("brian5", "brian5@btmatthews.com");
    }

    @Test(expected = AuthenticationFailedException.class)
    public void testPOPbrian6() throws Exception {
        testPOP("brian6", "everclear");
    }

    @Test(expected = AuthenticationFailedException.class)
    public void testIMAPbrian6() throws Exception {
        testIMAP("brian6", "everclear");
    }

    @Test(expected = AuthenticationFailedException.class)
    public void testPOPbrian7() throws Exception {
        testPOP("brian7", "everclear");
    }

    @Test(expected = AuthenticationFailedException.class)
    public void testIMAPbrian7() throws Exception {
        testIMAP("brian7", "everclear");
    }

    private void testStore(final Properties props,
                           final String protocol,
                           final int port,
                           final String username,
                           final String password) throws Exception {
        final Session session = Session.getInstance(props);
        final URLName urlName = new URLName(protocol, "localhost", port, null, username, password);
        final Store store = session.getStore(urlName);
        store.connect();
        store.close();
    }

    private void testPOP(final String username, final String password) throws Exception {
        final Properties props = new Properties();
        props.setProperty("mail.pop3.connectiontimeout", "5000");
        testStore(props, "pop3", 10110, username, password);
    }

    private void testIMAP(final String username, final String password) throws Exception {
        final Properties props = new Properties();
        testStore(props, "imap", 10143, username, password);
    }

    private static final Mailbox createMailbox(final String login, final String password, final String email) {
        final Mailbox mailbox = new Mailbox();
        mailbox.setLogin(login);
        mailbox.setPassword(password);
        mailbox.setEmail(email);
        return mailbox;
    }
}
