package fr.ua.javax86.model;

import fr.ua.javax86.exceptions.EmptyStackException;
import fr.ua.javax86.exceptions.FullStackException;

import java.util.BitSet;
import java.util.Stack;

public class AsmStack {
    private final Stack<BitSet> stack;
    private final long size;
    private long actualSize;

    public AsmStack(){
        stack = new Stack<BitSet>();
        this.size = 64000;
        this.actualSize = 0;
    }

    public AsmStack(long stackSize){
        stack = new Stack<BitSet>();
        this.size = stackSize;
        this.actualSize = 0;
    }

    public void push(BitSet bitSet) throws FullStackException {
        if(this.actualSize + bitSet.size() <= this.size) {
            stack.push(bitSet);
            actualSize += bitSet.size();
        }
        else{
            throw new FullStackException();
        }
    }

    public BitSet pop() throws EmptyStackException {
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        actualSize -= stack.peek().size();
        return stack.pop();
    }

    public boolean isEmpty(){
        return stack.empty();
    }
}
