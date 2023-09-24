package org.example;

public class Abeceda {

    //pool s ulozenymi znakmi
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "1234567890";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";

    private final StringBuilder pool;

    //vkladanie jednotlivych znakov do hesla
    public Abeceda(boolean upperCaseInclude, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded){

        pool = new StringBuilder();

        if(upperCaseInclude) pool.append(UPPERCASE_LETTERS);

        if(lowercaseIncluded) pool.append(LOWERCASE_LETTERS);

        if(numbersIncluded) pool.append(NUMBERS);

        if(specialCharactersIncluded) pool.append(SYMBOLS);
    }

    public String getAbeceda(){
        return pool.toString();
    }
}
