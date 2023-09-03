package com.Alberto.demo.exceptions;

public class CantDeleteException extends RuntimeException{

    public CantDeleteException (String msg){
        super(msg);
    }
}
