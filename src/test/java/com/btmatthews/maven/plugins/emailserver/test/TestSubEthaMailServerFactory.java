package com.btmatthews.maven.plugins.emailserver.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.btmatthews.maven.plugins.emailserver.subethasmtp.SubEthaSMTPMailServer;
import com.btmatthews.maven.plugins.emailserver.subethasmtp.SubEthaSMTPMailServerFactory;
import com.btmatthews.utils.monitor.Server;
import com.btmatthews.utils.monitor.ServerFactory;

public class TestSubEthaMailServerFactory {

    private ServerFactory serverFactory;

    @Before
    public void setUp() {
	serverFactory = new SubEthaSMTPMailServerFactory();
    }

    @Test
    public void testGetServerName() {
	assertEquals("subethasmtp", serverFactory.getServerName());
    }

    @Test
    public void testCreateServer() {
	final Server server = serverFactory.createServer();
	assertNotNull(server);
	assertTrue(server instanceof SubEthaSMTPMailServer);
    }
}
