package com.example.Financers.helpers;

import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordHashService {
    private static final int MIN_SALT_LENGTH = 4;
    private static final int MAX_SALT_LENGTH = 8;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    private final SecureRandom random = new SecureRandom();
    public String hash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update((password + salt).getBytes());
        return convertBytesToAllowedCharacters(messageDigest.digest());
    }

    public String generateSalt() {
        int saltLength = random.nextInt(MAX_SALT_LENGTH - MIN_SALT_LENGTH + 1) + MIN_SALT_LENGTH;
        byte[] bytes = new byte[saltLength];
        random.nextBytes(bytes);
        return convertBytesToAllowedCharacters(bytes);
    }

    private String convertBytesToAllowedCharacters(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes) {
            stringBuilder.append(ALLOWED_CHARACTERS.charAt((b + 256) % ALLOWED_CHARACTERS.length()));
        }
        return stringBuilder.toString();
    }
}
