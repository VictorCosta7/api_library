package br.com.victorcosta.libraryapi.exeptions;

public class AuthorFoundException extends RuntimeException{
    public  AuthorFoundException(){
        super("Author already exists");
    }
}