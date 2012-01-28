package com.btmatthews.maven.plugins.emailserver.subethasmtp;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

public class SimpleMessageHandlerFactory implements MessageHandlerFactory {

    public MessageHandler create(final MessageContext context) {
	return new SimpleMessageHandler(context);
    }

}
