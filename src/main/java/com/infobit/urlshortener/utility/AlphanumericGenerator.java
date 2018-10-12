package com.infobit.urlshortener.utility;

import java.util.Random;

public class AlphanumericGenerator {

    public static String generateAlphaNumericString8CharsLong() {
        return generateAlphaNumericString(8);
    }

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
