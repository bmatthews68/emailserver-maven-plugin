package com.btmatthews.maven.plugins.emailserver.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.btmatthews.maven.plugins.emailserver.dumbster.DumbsterMailServerFactory;
import com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServerFactory;
import com.btmatthews.maven.plugins.emailserver.subethasmtp.SubEthaSMTPMailServerFactory;
import com.btmatthews.utils.monitor.Logger;
import com.btmatthews.utils.monitor.ServerFactory;
import com.btmatthews.utils.monitor.ServerFactoryLocator;

public class TestServerFactoryLocator {

    @Mock
    private Logger logger;

    private ServerFactoryLocator locator;

    @Before
    public void setUp() throws Exception {
	initMocks(this);
	locator = ServerFactoryLocator.getInstance(logger);
    }

    @Test
    public void testNull() {
	final ServerFactory factory = locator.getFactory(null);
	assertNull(factory);
    }

    @Test
    public void testBlank() {
	final ServerFactory factory = locator.getFactory("");
	assertNull(factory);
    }

    @Test
    public void testGreenmail() {
	final ServerFactory factory = locator.getFactory("greenmail");
	assertNotNull(factory);
	assertEquals("greenmail", factory.getServerName());
	assertTrue(factory instanceof GreenmailMailServerFactory);
    }

    @Test
    public void testDumbster() {
	final ServerFactory factory = locator.getFactory("dumbster");
	assertNotNull(factory);
	assertEquals("dumbster", factory.getServerName());
	assertTrue(factory instanceof DumbsterMailServerFactory);
    }

    @Test
    public void testSubEthaSMTP() {
	final ServerFactory factory = locator.getFactory("subethasmtp");
	assertNotNull(factory);
	assertEquals("subethasmtp", factory.getServerName());
	assertTrue(factory instanceof SubEthaSMTPMailServerFactory);
    }
}
