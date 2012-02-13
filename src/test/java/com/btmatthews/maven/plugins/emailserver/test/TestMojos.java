package com.btmatthews.maven.plugins.emailserver.test;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import com.btmatthews.maven.plugins.emailserver.mojo.RunMojo;
import com.btmatthews.maven.plugins.emailserver.mojo.StopMojo;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

public class TestMojos extends AbstractMojoTestCase {

    private static final String RUN_DEFAULTS_POM_FILE = "src/test/resources/TestRunMojo-Defaults-pom.xml";

    private static final String RUN_GREENMAIL_POM_FILE = "src/test/resources/TestRunMojo-Greenmail-pom.xml";

    private static final String RUN_DUMBSTER_POM_FILE = "src/test/resources/TestRunMojo-Dumbster-pom.xml";

    private static final String RUN_SUBETHASMTP_POM_FILE = "src/test/resources/TestRunMojo-SubEthaSMTP-pom.xml";

    private static final String STOP_POM_FILE = "src/test/resources/TestStopMojo-pom.xml";

    private static final String RUN_GOAL = "run";

    private static final String STOP_GOAL = "stop";

    @Override
    protected void setUp() throws Exception {
	super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
	super.tearDown();
    }

    public void testDefaultsExecution() throws Exception {
	runTest(RUN_DEFAULTS_POM_FILE);
    }

    public void testGreenmailExecution() throws Exception {
	runTest(RUN_GREENMAIL_POM_FILE);
    }

    public void testDumbsterExecution() throws Exception {
	runTest(RUN_DUMBSTER_POM_FILE);
    }

    public void testSubEthaSMTPExecution() throws Exception {
	runTest(RUN_SUBETHASMTP_POM_FILE);
    }

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
