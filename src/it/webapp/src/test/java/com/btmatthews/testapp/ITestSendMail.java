package com.btmatthews.testapp;

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

import com.btmatthews.selenium.junit4.runner.SeleniumJUnit4ClassRunner;
import com.btmatthews.selenium.junit4.runner.SeleniumWebDriver;
import com.btmatthews.selenium.junit4.runner.WebDriverConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SeleniumJUnit4ClassRunner.class)
@WebDriverConfiguration
public class ITestSendMail {

    @SeleniumWebDriver
    private WebDriver webDriver;

    @Test
    public void testSendMail() throws Exception {
        webDriver.navigate().to("http://localhost:9080/send.html");
        assertEquals("Sent", webDriver.getTitle());

        final Properties props = new Properties();
        final Session session = Session.getInstance(props);
        final URLName urlName = new URLName("imap", "localhost", 13143, null, "brian", "everclear");
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
                assertEquals("admin@btmatthews.com", messages[0].getFrom()[0].toString());
            } finally {
                folder.close(true);
            }
        } finally {
            store.close();
        }

    }
}
