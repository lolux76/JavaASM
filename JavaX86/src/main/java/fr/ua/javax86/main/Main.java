package fr.ua.javax86.main;
import fr.ua.javax86.asm.ASM;
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
        System.out.println("Hello world!");

        ASM asm = new ASM();

        asm.mov("dh", 2);
        asm.mov("eax", 15);

        System.out.println("dh : " + (asm.toUnsigned("dh")));
        System.out.println("eax : " + (asm.toUnsigned("eax")));

        System.out.println(asm.toString("dh"));

        asm.shl("dh", 1);

        System.out.println("Décalage de dh de 1 bit à gauche : " + asm.toUnsigned("dh"));
        System.out.println(asm.toString("dh"));

        asm.mul("dh");

        System.out.println("4 * 15: " + asm.toUnsigned("edx"));
        System.out.println(asm.toString("edx"));

        asm.div("dh");

        System.out.println("60 : 4 : " + asm.toUnsigned("eax"));
        System.out.println("reste : " + asm.toUnsigned("edx"));
    }
}