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

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import com.btmatthews.maven.plugins.emailserver.mojo.RunMojo;
import com.btmatthews.maven.plugins.emailserver.mojo.StopMojo;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * Test Mojos.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class TestMojos extends AbstractMojoTestCase {

    /**
     * The POM file used to execute the run goal for a default configuration.
     */
    private static final String RUN_DEFAULTS_POM_FILE = "src/test/resources/TestRunMojo-Defaults-pom.xml";

    /**
     * The POM file used to execute the run goal for a Greenmail configuration.
     */
    private static final String RUN_GREENMAIL_POM_FILE = "src/test/resources/TestRunMojo-Greenmail-pom.xml";

    /**
     * The POM file used to execute the run goal for a Dumbster configuration.
     */
    private static final String RUN_DUMBSTER_POM_FILE = "src/test/resources/TestRunMojo-Dumbster-pom.xml";

    /**
     * The POM file used to execute the run goal for a SubEtha SMTP
     * configuration.
     */
    private static final String RUN_SUBETHASMTP_POM_FILE = "src/test/resources/TestRunMojo-SubEthaSMTP-pom.xml";

    /**
     * The POM file used to execute the stop goal.
     */
    private static final String STOP_POM_FILE = "src/test/resources/TestStopMojo-pom.xml";

    /**
     * The name of the run goal.
     */
    private static final String RUN_GOAL = "run";

    /**
     * The name of the stop goal.
     */
    private static final String STOP_GOAL = "stop";

    /**
     * Run the test using the default configuration.
     * 
     * @throws Exception
     *             If there was a problem executing the test case.
     */
    public void testDefaultsExecution() throws Exception {
	runTest(RUN_DEFAULTS_POM_FILE);
    }

    /**
     * Run the test using Greenmail configuration.
     * 
     * @throws Exception
     *             If there was a problem executing the test case.
     */
    public void testGreenmailExecution() throws Exception {
	runTest(RUN_GREENMAIL_POM_FILE);
    }

    /**
     * Run the test using Dumbster configuration.
     * 
     * @throws Exception
     *             If there was a problem executing the test case.
     */
    public void testDumbsterExecution() throws Exception {
	runTest(RUN_DUMBSTER_POM_FILE);
    }

    /**
     * Run the test using SubEtha SMTP configuration.
     * 
     * @throws Exception
     *             If there was a problem executing the test case.
     */
    public void testSubEthaSMTPExecution() throws Exception {
	runTest(RUN_SUBETHASMTP_POM_FILE);
    }

    /**
     * Test running the mail server as a daemon, sending an e-mail and then stop
     * the mail server.
     * 
     * @param runPomFileName
     *            The POM file containing the run goal used in the test.
     * @throws Exception
     *             If there was a problem executing the test.
     */
    private void runTest(final String runPomFileName) throws Exception {
	final File runPom = getTestFile(runPomFileName);
	assertNotNull(runPom);
	assertTrue(runPom.exists());
	final RunMojo runMojo = (RunMojo) lookupMojo(RUN_GOAL, runPom);
	assertNotNull(runMojo);
	runMojo.execute();
	GreenMailUtil.sendTextEmail("bmatthews68@gmail.com",
		"brian@btmatthews.com", "Testing", "One Two Three ...",
		ServerSetup.SMTP);
	final File stopPom = getTestFile(STOP_POM_FILE);
	assertNotNull(stopPom);
	assertTrue(stopPom.exists());
	final StopMojo stopMojo = (StopMojo) lookupMojo(STOP_GOAL, stopPom);
	assertNotNull(stopMojo);
	stopMojo.execute();
    }
}
