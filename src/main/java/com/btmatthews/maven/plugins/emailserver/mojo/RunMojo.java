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

package com.btmatthews.maven.plugins.emailserver.mojo;

import com.btmatthews.maven.plugins.emailserver.MailServer;
import com.btmatthews.utils.monitor.Monitor;
import com.btmatthews.utils.monitor.ServerFactory;
import com.btmatthews.utils.monitor.ServerFactoryLocator;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Run the mail server(s).
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.TEST_COMPILE)
public class RunMojo extends AbstractServerMojo {

    /**
     * If {@code true} the mail server is run as a daemon.
     */
    @Parameter(property = "emailserver.daemon", defaultValue = "false")
    private boolean daemon;

    /**
     * The name of the server:
     * <ul>
     * <li>greenmail</li>
     * <li>dumbster</li>
     * <li>subethasmtp
     * <li>
     * </ul>
     */
    @Parameter(property = "emailserver.serverName", defaultValue = "greenmail")
    private String serverName;

    /**
     * The offset applied to the standard protocol ports.
     */
    @Parameter(property = "emailserver.portOffset", defaultValue = "0")
    private int portOffset = 0;

    /**
     * If {@code true} then mail servers are run using secure transports.
     */
    @Parameter(property = "emailServer.useSSL", defaultValue = "false")
    private boolean useSSL = false;

    /**
     * The default constructor.
     */
    public RunMojo() {
    }

    /**
     * Execute the Mojo by launching the email servers and waiting for the stop
     * command. If the server daemon option is set the email servers are spun
     * off in a background thread.
     */
    public void execute() {
        final Monitor monitor = new Monitor(getMonitorKey(), getMonitorPort());
        final MailServer server = createServer();
        if (server != null) {
            server.setPortOffset(portOffset);
            server.setUseSSL(useSSL);
            server.start(this);
            if (daemon) {
                new Thread(new Runnable() {
                    public void run() {
                        monitor.runMonitor(server, RunMojo.this);
                    }
                }).start();
            } else {
                monitor.runMonitor(server, this);
            }
        }
    }

    /**
     * Create the mail server(s) using the {@link ServerFactory} registered
     * under the alias matched by {@link RunMojo#serverName}.
     *
     * @return The server object that starts and stops the mail server(s).
     */
    private MailServer createServer() {
        final ServerFactoryLocator locator = ServerFactoryLocator
                .getInstance(this);
        final ServerFactory factory = locator.getFactory(serverName);
        MailServer server;
        if (factory == null) {
            server = null;
        } else {
            server = (MailServer)factory.createServer();
        }
        return server;
    }
}