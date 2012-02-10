package com.btmatthews.maven.plugins.emailserver.test;

import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.btmatthews.maven.plugins.emailserver.dumbster.DumbsterMailServer;
import com.btmatthews.utils.monitor.Logger;

public class TestDumbsterMailServer {

    @Mock
    private Logger logger;

    private DumbsterMailServer mailServer;

    @Before
    public void setUp() {
	initMocks(this);
	mailServer = new DumbsterMailServer();
	mailServer.start(logger);
    }

    @After
    public void tearDown() {
	mailServer.stop(logger);
    }

    @Test
    public void testSendMail() throws AddressException, MessagingException {
	final Properties props = new Properties();
	props.put("mail.smtp.host", "localhost");
	props.put("mail.smtp.port", "25");

	final Session mailSession = Session.getDefaultInstance(props);
	final Message simpleMessage = new MimeMessage(mailSession);
	final InternetAddress fromAddress = new InternetAddress(
		"brian@btmatthews.com");
	final InternetAddress toAddress = new InternetAddress(
		"bmatthews68@gmail.com");

	simpleMessage.setFrom(fromAddress);
	simpleMessage.setRecipient(RecipientType.TO, toAddress);
	simpleMessage.setSubject("Testing");
	simpleMessage.setText("One Two Three ...");

	Transport.send(simpleMessage);
    }
}
