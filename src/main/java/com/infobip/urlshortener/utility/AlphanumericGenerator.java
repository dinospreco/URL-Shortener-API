package com.infobip.urlshortener.utility;

import java.util.Random;

/**
 * <h2>Alphanumeric Generator</h2>
 *
 * <p>
 *     This is helper class that can generate random alphanumeric strings
 * </p>
 */
public class AlphanumericGenerator {

    /**
     * Generates random alphanumeric string of 8 charactesr long.
     * It is used for generating password
     * @return
     */
    public static String generateAlphaNumericString8CharsLong() {
        return generateAlphaNumericString(8);
    }

    /**
     * Generates random alphanumeric string of given length
     * @param length
     * @return
     */
    public static String generateAlphaNumericString(int length) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0 ; i < length ; i++) {
            stringBuilder.append(
                    characters.charAt(
                            rnd.nextInt(characters.length())
                    )
            );
        }
        return stringBuilder.toString();
    }
}
