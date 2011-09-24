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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The monitor object is used to control a server.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class Monitor {

	/**
	 * The IP address of the localhost.
	 */
	private static final String LOCALHOST = "127.0.0.1";

	/**
	 * The monitor key that must prefix any commands.
	 */
	private String monitorKey;

	/**
	 * The port on which the monitor is listening.
	 */
	private int monitorPort;

	/**
	 * Indicates whether or not the monitor is running.
	 * <ul>
	 * <li>{@code true} if the monitor is running</li>
	 * <li>{@code false} if the monitor is not running</li>
	 * </ul>
	 */
	private boolean running;

	/**
	 * The constructor that initialises the monitor key and port.
	 * 
	 * @param key
	 *            The monitor key that must prefix any commands.
	 * @param port
	 *            The port on which the monitor is listening.
	 */
	public Monitor(final String key, final int port) {
		monitorKey = key;
		monitorPort = port;
		running = false;
	}

	/**
	 * Run the monitor listening for commands and sending them to the server.
	 * 
	 * @param server
	 *            The server being monitored.
	 * @param logger
	 *            Used to log error messages.
	 */
	public void runMonitor(final Server server, final Logger logger) {
		try {
			final ServerSocket serverSocket = new ServerSocket(monitorPort, 1,
					InetAddress.getByName(LOCALHOST));
			try {
				serverSocket.setReuseAddress(true);
				running = true;
				while (running) {
					Socket clientSocket = null;
					try {
						try {
							logger.logInfo("Waiting for command from client");
							clientSocket = serverSocket.accept();
							logger.logInfo("Recieving command from client");
							clientSocket.setSoLinger(false, 0);
							final InputStream inputStream = clientSocket
									.getInputStream();
							final Reader reader = new InputStreamReader(
									inputStream);
							final LineNumberReader lineReader = new LineNumberReader(
									reader);
							final String key = lineReader.readLine();
							if (monitorKey.equals(key)) {
								final String command = lineReader.readLine();
								executeCommand(server, command, logger);
							} else {
								logger.logError("Invalid monitor key");
							}
						} finally {
							if (clientSocket != null) {
								clientSocket.close();
							}
						}
					} catch (final IOException exception) {
						logger.logError("Error in the monitor", exception);
					}
				}
			} finally {
				if (serverSocket != null) {
					serverSocket.close();
				}
			}
		} catch (final IOException exception) {
			logger.logError("Error starting or stopping the monitor", exception);
		}
	}

	/**
	 * Send a command to the monitor.
	 * 
	 * @param command
	 *            The command.
	 * @param logger
	 *            Used to log error messages.
	 */
	public void sendCommand(final String command, final Logger logger) {
		try {
			final Socket socket = new Socket(InetAddress.getByName(LOCALHOST),
					monitorPort);
			try {
				socket.setSoLinger(false, 0);

				final OutputStream outputStream = socket.getOutputStream();
				final Writer writer = new OutputStreamWriter(outputStream);
				final PrintWriter printWriter = new PrintWriter(writer);
				printWriter.println(monitorKey);
				printWriter.println(command);
				printWriter.flush();
				socket.close();
			} finally {
				socket.close();
			}
		} catch (final IOException exception) {
			logger.logError("Error sending command to monitor", exception);
		}
	}

	/**
	 * Execute a command that was sent to the monitor.
	 * 
	 * @param server
	 *            The server.
	 * @param command
	 *            The command.
	 * @param logger
	 *            Used to log error messages.
	 */
	private void executeCommand(final Server server, final String command,
			final Logger logger) {
		if ("stop".equals(command)) {
			server.stop(logger);
			running = false;
		}
	}
}
