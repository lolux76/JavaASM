package fr.ua.javax86.main;

import fr.ua.javax86.model.Register;

import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        Register r1 = new Register("r1",new BitSet(8),0,8,13);
        System.out.println("r1 = "+r1.toString());
        Register r2 = new Register("r1",new BitSet(8),0,8,5);
        System.out.println("r2 = "+r2.toString());
        Register.add(r1,r2);
        System.out.println("r1+r2 = "+r1.toString());
    }
}