package com.uninsubria.clientCV.condivisa;

import java.security.SecureRandom;

public class IDGenerator {

    public void main() {
        System.out.println(bytesToDecimal());
        System.out.println(bytesToHex());
    }


    final protected static char[] decimalArray = "0123456789".toCharArray();

    public static String bytesToDecimal() {
        byte[] bytes = new byte[2];
        new SecureRandom().nextBytes(bytes);

        char[] decimalChars = new char[bytes.length * 4];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            decimalChars[j * 4] = decimalArray[v / 100];
            decimalChars[j * 4 + 1] = decimalArray[(v / 10) % 10];
            decimalChars[j * 4 + 2] = decimalArray[v % 10];
            if(j == 1)
                break;
            decimalChars[j * 4 + 3] = '-';
        }
        return new String(decimalChars);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex() {
        byte[] bytes = new byte[2];
        new SecureRandom().nextBytes(bytes);

        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
