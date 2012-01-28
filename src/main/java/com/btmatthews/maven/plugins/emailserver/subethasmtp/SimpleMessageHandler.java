package com.btmatthews.maven.plugins.emailserver.subethasmtp;

import java.io.IOException;
import java.io.InputStream;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;

public class SimpleMessageHandler implements MessageHandler {
    
    public SimpleMessageHandler(final MessageContext context) {
    }

    public void from(final String address) throws RejectException {
    }

    public void recipient(final String address) throws RejectException {
    }

    public void data(final InputStream inputStream) throws RejectException,
	    TooMuchDataException, IOException {
    }

    public void done() {
    }
}
