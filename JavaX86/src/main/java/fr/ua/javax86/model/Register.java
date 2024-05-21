package fr.ua.javax86.model;

import java.util.BitSet;

public class Register {

    String name;
    BitSet arrayOfBit;
    int debut;
    int fin;
    Register partieHaute;
    Register partieBasse;

    public Register(String name, BitSet arrayOfBit, int debut, int fin){
        this.name=name;
        this.arrayOfBit=arrayOfBit.get(debut, fin);
        this.debut=debut;
        this.fin=fin;
        this.partieBasse = new Register(name+"basse",arrayOfBit.get(debut,fin/2),debut, fin/2, true);
        this.partieHaute = new Register(name+"haute",arrayOfBit.get(fin/2,fin),fin/2, fin, true);
    }
    public Register(String name, BitSet arrayOfBit, int debut, int fin, boolean pourConstructeur) {
        this.name = name;
        this.arrayOfBit = arrayOfBit.get(debut, fin);
        this.debut = debut;
        this.fin = fin;
    }
    public Register(String name, BitSet arrayOfBit, int debut, int fin,int value){
        this.name=name;
        this.arrayOfBit=arrayOfBit;
        this.debut=debut;
        this.fin=fin;
        int i=0;
        this.arrayOfBit = BitSet.valueOf(new long[] {value});
    }
    public Register getPartieBasse(){
        return this.partieBasse;
    }
    public Register getPartieHaute(){
        return this.partieHaute;
    }

    public static void setValue(Register r1,int value){
        int i=0;
        while (value != 0) {
            if (value % 2 == 1) {
                r1.arrayOfBit.set(i);
            }
            value /= 2;
            i++;
        }
    }

    public static void add(Register r1,Register r2){
        boolean retenue=false;
        boolean signed = r1.getArrayOfBit().get(r1.fin - 1) && r2.getArrayOfBit().get(r2.fin - 1);

        for(int i=0;i<r1.arrayOfBit.size();i++){
            boolean a1 = r1.arrayOfBit.get(i);
            r1.arrayOfBit.set(i,r1.arrayOfBit.get(i) ^ r2.arrayOfBit.get(i) ^ retenue); //addition de chaque bit 1 à 1
            retenue = (a1 & retenue) | (r2.arrayOfBit.get(i) & retenue) | (a1 & r2.arrayOfBit.get(i)); //Retenue si au moins 2 bit à 1
        }

        //Carry Flag
        Flags.getFlags().setCarryFlag(retenue);

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != r1.getArrayOfBit().get(r1.fin - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

    }

    //A revoir car il s'agit de l'addition du registre 1 au complémentaire a 2 du registre 2
    public static void sub(Register r1,Register r2){
        BitSet retenue = new BitSet(r1.getSize());
        boolean signed = r1.getArrayOfBit().get(r1.fin - 1) && r2.getArrayOfBit().get(r2.fin - 1);
        for (int i = 0; i < r1.getSize(); i++) {
            boolean r1Bis = r1.arrayOfBit.get(i);
            boolean r2Bis = r2.arrayOfBit.get(i);
            boolean retenusBis = retenue.get(i);

            // Calculate the current result bit
            boolean resultBit = (r1Bis ^ r2Bis) ^ retenusBis;
            r1.arrayOfBit.set(i, resultBit);

            // Calculate the borrow for the next bit
            boolean newBorrow = (!r1Bis && (r2Bis || retenusBis)) || (r2Bis && retenusBis);
            if (newBorrow) {
                retenue.set(i + 1);
            }
        }
        //Carry Flag
        Flags.getFlags().setCarryFlag(retenue.get(r1.getSize()));

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != r1.getArrayOfBit().get(r1.fin - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);
    }

    public static void mul(Register ax, Register al, Register operande){
        boolean signed = al.getArrayOfBit().get(al.fin - 1) && operande.getArrayOfBit().get(operande.fin - 1);

        ax.getArrayOfBit().clear();

        for (int i = 0; i < 8; i++) {
            if (operande.getArrayOfBit().get(i)) {
                BitSet shifted = new BitSet(8);
                shifted.or(al.getArrayOfBit());
                ax.getArrayOfBit().xor(shifted);
            }
            al.shl(1);
        }

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = ax.debut; i < ax.fin; i++){
            if(ax.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != ax.getArrayOfBit().get(ax.fin - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(ax.getArrayOfBit().get(ax.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = ax.debut; i < ax.fin; i++){
            if(ax.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(!ax.isHomogeneous());
    }

    public static void div(Register quotient, Register dividend, Register divisor, Register remainder){
        boolean signed = dividend.getArrayOfBit().get(dividend.fin - 1) && divisor.getArrayOfBit().get(divisor.fin - 1);

        // Vérifier si le diviseur est zéro
        if (divisor.getArrayOfBit().isEmpty()) {
            throw new ArithmeticException("Division by zero");
        }

        // Initialise le reste avec le dividende
        remainder.setArrayOfBit(dividend.getArrayOfBit());

        // Initialise le quotient avec zéro
        quotient.getArrayOfBit().clear();

        // Trouver le bit le plus significatif du diviseur
        int msbDivisor = findMostSignificantBit(divisor.getArrayOfBit());

        // Parcourir le dividende
        for (int i = findMostSignificantBit(remainder.getArrayOfBit()); i >= msbDivisor; i--) {
            // Décalage du reste vers la droite d'un bit
            remainder.shr(1);

            // Définir le bit de droite du reste au même que le bit correspondant du dividende
            if (dividend.getArrayOfBit().get(i)) {
                remainder.getArrayOfBit().set(0);
            } else {
                remainder.getArrayOfBit().clear(0);
            }

            // Si le reste est plus grand ou égal au diviseur, soustraire et mettre le bit de quotient à 1
            if (cmp(remainder, divisor) >= 0) {
                quotient.getArrayOfBit().set(i - msbDivisor);
                sub(remainder, divisor);
            }
        }

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = quotient.debut; i < quotient.fin; i++){
            if(quotient.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != quotient.getArrayOfBit().get(quotient.fin - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(quotient.getArrayOfBit().get(quotient.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = quotient.debut; i < quotient.fin; i++){
            if(quotient.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(!quotient.isHomogeneous());
    }

    public static void and(Register r1,Register r2){
        r1.arrayOfBit.and(r2.arrayOfBit);

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        Flags.getFlags().setOverflowFlag(false);

        //Sign Flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(false);
    }
    public static void or(Register r1,Register r2){
        r1.arrayOfBit.or(r2.arrayOfBit);

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        Flags.getFlags().setOverflowFlag(false);

        //Sign Flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(false);
    }
    public static void xor(Register r1,Register r2){
        r1.arrayOfBit.xor(r2.arrayOfBit);

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        Flags.getFlags().setOverflowFlag(false);

        //Sign Flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = r1.debut; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(false);
    }

    public BitSet getArrayOfBit() {
        return arrayOfBit;
    }

    public void setArrayOfBit(BitSet bitSet) {
        for(int i = 0; i < bitSet.size(); i++){
            this.arrayOfBit.set(i, bitSet.get(i));
        }
    }

    public void mov(int value){
        this.arrayOfBit = BitSet.valueOf(new long[] {value});

        //Zero Flag
        Flags.getFlags().setZeroFlag(value == 0);

        //Overflow Flag
        Flags.getFlags().setOverflowFlag(false);

        //Sign Flag
        Flags.getFlags().setSignFlag(this.arrayOfBit.get(this.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = this.debut; i < this.fin; i++){
            if(this.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(false);
    }

    public void shl(int distance){
        boolean signed = this.arrayOfBit.get(this.fin - 1);

        //Carry Flag
        Flags.getFlags().setCarryFlag(this.arrayOfBit.get(this.fin - 1));

        for (int i = this.arrayOfBit.size() - 1; i >= distance; i--) {
            this.arrayOfBit.set(i, this.arrayOfBit.get(i - distance));
        }
        for (int i = distance - 1; i >= 0; i--) {
            this.arrayOfBit.set(i, false);
        }

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = this.debut; i < this.fin; i++){
            if(this.arrayOfBit.get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != this.arrayOfBit.get(this.fin - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(this.arrayOfBit.get(this.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = this.debut; i < this.fin; i++){
            if(this.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);
    }

    public static void shl(BitSet bitset, int distance) {
        boolean signed = bitset.get(bitset.size() - 1);

        //Carry Flag
        Flags.getFlags().setCarryFlag(bitset.get(bitset.size() - 1));

        for (int i = bitset.size() - 1; i >= distance; i--) {
            bitset.set(i, bitset.get(i - distance));
        }
        for (int i = distance - 1; i >= 0; i--) {
            bitset.set(i, false);
        }

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = 0; i < bitset.size() - 1; i++){
            if(bitset.get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != bitset.get(bitset.size() - 1)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(bitset.get(bitset.size() - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = 0; i < bitset.size(); i++){
            if(bitset.get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);
    }

    public void shr(int distance) {
        boolean signed = this.getArrayOfBit().get(this.fin - 1);

        //Carry Flag
        Flags.getFlags().setCarryFlag(this.getArrayOfBit().get(this.debut));

        for (int i = 0; i < this.arrayOfBit.size() - distance; i++) {
            this.arrayOfBit.set(i, this.arrayOfBit.get(i + distance));
        }
        for (int i = this.arrayOfBit.size() - distance; i < this.arrayOfBit.size(); i++) {
            this.arrayOfBit.set(i, false);
        }

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = 0; i < this.fin - 1; i++){
            if(this.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Overflow Flag
        if(signed != this.getArrayOfBit().get(this.debut)){
            Flags.getFlags().setOverflowFlag(true);
        }
        else{
            Flags.getFlags().setOverflowFlag(false);
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(this.getArrayOfBit().get(this.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = 0; i < this.fin; i++){
            if(this.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);
    }

    public static int cmp(Register r1, Register r2) {
        int msb1 = findMostSignificantBit(r1.getArrayOfBit());
        int msb2 = findMostSignificantBit(r2.getArrayOfBit());

        Flags.getFlags().setZeroFlag(false);
        Flags.getFlags().setCarryFlag(false);

        if (msb1 < msb2) {
            return -1;
        } else if (msb1 > msb2) {
            return 1;
        }

        for (int i = msb1; i >= 0; i--) {
            boolean bit1 = r1.getArrayOfBit().get(i);
            boolean bit2 = r2.getArrayOfBit().get(i);
            if (bit1 != bit2) {
                if(!bit1 && bit2)
                {
                    Flags.getFlags().setCarryFlag(true);
                }
                return bit1 ? 1 : -1;
            }
        }
        Flags.getFlags().setZeroFlag(true);
        return 0;
    }

    public static void not(Register r1){
        r1.arrayOfBit.flip(0,r1.arrayOfBit.size());

        //Zero Flag
        Flags.getFlags().setZeroFlag(true);
        for(int i = 0; i < r1.fin - 1; i++){
            if(r1.getArrayOfBit().get(i)){
                Flags.getFlags().setZeroFlag(false);
            }
        }

        //Sign Flag
        Flags.getFlags().setSignFlag(r1.getArrayOfBit().get(r1.fin - 1));

        //Parity Flag
        int compteur = 0;
        for(int i = 0; i < r1.fin; i++){
            if(r1.getArrayOfBit().get(i)){
                compteur++;
            }
        }
        Flags.getFlags().setParityFlag(compteur % 2 == 0);

        //Carry Flag
        Flags.getFlags().setCarryFlag(Flags.getFlags().getZeroFlag());
    }

    public static int findMostSignificantBit(BitSet bs) {
        for (int i = bs.length() - 1; i >= 0; i--) {
            if (bs.get(i)) {
                return i;
            }
        }
        return -1; // BitSet est zéro
    }

    protected boolean isHomogeneous() {
        boolean firstBit = this.getArrayOfBit().get(this.debut);
        for (int i = this.debut + 1; i < this.fin; i++) {
            if (this.getArrayOfBit().get(i) != firstBit) {
                return false;
            }
        }
        return true;
    }

    //
    public long toSigned(){
        BitSet aob = this.arrayOfBit;
        long value = 0L;

        for (int i = 0; i < aob.length(); ++i) {
            if (aob.get(i)) {
                value |= (1L << i); // Met à 1 le bit correspondant dans value
            }
        }

        // Si le bit de signe est à 1, alors il s'agit d'un nombre négatif
        if (aob.get(this.fin)) {
            // Calcul du complément à deux pour obtenir la valeur négative
            value = -((1L << aob.length()) - value);
            value += 1;
        }

        return value;
    }
    //
    public long toUnsigned(){
        BitSet aob = this.arrayOfBit;
        long value = 0L;

        for (int i = 0; i < aob.length(); ++i) {
            value += aob.get(i) ? (1L << i) : 0L; //Rajoute un bit à 1 dans value, sinon rajoute un 0 à la i-ème poisition
        }
        return value;
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int white_space = 0;
        for (int i = this.arrayOfBit.size() - 1; i >= 0; i--) {
            sb.append(this.arrayOfBit.get(i) ? '1' : '0');
            white_space++;
            if(white_space == 4){
                white_space = 0;
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public int getSize(){
        return this.arrayOfBit.size();
    }

    public static void toComp(Register r1){
        Register.not(r1);
        Register.add(r1,new Register("add",new BitSet(r1.arrayOfBit.size()),0,r1.arrayOfBit.size(),1));
    }
}
