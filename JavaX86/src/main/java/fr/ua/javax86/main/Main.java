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

        asm.mov("ah", -2);
        asm.mov("ebx", -2);

        System.out.println("ah (-2) : " + (asm.toString("ah")));
        System.out.println("ebx (-2) : " + (asm.toString("ebx")));
        System.out.println("ah (-2) en unsigned : " + (asm.toUnsigned("ah")));
        System.out.println("ebx (-2) en unsigned : " + (asm.toUnsigned("ebx")));
        System.out.println("ah (-2) en signed : " + (asm.toSigned("ah")));
        System.out.println("ebx (-2) en signed : " + (asm.toSigned("ebx")));

        asm.mov("dh", 2);
        asm.mov("eax", 15);

        System.out.println("dh : " + (asm.toUnsigned("dh")));
        System.out.println("eax : " + (asm.toUnsigned("eax")));

        System.out.println(asm.toString("dh"));

        asm.shl("dh", 1);

        System.out.println("Décalage de dh de 1 bit à gauche : " + asm.toUnsigned("dh"));
        System.out.println(asm.toString("dh"));



        asm.mov("ebx", -3);
        asm.mov("eax", 12);
        System.out.println("ebx : " + (asm.toString("ebx")));
        System.out.println("eax : " + (asm.toString("eax")));
        System.out.println("ebx : " + (asm.toSigned("ebx")));
        System.out.println("eax : " + (asm.toSigned("eax")));
        System.out.println("ebx : " + (asm.toString("ebx")));
        System.out.println("eax : " + (asm.toString("eax")));


        asm.mul("ebx");

        System.out.println("-3 * 12: " + asm.toSigned("eax"));
        System.out.println(asm.toString("eax"));
        System.out.println(asm.toString("edx"));

        asm.div("dh");

        System.out.println("60 : 4 : " + asm.toUnsigned("eax"));
        System.out.println("reste : " + asm.toUnsigned("edx"));
    }
}