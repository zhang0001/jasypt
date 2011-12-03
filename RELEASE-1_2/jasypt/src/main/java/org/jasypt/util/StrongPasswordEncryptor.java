/*
 * =============================================================================
 * 
 *   Copyright (c) 2007, The JASYPT team (http://www.jasypt.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.jasypt.util;

import org.jasypt.digest.StandardStringDigester;

/**
 * <p>
 * Utility class for easily performing high-strength password digesting and checking.
 * </p>
 * <p>
 * This class internally holds a {@link StandardStringDigester} 
 * configured this way:
 * <ul>
 *   <li>Algorithm: <tt>SHA-256</tt>.</li>
 *   <li>Salt size: <tt>16 bytes</tt>.</li>
 *   <li>Iterations: <tt>100000</tt>.</li>
 * </ul>
 * </p>
 * <p>
 * The required steps to use it are:
 * <ol>
 *   <li>Create an instance (using <tt>new</tt>).</li>
 *   <li>Perform the desired <tt>{@link #encryptPassword(String)}</tt> or 
 *       <tt>{@link #checkPassword(String, String)}</tt> 
 *       operations.</li> 
 * </ol> 
 * </p>
 * <p>
 * This class is <i>thread-safe</i>
 * </p>
 * 
 * @deprecated Replaced by {@link org.jasypt.util.password.StrongPasswordEncryptor}
 *             and will be removed in version 1.3. 
 * @see org.jasypt.util.password.StrongPasswordEncryptor
 * @since 1.1
 * 
 * @author Daniel Fern&aacute;ndez Garrido
 * 
 */
public final class StrongPasswordEncryptor {

    // The internal digester used
    private StandardStringDigester digester = null;
    
    
    /**
     * Creates a new instance of <tt>StrongPasswordEncryptor</tt>
     *
     */
    public StrongPasswordEncryptor() {
        super();
        this.digester = new StandardStringDigester();
        this.digester.setAlgorithm("SHA-256");
        this.digester.setIterations(100000);
        this.digester.setSaltSizeBytes(16);
        this.digester.initialize();
    }
    
    
    /**
     * Encrypts (digests) a password.
     * 
     * @param password the password to be encrypted.
     * @return the resulting digest.
     * @see StandardStringDigester#digest(String)
     */
    public String encryptPassword(String password) {
        return digester.digest(password);
    }

    
    /**
     * Checks an unencrypted (plain) password against an encrypted one
     * (a digest) to see if they match.
     * 
     * @param plainPassword the plain password to check.
     * @param encryptedPassword the digest against which to check the password.
     * @return true if passwords match, false if not.
     * @see StandardStringDigester#matches(String, String)
     */
    public boolean checkPassword(String plainPassword, 
            String encryptedPassword) {
        return digester.matches(plainPassword, encryptedPassword);
    }
    
}
