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

import org.apache.maven.plugin.AbstractMojo;

import com.btmatthews.utils.monitor.Logger;

/**
 * Abstract base class for the Mojos in the plug-in.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public abstract class AbstractServerMojo extends AbstractMojo implements Logger {

	/**
	 * The port to listen on for the monitor commands.
	 * 
	 * @parameter expression="${greenmail.monitor.port}"
	 * @required
	 */
	private int monitorPort;

	/**
	 * Key to provide when sending commands to the GreenMail server.
	 * 
	 * @parameter expression="${greenmail.monitor.key}"
	 * @required
	 */
	private String monitorKey;

	/**
	 * The default constructor is protected because this is an abstract base
	 * class.
	 */
	protected AbstractServerMojo() {
	}

	/**
	 * Get the port number on which the monitor is listening.
	 * 
	 * @return The port number.
	 */
	protected final int getMonitorPort() {
		return monitorPort;
	}

	/**
	 * Get the key used to prefix monitor commands.
	 * 
	 * @return The key.
	 */
	protected final String getMonitorKey() {
		return monitorKey;
	}

	/**
	 * Write an informational message to the plug-in log file.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void logInfo(final String message) {
		getLog().info(message);
	}

	/**
	 * Write an error message to the plug-in log file.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void logError(final String message) {
		getLog().error(message);
	}

	/**
	 * Write an error message to the plug-in log file.
	 * 
	 * @param message
	 *            The message to be logged.
	 * @param cause
	 *            The exception that caused the message to be logged.
	 */
	public void logError(final String message, final Throwable cause) {
		getLog().error(message, cause);
	}

}
