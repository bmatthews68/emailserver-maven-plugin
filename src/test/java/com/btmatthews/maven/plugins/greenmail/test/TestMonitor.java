/*
 * Copyright 2011 Brian Matthews
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

package com.btmatthews.maven.plugins.greenmail.test;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.btmatthews.maven.plugins.greenmail.mojo.Logger;
import com.btmatthews.maven.plugins.greenmail.mojo.Monitor;
import com.btmatthews.maven.plugins.greenmail.mojo.Server;

/**
 * Unit test the monitor.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @version 1.0.0
 */
public class TestMonitor {

	/**
	 * Verify that a monitor can be started and stopped successfully.
	 */
	@Test
	public void testMonitor() throws InterruptedException {
		final Server server = Mockito.mock(Server.class);
		final Logger logger = Mockito.mock(Logger.class);
		final Monitor monitor = new Monitor("greenMail", 10025);
		final Thread monitorThread = new Thread(new Runnable() {
			public void run() {
				server.start(logger);
				monitor.runMonitor(server, logger);
			}
		});
		monitorThread.start();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				monitor.sendCommand("stop", logger);
			}
		}, 5000L);
		monitorThread.join(10000L);
		Mockito.verify(server).start(Matchers.same(logger));
		Mockito.verify(server).stop(Matchers.same(logger));
		Mockito.validateMockitoUsage();
	}
}
