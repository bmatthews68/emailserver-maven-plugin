/*
 * Copyright 2011-2012 Brian Matthews
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

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

import com.btmatthews.maven.plugins.emailserver.dumbster.DumbsterMailServerFactory;
import com.btmatthews.maven.plugins.emailserver.greenmail.GreenmailMailServerFactory;
import com.btmatthews.maven.plugins.emailserver.subethasmtp.SubEthaSMTPMailServerFactory;
import com.btmatthews.utils.monitor.Logger;
import com.btmatthews.utils.monitor.ServerFactory;
import com.btmatthews.utils.monitor.ServerFactoryLocator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Unit test {@link ServerFactoryLocator}.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class TestServerFactoryLocator {

    /**
     * Used to mock the logger interface.
     */
    @Mock
    private Logger logger;

    /**
     * The server factory locator used for unit tests.
     */
    private ServerFactoryLocator locator;

    /**
     * Initialise the mock object and create a server factory locator.
     */
    @Before
    public void setUp() {
        initMocks(this);
        locator = ServerFactoryLocator.getInstance(logger);
    }

    /**
     * Verify that the server factory locator returns {@code null} if the server
     * name is {@code null}.
     */
    @Test
    public void testNull() {
        final ServerFactory factory = locator.getFactory(null);
        assertNull(factory);
    }

    /**
     * Verify that the server factory locator returns {@code null} if the server
     * name is an empty string.
     */
    @Test
    public void testBlank() {
        final ServerFactory factory = locator.getFactory("");
        assertNull(factory);
    }

    /**
     * Verify that the server factory locator returns a
     * {@link GreenmailMailServerFactory} if the server name is
     * {@code "greenmail"}.
     */
    @Test
    public void testGreenmail() {
        final ServerFactory factory = locator.getFactory("greenmail");
        assertNotNull(factory);
        assertEquals("greenmail", factory.getServerName());
        assertTrue(factory instanceof GreenmailMailServerFactory);
    }

    /**
     * Verify that the server factory locator returns a
     * {@link DumbsterMailServerFactory} if the server name is
     * {@code "dumbster"}.
     */
    @Test
    public void testDumbster() {
        final ServerFactory factory = locator.getFactory("dumbster");
        assertNotNull(factory);
        assertEquals("dumbster", factory.getServerName());
        assertTrue(factory instanceof DumbsterMailServerFactory);
    }

    /**
     * Verify that the server factory locator returns a
     * {@link SubEthaSMTPMailServerFactory} if the server name is
     * {@code "subethasmtp"}.
     */
    @Test
    public void testSubEthaSMTP() {
        final ServerFactory factory = locator.getFactory("subethasmtp");
        assertNotNull(factory);
        assertEquals("subethasmtp", factory.getServerName());
        assertTrue(factory instanceof SubEthaSMTPMailServerFactory);
    }
}
