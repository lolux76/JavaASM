package fr.ua.javax86.asm;

import fr.ua.javax86.model.Register;

import java.util.BitSet;

public class ASM {
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
        sse = new Register("SSE", new BitSet(128), 0, 128);
        rax = new Register("RAX", new BitSet(64), 0, 64);
        rbx = new Register("RBX", new BitSet(64), 0, 64);
        rcx = new Register("RCX", new BitSet(64), 0, 64);
        rdx = new Register("RDX", new BitSet(64), 0, 64);
        rsi = new Register("RSI", new BitSet(64), 0, 64);
        rdi = new Register("RDI", new BitSet(64), 0, 64);
        rbp = new Register("RBP", new BitSet(64), 0, 64);
        rsp = new Register("RSP", new BitSet(64), 0, 64);
        r8 = new Register("R8", new BitSet(64), 0, 64);
        r9 = new Register("R9", new BitSet(64), 0, 64);
        r10 = new Register("R10", new BitSet(64), 0, 64);
        r11 = new Register("R11", new BitSet(64), 0, 64);
        r12 = new Register("R12", new BitSet(64), 0, 64);
        r13 = new Register("R13", new BitSet(64), 0, 64);
        r14 = new Register("R14", new BitSet(64), 0, 64);
        r15 = new Register("R15", new BitSet(64), 0, 64);
        eax = new Register("EAX", new BitSet(32), 0, 32);
        ebx = new Register("EBX", new BitSet(32), 0, 32);
        ecx = new Register("ECX", new BitSet(32), 0, 32);
        edx = new Register("EDX", new BitSet(32), 0, 32);
        esi = new Register("ESI", new BitSet(32), 0, 32);
        edi = new Register("EDI", new BitSet(32), 0, 32);
        ebp = new Register("EBP", new BitSet(32), 0, 32);
        esp = new Register("ESP", new BitSet(32), 0, 32);
        r8d = new Register("R8D", new BitSet(32), 0, 32);
        r9d = new Register("R9D", new BitSet(32), 0, 32);
        r10d = new Register("R10D", new BitSet(32), 0, 32);
        r11d = new Register("R11D", new BitSet(32), 0, 32);
        r12d = new Register("R12D", new BitSet(32), 0, 32);
        r13d = new Register("R13D", new BitSet(32), 0, 32);
        r14d = new Register("R14D", new BitSet(32), 0, 32);
        r15d = new Register("R15D", new BitSet(32), 0, 32);
        ax = new Register("AX", new BitSet(16), 0, 16);
        bx = new Register("BX", new BitSet(16), 0, 16);
        cx = new Register("CX", new BitSet(16), 0, 16);
        dx = new Register("DX", new BitSet(16), 0, 16);
        si = new Register("SI", new BitSet(16), 0, 16);
        di = new Register("DI", new BitSet(16), 0, 16);
        bp = new Register("BP", new BitSet(16), 0, 16);
        sp = new Register("SP", new BitSet(16), 0, 16);
        r8w = new Register("R8W", new BitSet(16), 0, 16);
        r9w = new Register("R9W", new BitSet(16), 0, 16);
        r10w = new Register("R10W", new BitSet(16), 0, 16);
        r11w = new Register("R11W", new BitSet(16), 0, 16);
        r12w = new Register("R12W", new BitSet(16), 0, 16);
        r13w = new Register("R13W", new BitSet(16), 0, 16);
        r14w = new Register("R14W", new BitSet(16), 0, 16);
        r15w = new Register("R15W", new BitSet(16), 0, 16);
        al = new Register("AL", new BitSet(8), 0, 8);
        bl = new Register("BL", new BitSet(8), 0, 8);
        cl = new Register("CL", new BitSet(8), 0, 8);
        dl = new Register("DL", new BitSet(8), 0, 8);
        sil = new Register("SIL", new BitSet(8), 0, 8);
        dil = new Register("DIL", new BitSet(8), 0, 8);
        bpl = new Register("BPL", new BitSet(8), 0, 8);
        spl = new Register("SPL", new BitSet(8), 0, 8);
        r8b = new Register("R8B", new BitSet(8), 0, 8);
        r9b = new Register("R9B", new BitSet(8), 0, 8);
        r10b = new Register("R10B", new BitSet(8), 0, 8);
        r11b = new Register("R11B", new BitSet(8), 0, 8);
        r12b = new Register("R12B", new BitSet(8), 0, 8);
        r13b = new Register("R13B", new BitSet(8), 0, 8);
        r14b = new Register("R14B", new BitSet(8), 0, 8);
        r15b = new Register("R15B", new BitSet(8), 0, 8);
        ah = new Register("AH", new BitSet(8), 8, 16);
        bh = new Register("BH", new BitSet(8), 8, 16);
        ch = new Register("CH", new BitSet(8), 8, 16);
        dh = new Register("DH", new BitSet(8), 8, 16);
    }

    Register parsing(String name){
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
        }
        return null;
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
        Register.mul(this.eax, Registre1, this.edx);
    }
    public void div(String r1){
        Register Registre1 = this.parsing(r1);
        Register.div(this.eax, Registre1, this.edx);
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
