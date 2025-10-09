package br.com.victorcosta.libraryapi.exeptions;

public class BookNotAvailableException extends RuntimeException {
    public  BookNotAvailableException(){
        super("No books in stock");
    }
}
