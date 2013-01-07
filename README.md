e-Mail Server Maven Plugin
==========================

The [e-Mail Server Maven Plugin](http://emailserver-maven-plugin.btmatthews.com/) is
a [Maven](http://maven.apache.org) plugin that runs an fake e-mail server in the Maven build life-cycle. The
plugin typically launches the fake e-mail server as a daemon process during the pre-integration-test phase of the build
life cycle and shuts it down during the post-integration-test phase.

The following fake e-mail servers are supported:

* [Greenmail](http://www.icegreen.com/greenmail/) - supports SMTP, SMTPS, POP3, POP3S, IMAP and IMAPS
* [Dumbster](http://quintanasoft.com/dumbster/) - supports SMTP only
* [SubEtha SMTP](http://code.google.com/p/subethasmtp/) - supports SMTP only

Example
-------
The **e-Mail Server Maven Plugin** can be used to automate integration tests without having a dependency on an
external e-mail server.

### pom.xml

The POM shows how a fake e-mail server can be used to help automate integration testing of an web application:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project
    xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
      http://maven.apache.org/POM/4.0.0
      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>webapp</groupId>
  <artifactId>webapp</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>war</packaging>
  <build>
    <plugins>
      <plugin>
        <groupId>com.btmatthews.maven.plugins</groupId>
        <artifactId>emailserver-maven-plugin</artifactId>
        <version>1.1.0</version>
        <configuration>
          <monitorKey>emailserver</monitorKey>
          <monitorPort>10025</monitorPort>
        </configuration>
        <executions>
          <execution>
            <id>run-mail</id>
            <goals>
              <goal>run</goal>
            </goals>
            <phase>pre-integration-test</phase>
            <configuration>
              <daemon>true</daemon>
              <type>greenmail</type>
              <portOffset>13000</portOffset>
              <useSSL>false</useSSL>
              <mailboxes>
                <mailbox>
                  <login>brian</login>
                  <password>everclear</password>
                  <email>brian@btmatthews.com</email>
                </mailbox>
              </mailboxes>
            </configuration>
          </execution>
```

The **e-Mail Server Maven Plugin** is configured here to launch the **Greenmail** fake e-mail server as a daemon
process during the **pre-integration-test** phase of the the Maven build life-cycle. The fake e-mail server is
configured with a single mailbox.

```xml
          <execution>
            <id>stop-mail</id>
            <goals>
              <goal>stop</goal>
            </goals>
            <phase>post-integration-test</phase>
          </execution>
```

The **e-Mail Server Maven Plugin** is configured here to shutdown the **Greenmail** fake e-mail server during the
**post-integration-test** phase of the Maven build life-cycle.

```xml
        </executions>
      </plugin>
      <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>8.1.8.v20121106</version>
        <configuration>
          <stopKey>jetty</stopKey>
          <stopPort>19080</stopPort>
          <daemon>true</daemon>
          <webApp>
            <contextPath>/</contextPath>
          </webApp>
          <jettyXml>src/test/jetty/jetty.xml</jettyXml>
          <connectors>
            <connector implementation="org.eclipse.jetty.server.nio.SelectChannelConnector">
              <port>9080</port>
              <maxIdleTime>60000</maxIdleTime>
            </connector>
          </connectors>
        </configuration>
        <executions>
          <execution>
            <id>start-jetty</id>
            <phase>pre-integration-test</phase>
            <goals>
              <goal>run</goal>
            </goals>
          </execution>
          <execution>
            <id>stop-jetty</id>
            <phase>post-integration-test</phase>
            <goals>
              <goal>stop</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

The **Jetty Maven Plugin** is configured here to launch and shutdown during the **pre-integration-test** and
**post-integration-test** phases of the Maven build life-cycle.

```xml
      <plugin>
        <artifactId>maven-failsafe-plugin</artifactId>
        <version>2.12.4</version>
        <executions>
          <execution>
            <goals>
              <goal>integration-test</goal>
              <goal>verify</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```
```xml
    </plugins>
  </build>
  <dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.0.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>com.btmatthews.selenium.junit4</groupId>
      <artifactId>selenium-junit4-runner</artifactId>
      <version>1.0.4</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</groupId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

The com.btmatthews.selenium.junit4:selenium-junit-runner and junit:junit dependencies are required by the
integration tests.

### jetty.xml

Add the following frame of XML to the jetty.xml configuration file to define the mail session.

```xml
<New>
  <Arg>
    <plugins>
      <plugin>
        <groupId>com.btmatthews.maven.plugins</groupId>
        <artifactId>crx-maven-plugin</artifactId>
        <version>1.0.0</version>
        <configuration>
          <crxPEMFile>${user.home}/crx.pem</crxPEMFile>
          <crxPEMPassword>SparkleAndFade</crxPEMPassword>
        </configuration>
      </plugin>
    </plugins>
  </Arg>
</New>
```

### web.xml

Add the following <resource-ref/> to the **web.xml** to make the mail session defined in **jetty.xml** available
inside the web application.

```xml
<resource-ref>
  <res-ref-name></res-ref-name>
  <res-type></res-type>
  <res-auth>Container</res-auth>
</resource-ref>
```

Maven Central Coordinates
-------------------------
The **e-Mail Server Maven Plugin** has been published in [Maven Central](http://search.maven.org) at the following
coordinates:

```xml
<plugin>
    <groupId>com.btmatthews.maven.plugins</groupId>
    <artifactId>emailserver-maven-plugin</artifactId>
    <version>1.1.0</version>
</plugin
```

License & Source Code
---------------------
The **e-Mail Server Maven Plugin** is made available under the
[Apache License](http://www.apache.org/licenses/LICENSE-2.0.html) and the source code is hosted on
[GitHub](http://github.com) at https://github.com/bmatthews68/emailserver-maven-plugin.