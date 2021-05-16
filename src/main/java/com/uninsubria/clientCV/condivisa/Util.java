/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.condivisa;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random rng = new SecureRandom();

    public boolean emailIsValid(String email) {

        String EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean cfIsValid(String CF) {
        return CF.matches("^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]" +
                "|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|" +
                "[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]+$");
    }

    public String lowercaseNotFirst(String str) {
        if (str.isBlank())
            return "";

        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
    }



    public char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }


    //possibilità di generare sempre due ID diversi è dello 99.99999999999999999987%
    public String randomUUID(int length, int spacing, char spacerChar){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(length > 0){
            if(spacer == spacing){
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }

    public String randomUUID16() {
        byte[] unique = new byte[2];
        new SecureRandom().nextBytes(unique);

        converBytesToHex(unique);

        return unique.toString();
    }

    private String converBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte tmp : bytes)
            result.append(String.format("%02x", tmp));

        return result.toString();
    }


}
