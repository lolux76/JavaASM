package fr.ua.javax86.model;

import java.util.BitSet;
import java.util.Stack;

public class AsmStack {
    private Stack<BitSet> stack;

    public AsmStack(){
        stack = new Stack<BitSet>();
    }

    public void push(BitSet bitSet){
        stack.push(bitSet);
    }

    public BitSet pop(){
        return stack.pop();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
