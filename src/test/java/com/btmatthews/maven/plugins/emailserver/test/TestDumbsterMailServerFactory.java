package com.btmatthews.maven.plugins.emailserver.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.btmatthews.maven.plugins.emailserver.dumbster.DumbsterMailServer;
import com.btmatthews.maven.plugins.emailserver.dumbster.DumbsterMailServerFactory;
import com.btmatthews.utils.monitor.Server;
import com.btmatthews.utils.monitor.ServerFactory;

public class TestDumbsterMailServerFactory {

    private ServerFactory serverFactory;

    @Before
    public void setUp() {
	serverFactory = new DumbsterMailServerFactory();
    }

    @Test
    public void testGetServerName() {
	assertEquals("dumbster", serverFactory.getServerName());
    }

    @Test
    public void testCreateServer() {
	final Server server = serverFactory.createServer();
	assertNotNull(server);
	assertTrue(server instanceof DumbsterMailServer);
    }
}
