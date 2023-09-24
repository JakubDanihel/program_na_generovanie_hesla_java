package org.example;

import java.net.PasswordAuthentication;
import java.util.Objects;
import java.util.Scanner;

public class Generator {

    Abeceda abeceda;
    public static Scanner klavesnica;

    public Generator(Scanner scanner) {
        klavesnica = scanner;
    }

    //vlozenie do generovania pozadovane hodnoty
    public Generator(boolean IncludeUpper, boolean IncludeNum, boolean IncludeSym, boolean IncludeLower) {
        abeceda = new Abeceda(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    //hlavna slucka kodu
    public void mainLoop() {
        System.out.println("Generator hesiel: ");

        printMenu();

        String userOption = "-1";

        //ziskanie pozadovanych informacii pre generovanie hesla
        while (!userOption.equals("4")) {

            userOption = klavesnica.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    chcekPassword();
                    printMenu();
                }
                case "3" -> {
                    printUserFullInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Zvlote jednu z moznosti: ");
                    printMenu();
                }
            }
        }
    }

    //generovanie noveho nahodneho hesla
    private Heslo GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = abeceda.getAbeceda().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(abeceda.getAbeceda().charAt(index));
        }

        return new Heslo(pass.toString());
    }

    //informacie o tom ake je dobre pouzit heslo
    private void printUserFullInfo() {
        System.out.println();
        System.out.println("Pouzivanie minimalneho poctu znakov 8 a viac je povolene");
        System.out.println("Povolene pouzivanie malych a velkych pismen, cisel a symbolov");
        System.out.println("Vygeneruj heslo nahodne ak je to mozne.");
        System.out.println("Vyhni sa pouzivaniu rovnakeho hesla dva a viac krat (napriklad napriec viacerym uctov a/alebo softverom)");
        System.out.println("Vyhni sa pouzivaniu znakov opakovane, klavesovych vzorcov, slov zo slovnika, " + "\nprezyvok, znamych alebo mena domacich zvierat, roznych vztahov, sucastnych alebo minulich" + "\na rozncyh bibliografickych informacii ako je napriklad (ID, mena predtov ale rozne datumy)");
        System.out.println("Vyhni sa pouzivaniu infomacii ktore by mohli tvoji kolegovaia a/alebo " + "spolocnici vediet a spojit si ich s pouzivatelom.");
        System.out.println("Nepouzivaj hesla ktore mozu pozostavat z akejkolvek kombinacie z tychto znakov. Toto je oznacovane ako slabe heslo.");

    }

    //ziskanie informacii o hesle ako by malo byt generovane a co vsetko by malo obsahovat
    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParamas;

        System.out.println();
        System.out.println("Vytaj v generatore nahodneho heslo. Prosim odpovedz na nasledujuce otazky pomocou Ano alebo Nie \n");

        do {
            String vstup;
            correctParamas = false;

            do {
                System.out.print("Chces aby boli pouzite male pismena? ");
                vstup = klavesnica.next();
                PasswordRequestError(vstup);

            } while (!vstup.equalsIgnoreCase("ano") && !vstup.equalsIgnoreCase("nie"));

            if (isInclude(vstup)) IncludeLower = true;

            do {
                System.out.println("Chces aby boli pouzite velke pismena? ");
                vstup = klavesnica.next();
                PasswordRequestError(vstup);
            } while (!vstup.equalsIgnoreCase("ano") && !vstup.equalsIgnoreCase("nie"));

            if (isInclude(vstup)) IncludeUpper = true;

            do {
                System.out.println("Chces pouzit cisla? ");
                vstup = klavesnica.next();
                PasswordRequestError(vstup);

            } while (!vstup.equalsIgnoreCase("ano") && !vstup.equalsIgnoreCase("nie"));

            if (isInclude(vstup)) IncludeNum = true;

            do {
                System.out.println("Chces pouzit specialne znaky? ");
                vstup = klavesnica.next();
                PasswordRequestError(vstup);

            } while (!vstup.equalsIgnoreCase("ano") && !vstup.equalsIgnoreCase("nie"));

            if (isInclude(vstup)) IncludeNum = true;

            //Ak nie je nic zvolene
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("Musis si zvolit aspon jednu z pouzitych moznosti.\n");
                correctParamas = true;
            }
        } while (correctParamas);

        System.out.println("Zadaj pozadovanu dlzku hesla: ");
        int dlzka = klavesnica.nextInt();

        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Heslo heslo = generator.GeneratePassword(dlzka);

        System.out.println("Vytvoril si si heslo ->  " + heslo);
    }

    private void PasswordRequestError(String i) {
        if(!i.equalsIgnoreCase("yes")&&!i.equalsIgnoreCase("no"))

        {
            System.out.println("Vlozil si nieco nespravne. Pouzivaj len ano alebo nie \n");
        }
    }

    //urcenie co vsetko vlozit do hesla
    private boolean isInclude(String Vstup) {
        if (Vstup.equalsIgnoreCase("ano")) {
            return true;
        } else {
            return false;
        }
    }


    //urcenie sily hesla
    private void chcekPassword() {
        String vstup;

        System.out.println("Zadaj heslo: ");
        vstup = klavesnica.next();

        final Heslo h = new Heslo(vstup);
        System.out.println(h.vyratajSkore());
    }

    //zobrazenie menu spolu s moznostami pre volbu toho co chceme
    private void printMenu() {
        System.out.print("Volba: ");
        System.out.println();
        System.out.println("Zadaj 1 - Generovanie hesla");
        System.out.println("Zadaj 2 - Urci silu hesla");
        System.out.println("Zadaj 3 - Dolezite informacie");
        System.out.println("Zadaj 4 - Pre skoncenie");

    }

    private void printQuitMessage() {
        System.out.println("Ukoncenie programu. ");
    }
}