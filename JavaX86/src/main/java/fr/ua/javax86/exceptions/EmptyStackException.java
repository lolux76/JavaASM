package fr.ua.javax86.exceptions;

public class EmptyStackException extends Exception{
    public EmptyStackException(){
        super("Stack has no elements");
    }

    public EmptyStackException(String message){
        super(message);
    }
}
