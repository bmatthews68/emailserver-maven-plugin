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

package com.btmatthews.maven.plugins.greenmail.mojo;

import com.icegreen.greenmail.util.GreenMail;

/**
 * Encapsulates the GreenMail mail servers allowing then to be controlled by a
 * monitor.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class GreenMailServer implements Server {

	/**
	 * Used to control the GreenMail mail servers.
	 */
	private GreenMail greenMail;

	/**
	 * The constructor initialises {@link #greenMail} with the default
	 * configuration.
	 */
	public GreenMailServer() {
		greenMail = new GreenMail();
	}

	/**
	 * Start the GreenMail mail servers.
	 */
	public void start(final Logger logger) {
		logger.logInfo("Starting mail servers...");
		greenMail.start();
		logger.logInfo("Started mail servers");
	}

	/**
	 * Stop the GreenMail mail servers.
	 */
	public void stop(final Logger logger) {
		logger.logInfo("Stopping mail servers...");
		greenMail.stop();
		logger.logInfo("Stopped mail servers");
	}
}
