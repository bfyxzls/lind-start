/* Copyright (c) 2016 Jon Chambers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. */

package com.lind.common.otp;

import javax.crypto.Mac;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Generates HMAC-based one-time passwords (HOTP).
 */
public class HmacOneTimePasswordGenerator {
    /**
     * The default length, in decimal digits, for one-time passwords.
     */
    public static final int DEFAULT_PASSWORD_LENGTH = 8;
    /**
     * The HMAC algorithm specified by the HOTP standard.
     */
    public static final String HOTP_HMAC_ALGORITHM = "HmacSHA1";
    private final Mac mac;
    private final int passwordLength;
    private final byte[] buffer;
    private final int modDivisor;

    /**
     * Creates a new HMAC-based one-time password (HOTP) generator using a default password length.
     */
    public HmacOneTimePasswordGenerator() throws NoSuchAlgorithmException {
        this(DEFAULT_PASSWORD_LENGTH);
    }

    /**
     * Creates a new HMAC-based one-time password (HOTP) generator using the given password length.
     */
    public HmacOneTimePasswordGenerator(final int passwordLength) throws NoSuchAlgorithmException {
        this(passwordLength, HOTP_HMAC_ALGORITHM);
    }

    /**
     * Creates a new HMAC-based one-time password generator using the given password length
     * and algorithm.
     */
    protected HmacOneTimePasswordGenerator(final int passwordLength, final String algorithm)
            throws NoSuchAlgorithmException {
        this.mac = Mac.getInstance(algorithm);

        switch (passwordLength) {
            case 6: {
                this.modDivisor = 1_000_000;
                break;
            }

            case 7: {
                this.modDivisor = 10_000_000;
                break;
            }

            case 8: {
                this.modDivisor = 100_000_000;
                break;
            }

            default: {
                throw new IllegalArgumentException(
                        "Password length must be between 6 and 8 digits.");
            }
        }

        this.passwordLength = passwordLength;
        this.buffer = new byte[this.mac.getMacLength()];
    }

    /**
     * 生成key.
     */
    protected Key generateKey(String password) {
        byte[] keyBytes = password.getBytes(Charset.forName("UTF-8"));
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, this.mac.getAlgorithm());
        return signingKey;
    }

    /**
     * Generates a one-time password using the given key and counter value.
     */
    public synchronized int generateOneTimePassword(String password, final long counter)
            throws Exception {
        Key key = generateKey(password);
        this.mac.init(key);
        this.buffer[0] = (byte) ((counter & 0xff00000000000000L) >>> 56);
        this.buffer[1] = (byte) ((counter & 0x00ff000000000000L) >>> 48);
        this.buffer[2] = (byte) ((counter & 0x0000ff0000000000L) >>> 40);
        this.buffer[3] = (byte) ((counter & 0x000000ff00000000L) >>> 32);
        this.buffer[4] = (byte) ((counter & 0x00000000ff000000L) >>> 24);
        this.buffer[5] = (byte) ((counter & 0x0000000000ff0000L) >>> 16);
        this.buffer[6] = (byte) ((counter & 0x000000000000ff00L) >>> 8);
        this.buffer[7] = (byte) (counter & 0x00000000000000ffL);

        this.mac.update(this.buffer, 0, 8);

        try {
            this.mac.doFinal(this.buffer, 0);
        } catch (final ShortBufferException e) {
            // We allocated the buffer to (at least) match the size of the MAC length at construction time, so this
            // should never happen.
            throw new RuntimeException(e);
        }

        final int offset = this.buffer[this.buffer.length - 1] & 0x0f;

        return ((this.buffer[offset] & 0x7f) << 24
                |
                (this.buffer[offset + 1] & 0xff) << 16
                |
                (this.buffer[offset + 2] & 0xff) << 8
                |
                (this.buffer[offset + 3] & 0xff))
                %
                this.modDivisor;
    }

    /**
     * Returns the length, in decimal digits, of passwords produced by this generator.
     *
     * @return the length, in decimal digits, of passwords produced by this generator
     */
    public int getPasswordLength() {
        return this.passwordLength;
    }

    /**
     * Returns the name of the HMAC algorithm used by this generator.
     *
     * @return the name of the HMAC algorithm used by this generator
     */
    public String getAlgorithm() {
        return this.mac.getAlgorithm();
    }
}
