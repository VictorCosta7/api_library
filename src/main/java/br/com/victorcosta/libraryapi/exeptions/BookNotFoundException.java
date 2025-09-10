package br.com.victorcosta.libraryapi.exeptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("Book not found");
    }
}