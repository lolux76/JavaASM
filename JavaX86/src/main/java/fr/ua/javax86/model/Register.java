package fr.ua.javax86.model;

import java.util.BitSet;

public class Register {

    String name;
    BitSet arrayOfBit;
    int debut;
    int fin;

    Register(String name, BitSet arrayOfBit,int debut,int fin){
        this.name=name;
        this.arrayOfBit=arrayOfBit;
        this.debut=debut;
        this.fin=fin;
    }
}
