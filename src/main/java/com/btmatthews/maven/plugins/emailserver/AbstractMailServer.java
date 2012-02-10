package com.btmatthews.maven.plugins.emailserver;

import com.dumbster.smtp.SimpleSmtpServer;

public abstract class AbstractMailServer implements MailServer {

    private int portOffset = 0;
    private boolean useSSL;

    public void setPortOffset(final int offset) {
	portOffset = offset;
    }

    public void setUseSSL(final boolean use) {
	useSSL = use;
    }

    protected int getPortOffset() {
	return portOffset;
    }

    protected boolean isUseSSL() {
	return useSSL;
    }

}