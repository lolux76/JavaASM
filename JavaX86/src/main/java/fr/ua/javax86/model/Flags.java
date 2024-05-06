package fr.ua.javax86.model;

import java.util.BitSet;

public class Flags{

    private final BitSet bitSet;
    private static Flags instance;

    private Flags(){
        this.bitSet = new BitSet(16);
    }

    public static Flags getFlags(){
        if(instance == null){
            instance = new Flags();
        }
        return instance;
    }

    public void setZeroFlag(boolean value){
        bitSet.set(6, value);
    }

    public void setCarryFlag(boolean value){
        bitSet.set(0, value);
    }

    public void setParityFlag(boolean value){
        bitSet.set(2,value);
    }

    public void setOverflowFlag(boolean value){
        bitSet.set(11, value);
    }

    public void setSignFlag(boolean value){
        bitSet.set(7, value);
    }

    public boolean getZeroFlag(){
        return bitSet.get(6);
    }

    public boolean getCarryFlag(){
        return bitSet.get(0);
    }

    public boolean getParityFlag(){
        return bitSet.get(2);
    }

    public boolean getOverflowFlag(){
        return bitSet.get(11);
    }

    public boolean getSignFlag(){
        return bitSet.get(7);
    }

    @Override
    public String toString() {
        return "Zero : " + bitSet.get(6)
                + "\nCarry : " + bitSet.get(0)
                + "\nOverflow : " + bitSet.get(11)
                + "\nParity : " + bitSet.get(2)
                + "\nSign : " + bitSet.get(7);
    }
}
