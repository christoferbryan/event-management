package com.events.eventmanagement.generator;

import java.security.SecureRandom;

public class ReferralCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateCode(int length){

        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for(int i=0; i<length; i++){
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return code.toString();
    }
}
