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

import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Test;

/**
 * Integration tests for the default embedded (Greenmail) e-mail server.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.1
 */
public class ITestDefaultsSendmail {

    /**
     * Verify that the  default embedded (Greenmail) server is working.
     */
    @Test
    public void testSendmail() {
        GreenMailUtil.sendTextEmail(
                "bmatthews68@gmail.com",
                "brian@btmatthews.com",
                "Testing",
                "One Two Three ...",
                ServerSetup.SMTP);
    }
}