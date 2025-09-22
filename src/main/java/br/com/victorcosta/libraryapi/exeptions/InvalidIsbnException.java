package br.com.victorcosta.libraryapi.exeptions;

public class InvalidIsbnException extends RuntimeException {
    public  InvalidIsbnException(){
        super("Invalid ISBN");
    }
}
