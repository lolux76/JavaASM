package fr.ua.javax86.model;

import java.util.BitSet;

public class Register {

    String name;
    BitSet arrayOfBit;
    int debut;
    int fin;

    public Register(String name, BitSet arrayOfBit, int debut, int fin){
        this.name=name;
        this.arrayOfBit=arrayOfBit;
        this.debut=debut;
        this.fin=fin;
    }

    public static void add(Register r1,Register r2){
        boolean retenue=false;
        for(int i=0;i<r1.arrayOfBit.size();i++){
            boolean a1 = r1.arrayOfBit.get(i);
            r1.arrayOfBit.set(i,r1.arrayOfBit.get(i) ^ r2.arrayOfBit.get(i) ^ retenue); //addition de chaque bit 1 à 1
            retenue = (a1 & retenue) | (r2.arrayOfBit.get(i) & retenue) | (a1 & r2.arrayOfBit.get(i)); //Retenue si au moins 2 bit à 1
        }
        // Pour plus tard si carry=true alors overflow
    }
    public static void sub(Register r1,Register r2){
    }
    public static void mul(Register r1,Register r2){

    }
    public static void div(Register r1,Register r2){

    }
    public static void and(Register r1,Register r2){
        r1.arrayOfBit.and(r2.arrayOfBit);
    }
    public static void or(Register r1,Register r2){
        r1.arrayOfBit.or(r2.arrayOfBit);
    }
    public static void xor(Register r1,Register r2){
        r1.arrayOfBit.xor(r2.arrayOfBit);
    }

    //
    public long toSigned(){
        BitSet aob = this.arrayOfBit;
        long value = 0L;

        for (int i = 0; i < aob.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L; //Rajoute un bit à 1 dans value, sinon rajoute un 0 à la i-ème poisition
            if (value >= (1L << (aob.length() - 1))) {
                value -= (1L << aob.length());
            }
        }

        return value
    }
    //
    public static long toUnsigned(){
        BitSet aob = this.arrayOfBit;
        long value = 0L;

        for (int i = 0; i < aob.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L; //Rajoute un bit à 1 dans value, sinon rajoute un 0 à la i-ème poisition
        }
        return value
    }
    //
    public String toHex() {
        long value = toUnsigned(); // Utiliser la méthode toUnsigned() pour obtenir la valeur non signée
        return Long.toHexString(value);
    }
    //
    public String toOct() {
        long value = toUnsigned(); // Utiliser la méthode toUnsigned() pour obtenir la valeur non signée
        return Long.toOctalString(value);
    }
    //
    public static String toString(String r1){
        BitSet aob = this.arrayOfBit;
        String value = "";

        for (int i = 0; i < aob.length(); ++i) {
            value += bits.get(i)
        }
        return value
    }
}
