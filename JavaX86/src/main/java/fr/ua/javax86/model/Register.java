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

    }
    public static void sub(Register r1,Register r2){

    }
    public static void mul(Register r1,Register r2){

    }
    public static void div(Register r1,Register r2){

    }
}
