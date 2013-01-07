/*
 * Copyright 2013 Brian Matthews
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

import org.codehaus.plexus.util.StringUtils;

/**
 * Describes a mailbox.
 *
 * @since 1.1.0
 */
public final class Mailbox {

    /**
     * The login id for the mailbox.
     */
    private String login;

    /**
     * The login credentials for the mailbox.
     */
    private String password;

    /**
     * The e-mail address for the mailbox.
     */
    private String email;

    /**
     * Determine if the mailbox definition is valid. The mailbox definition is valid if at least the e-mail address
     * has been specified.
     *
     * @return <ul>
     *     <li>{@code true} - if the mailbox definition is valid</li>
     *     <li>{@code false} - if the mailbox definition is not valid</li>
     *     </ul>
     */
    public boolean isValid() {
        return StringUtils.isNotEmpty(email);
    }

    /**
     * The login id for the mailbox. Defaults to the e-mail address if not defined.
     *
     * @return The login id for the mailbox.
     */
    public String getLogin() {
        if (StringUtils.isNotEmpty(login)) {
            return login;
        } else {
            return email;
        }
    }

    /**
     * Set the login id for the mailbox.
     *
     * @param value The login id for the mailbox.
     */
    public void setLogin(final String value) {
        login = value;
    }

    /**
     * The login credentials for the mailbox. Defaults to the e-mail address if not defined.
     *
     * @return The login credentials for the mailbox.
     */
    public String getPassword() {
        if (StringUtils.isNotEmpty(password)) {
            return password;
        } else {
            return email;
        }
    }

    /**
     * Set the login credentials for the mailbox.
     *
     * @param value The login credentials for the mailbox.
     */
    public void setPassword(final String value) {
        password = value;
    }

    /**
     * Get the e-mail address for the mailbox.
     *
     * @return The e-mail address for the mailbox.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the e-mail address for the mailbox.
     *
     * @param value The e-mail address for the mailbox.
     */
    public void setEmail(final String value) {
        email = value;
    }
}
