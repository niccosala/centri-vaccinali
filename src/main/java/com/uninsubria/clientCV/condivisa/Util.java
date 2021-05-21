/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.condivisa;

import com.uninsubria.serverCV.Proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class contains multiple tools used into the application
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class Util {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random rng = new SecureRandom();

    /**
     * Email is valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean emailIsValid(String email) {

        String EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Cf is valid: check if given CF is valid.
     *
     * @param CF the cf
     * @return the boolean
     */
    public boolean cfIsValid(String CF) {
        return CF.matches("^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]" +
                "|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|" +
                "[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]+$");
    }

    /**
     * Lowercase not first string.
     *
     * @param str the str
     * @return the string
     */
    public String lowercaseNotFirst(String str) {
        if (str.isBlank())
            return "";

        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
    }

    /**
     * Format CentroVaccinale's name to valid CentroVaccinale's table name
     *
     * @param tableName the string to be formatted
     * @return the formatted string
     */
    public String formatTableName(String tableName) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < tableName.length(); i++) {
            if(tableName.charAt(i) != ' ')
                name.append(tableName.charAt(i));
            else
                name.append('_');
        }
        return name.toString();
    }

    /**
     * Populate Database at first launch
     *
     * @return if DB needed to be populated
     */
    public boolean populateDatabase() throws IOException, SQLException {
        Proxy proxyCheck = new Proxy();
        Proxy proxyPopulate = new Proxy();

        String queryCheck = "SELECT idsegnalazione FROM segnalazione";
        ArrayList<String> segnalazioni = proxyCheck.getSingleValues(queryCheck, "idsegnalazione");

        if(segnalazioni.size() > 9)
            return false;

        StringBuilder queryPopulate = new StringBuilder();
        try {
            File entriesFile = new File("src/main/resources/com/uninsubria/sql/DefaultEntries.sql");
            Scanner scanner = new Scanner(entriesFile);
            while (scanner.hasNextLine())
                queryPopulate.append(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        proxyPopulate.insertDb(queryPopulate.toString());

        System.out.println("> First launch: default data generated.");
        return true;
    }

}
