package com.monggovest.MonggoVestBackEnd.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class UtilsRandomNumber {

    // FUNGSI GET RANDOM NUMBER 6 ANGKA

    private final Random r = new SecureRandom();
    private final String ALPHABET = "0123456789";

    public String generateTrxInvoiceNum (int lenght){
        return generateRandomString (lenght);
    }
    private String generateRandomString (int lenght){
        StringBuilder returnValue = new StringBuilder(lenght);
        for (int i = 0; i < lenght; i++){
            returnValue.append(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
