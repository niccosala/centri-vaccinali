package com.uninsubria.clientCV.condivisa.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
