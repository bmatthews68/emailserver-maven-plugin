/*
 * Copyright 2011-2013 Brian Matthews
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

package com.btmatthews.maven.plugins.emailserver.subethasmtp;

import java.io.InputStream;

import org.subethamail.smtp.MessageHandler;

/**
 * Dummy message handler that does nothing with the message.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public final class SimpleMessageHandler implements MessageHandler {

    /**
     * Set the from e-mail address.
     *
     * @param address The from e-mail address.
     * @see org.subethamail.smtp.MessageHandler#from(java.lang.String)
     */
    public void from(final String address) {
    }

    /**
     * Set a recipient e-mail address.
     *
     * @param address The recipient e-mail address.
     * @see org.subethamail.smtp.MessageHandler#recipient(java.lang.String)
     */
    public void recipient(final String address) {
    }

    /**
     * Set the message data.
     *
     * @param inputStream The message data stream.
     * @see org.subethamail.smtp.MessageHandler#data(java.io.InputStream)
     */
    public void data(final InputStream inputStream) {
    }

    /**
     * Used to indicate the message has been delivered.
     *
     * @see org.subethamail.smtp.MessageHandler#done()
     */
    public void done() {
    }
}
