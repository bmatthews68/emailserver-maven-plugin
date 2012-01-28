package com.btmatthews.maven.plugins.emailserver.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServer;
import com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServerFactory;
import com.btmatthews.utils.monitor.Server;
import com.btmatthews.utils.monitor.ServerFactory;

public class TestGreenmailMailServerFactory {

    private ServerFactory serverFactory;

    @Before
    public void setUp() {
	serverFactory = new GreenmailMailServerFactory();
    }

    @Test
    public void testGetServerName() {
	assertEquals("greenmail", serverFactory.getServerName());
    }

    @Test
    public void testCreateServer() {
	final Server server = serverFactory.createServer();
	assertNotNull(server);
	assertTrue(server instanceof GreenmailMailServer);
    }
}
