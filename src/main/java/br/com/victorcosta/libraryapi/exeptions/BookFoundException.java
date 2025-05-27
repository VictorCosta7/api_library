package br.com.victorcosta.libraryapi.exeptions;

public class BookFoundException extends RuntimeException{
    public  BookFoundException(){
        super("Book already Registred");
    }
}