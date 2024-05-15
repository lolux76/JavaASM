package fr.ua.javax86.asm;

import fr.ua.javax86.model.Register;

import java.util.BitSet;

public class ASM {
    //Bitsets
    BitSet bs0;
    BitSet bs1;
    BitSet bs2;
    BitSet bs3;
    BitSet bs4;
    BitSet bs5;
    BitSet bs6;
    BitSet bs7;
    BitSet bs8;
    BitSet bs9;
    BitSet bs10;
    BitSet bs11;
    BitSet bs12;
    BitSet bs13;
    BitSet bs14;
    BitSet bs15;

    //Registres de 128 bits
    Register sse;
    //Registres de 64 bits
    Register rax;
    Register rbx;
    Register rcx;
    Register rdx;
    Register rsi;
    Register rdi;
    Register rbp;
    Register rsp;
    Register r8;
    Register r9;
    Register r10;
    Register r11;
    Register r12;
    Register r13;
    Register r14;
    Register r15;
    //Registres de 32 bits
    Register eax;
    Register ebx;
    Register ecx;
    Register edx;
    Register esi;
    Register edi;
    Register ebp;
    Register esp;
    Register r8d;
    Register r9d;
    Register r10d;
    Register r11d;
    Register r12d;
    Register r13d;
    Register r14d;
    Register r15d;
    //Registres de 16 bits
    Register ax;
    Register bx;
    Register cx;
    Register dx;
    Register si;
    Register di;
    Register bp;
    Register sp;
    Register r8w;
    Register r9w;
    Register r10w;
    Register r11w;
    Register r12w;
    Register r13w;
    Register r14w;
    Register r15w;
    //Registres de 8 bits partie haute
    Register ah;
    Register bh;
    Register ch;
    Register dh;
    //Registres de 8 bits partie basse
    Register al;
    Register bl;
    Register cl;
    Register dl;
    Register sil;
    Register dil;
    Register bpl;
    Register spl;
    Register r8b;
    Register r9b;
    Register r10b;
    Register r11b;
    Register r12b;
    Register r13b;
    Register r14b;
    Register r15b;
    public ASM(){
        //bitsets
        BitSet bs0 = new BitSet(64);
        BitSet bs1 = new BitSet(64);
        BitSet bs2 = new BitSet(64);
        BitSet bs3 = new BitSet(64);
        BitSet bs4 = new BitSet(64);
        BitSet bs5 = new BitSet(64);
        BitSet bs6 = new BitSet(64);
        BitSet bs7 = new BitSet(64);
        BitSet bs8 = new BitSet(64);
        BitSet bs9 = new BitSet(64);
        BitSet bs10 = new BitSet(64);
        BitSet bs11 = new BitSet(64);
        BitSet bs12 = new BitSet(64);
        BitSet bs13 = new BitSet(64);
        BitSet bs14 = new BitSet(64);
        BitSet bs15 = new BitSet(64);

        //64 bits
        rax = new Register("RAX", bs0, 0, 64);
        rbx = new Register("RBX", bs1, 0, 64);
        rcx = new Register("RCX", bs2, 0, 64);
        rdx = new Register("RDX", bs3, 0, 64);
        rsi = new Register("RSI", bs4, 0, 64);
        rdi = new Register("RDI", bs5, 0, 64);
        rbp = new Register("RBP", bs6, 0, 64);
        rsp = new Register("RSP", bs7, 0, 64);
        r8 = new Register("R8", bs8, 0, 64);
        r9 = new Register("R9", bs9, 0, 64);
        r10 = new Register("R10", bs10, 0, 64);
        r11 = new Register("R11", bs11, 0, 64);
        r12 = new Register("R12", bs12, 0, 64);
        r13 = new Register("R13", bs13, 0, 64);
        r14 = new Register("R14", bs14, 0, 64);
        r15 = new Register("R15", bs15, 0, 64);

        //32 bits
        eax = new Register("EAX", bs0, 0, 32);
        ebx = new Register("EBX", bs1, 0, 32);
        ecx = new Register("ECX", bs2, 0, 32);
        edx = new Register("EDX", bs3, 0, 32);
        esi = new Register("ESI", bs4, 0, 32);
        edi = new Register("EDI", bs5, 0, 32);
        ebp = new Register("EBP", bs6, 0, 32);
        esp = new Register("ESP", bs7, 0, 32);
        r8d = new Register("R8D", bs8, 0, 32);
        r9d = new Register("R9D", bs9, 0, 32);
        r10d = new Register("R10D", bs10, 0, 32);
        r11d = new Register("R11D", bs11, 0, 32);
        r12d = new Register("R12D", bs12, 0, 32);
        r13d = new Register("R13D", bs13, 0, 32);
        r14d = new Register("R14D", bs14, 0, 32);
        r15d = new Register("R15D", bs15, 0, 32);
        //16 bits
        ax = new Register("AX", bs0, 0, 16);
        bx = new Register("BX", bs1, 0, 16);
        cx = new Register("CX", bs2, 0, 16);
        dx = new Register("DX", bs3, 0, 16);
        si = new Register("SI", bs4, 0, 16);
        di = new Register("DI", bs5, 0, 16);
        bp = new Register("BP", bs6, 0, 16);
        sp = new Register("SP", bs7, 0, 16);
        r8w = new Register("R8W", bs8, 0, 16);
        r9w = new Register("R9W", bs9, 0, 16);
        r10w = new Register("R10W", bs10, 0, 16);
        r11w = new Register("R11W", bs11, 0, 16);
        r12w = new Register("R12W", bs12, 0, 16);
        r13w = new Register("R13W", bs13, 0, 16);
        r14w = new Register("R14W", bs14, 0, 16);
        r15w = new Register("R15W", bs15, 0, 16);
        //8 bits low
        al = new Register("AL", bs0, 0, 8);
        bl = new Register("BL", bs1, 0, 8);
        cl = new Register("CL", bs2, 0, 8);
        dl = new Register("DL", bs3, 0, 8);
        sil = new Register("SIL", bs4, 0, 8);
        dil = new Register("DIL", bs5, 0, 8);
        bpl = new Register("BPL", bs6, 0, 8);
        spl = new Register("SPL", bs7, 0, 8);
        r8b = new Register("R8B", bs8, 0, 8);
        r9b = new Register("R9B", bs9, 0, 8);
        r10b = new Register("R10B", bs10, 0, 8);
        r11b = new Register("R11B", bs11, 0, 8);
        r12b = new Register("R12B", bs12, 0, 8);
        r13b = new Register("R13B", bs13, 0, 8);
        r14b = new Register("R14B", bs14, 0, 8);
        r15b = new Register("R15B", bs15, 0, 8);
        //8 bits high
        ah = new Register("AH", bs0, 8, 16);
        bh = new Register("BH", bs1, 8, 16);
        ch = new Register("CH", bs2, 8, 16);
        dh = new Register("DH", bs3, 8, 16);
    }

    public Register parsing(String name){
        switch (name){
            case "sse":
                return this.sse;
            case "rax":
                return this.rax;
            case "rbx":
                return this.rbx;
            case "rcx":
                return this.rcx;
            case "rdx":
                return this.rdx;
            case "rsi":
                return this.rsi;
            case "rdi":
                return this.rdi;
            case "rbp":
                return this.rbp;
            case "rsp":
                return this.rsp;
            case "r8":
                return this.r8;
            case "r9":
                return this.r9;
            case "r10":
                return this.r10;
            case "r11":
                return this.r11;
            case "r12":
                return this.r12;
            case "r13":
                return this.r13;
            case "r14":
                return this.r14;
            case "r15":
                return this.r15;
            case "eax":
                return this.eax;
            case "ebx":
                return this.ebx;
            case "ecx":
                return this.ecx;
            case "edx":
                return this.edx;
            case "esi":
                return this.esi;
            case "edi":
                return this.edi;
            case "ebp":
                return this.ebp;
            case "esp":
                return this.esp;
            case "r8d":
                return this.r8d;
            case "r9d":
                return this.r9d;
            case "r10d":
                return this.r10d;
            case "r11d":
                return this.r11d;
            case "r12d":
                return this.r12d;
            case "r13d":
                return this.r13d;
            case "r14d":
                return this.r14d;
            case "r15d":
                return this.r15d;
            case "ax":
                return this.ax;
            case "bx":
                return this.bx;
            case "cx":
                return this.cx;
            case "dx":
                return this.dx;
            case "si":
                return this.si;
            case "di":
                return this.di;
            case "bp":
                return this.bp;
            case "sp":
                return this.sp;
            case "r8w":
                return this.r8w;
            case "r9w":
                return this.r9w;
            case "r10w":
                return this.r10w;
            case "r11w":
                return this.r11w;
            case "r12w":
                return this.r12w;
            case "r13w":
                return this.r13w;
            case "r14w":
                return this.r14w;
            case "r15w":
                return this.r15w;
            case "al":
                return this.al;
            case "bl":
                return this.bl;
            case "cl":
                return this.cl;
            case "dl":
                return this.dl;
            case "sil":
                return this.sil;
            case "dil":
                return this.dil;
            case "bpl":
                return this.bpl;
            case "spl":
                return this.spl;
            case "r8b":
                return this.r8b;
            case "r9b":
                return this.r9b;
            case "r10b":
                return this.r10b;
            case "r11b":
                return this.r11b;
            case "r12b":
                return this.r12b;
            case "r13b":
                return this.r13b;
            case "r14b":
                return this.r14b;
            case "r15b":
                return this.r15b;
            case "ah":
                return this.ah;
            case "bh":
                return this.bh;
            case "ch":
                return this.ch;
            case "dh":
                return this.dh;
            default:
                return null;
        }
    }


    public void mov(String r1, int value){
        Register Registre1 = this.parsing(r1);
        Registre1.mov(value);
    }

    public void shl(String r1, int value){
        Register Registre1 = this.parsing(r1);
        Registre1.shl(value);
    }

    //Opérations mathématiques
    public void add(String r1, String r2){
        Register Registre1 = this.parsing(r1);
        Register Registre2 = this.parsing(r2);
        Register.add(Registre1, Registre2);
    }
    public void sub(String r1, String r2){
        Register Registre1 = this.parsing(r1);
        Register Registre2 = this.parsing(r2);
        Register.sub(Registre1, Registre2);
    }
    public void mul(String r1){
        Register Registre1 = this.parsing(r1);
        switch (Registre1.getSize()){
            case 8 :
                Register.mul(this.ax, this.al, Registre1);
                break;
            case 16 :
                Register.mul(this.dx, this.ax, Registre1);
                break;
            default:
                Register.mul(this.edx, this.eax, Registre1);
                break;
        }
    }
    public void div(String r1){
        Register Registre1 = this.parsing(r1);
        switch (Registre1.getSize()){
            case 8 :
                //Register.div(this.ax, this.al, Registre1);
                break;
            case 16 :
                //Register.div(this.dx, this.ax, Registre1);
                break;
            default:
                Register.div(this.eax, this.edx, Registre1, this.edx);
                break;
        }
    }
    //Opérations logiques
    public void and(String r1, String r2){
        Register Registre1 = this.parsing(r1);
        Register Registre2 = this.parsing(r2);
        Register.and(Registre1, Registre2);
    }
    public void or(String r1, String r2){
        Register Registre1 = this.parsing(r1);
        Register Registre2 = this.parsing(r2);
        Register.or(Registre1, Registre2);
    }
    public void xor(String r1, String r2){
        Register Registre1 = this.parsing(r1);
        Register Registre2 = this.parsing(r2);
        Register.xor(Registre1, Registre2);
    }
    public void not(String r1){
        Register Registre1 = this.parsing(r1);
        Register.not(Registre1);
    }
    //
    public Long toSigned(String r1){
        Register Registre1 = this.parsing(r1);
        return Registre1.toSigned();
    }
    //
    public Long toUnsigned(String r1){
        Register Registre1 = this.parsing(r1);
        return Registre1.toUnsigned();
    }
    //
    public String toHex(String r1){
        Register Registre1 = this.parsing(r1);
        return Registre1.toHex();
    }
    //
    public String toOct(String r1){
        Register Registre1 = this.parsing(r1);
        return Registre1.toOct();
    }
    //
    public String toString(String r1){
        Register Registre1 = this.parsing(r1);
        return Registre1.toString();
    }

}
