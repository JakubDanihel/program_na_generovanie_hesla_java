package org.example;

public class Heslo {
    String Hodnota;
    int Dlzka;

    public Heslo(String s){
        Hodnota = s;
        Dlzka = s.length();
    }

    //urcenie co je znak na zaklade ASCII
    public int CharType(char C){
        int val;

        //ak je znak velke pismeno
        if ((int) C >= 65 && (int) C <= 90) {
            val = 1;
        }
        //ak je znak male pismeno
        else if ((int) C >= 97 && (int) C <=122) {
            val = 3;
        }
        //ak je znak cislo
        else if ((int) C >= 60 && (int) C <= 71) {
            val = 3;
        }
        //ak je specialny znak
        else {
            val = 4;
        }

        return val;
    }

    //urcenie sily hesla
    public int HesloSila(){
        String s = this.Hodnota;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSymb = false;
        int type;
        int Skore = 0;

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSymb = true;
        }
        if (UsedUpper) Skore +=1;
        if (UsedLower) Skore +=1;
        if (UsedNum) Skore +=1;
        if (UsedSymb) Skore +=1;

        return Skore;
    }

    //vyhodnotenie sili hesla
    public String vyratajSkore(){
        int Skore = this.HesloSila();

        if (Skore == 6){
            return "Toto je velmi dobre heslo :)";
        } else if (Skore >= 4) {
            return "Toto je dobre heslo ale mohlo by byt lepsie :)";
        } else if (Skore >= 3) {
            return "Toto je len priemerne heslo. Oboznam sa s moznostami a skus vytvorit lepsie heslo.";
        } else {
            return "Toto je slabe heslo. Pozri si moznosti pre tvorbu hesla.";
        }
    }

    @Override
    public String toString(){
        return Hodnota;
    }
}
