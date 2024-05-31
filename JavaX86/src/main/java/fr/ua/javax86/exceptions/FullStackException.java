package fr.ua.javax86.exceptions;

public class FullStackException extends Exception{
    public FullStackException(){
        super("Stack is full");
    }

    public FullStackException(String message){
        super(message);
    }
}
