package com.fruit.serverwebapp.config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasscodeGenerator {
    private static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int CODE_LENGTH = 16;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generatePasscode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int idx = secureRandom.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(idx));
        }
        return sb.toString();
    }
}
