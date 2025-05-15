package br.com.victorcosta.libraryapi.exeptions;

public class AuthorEmailException extends RuntimeException{
    public  AuthorEmailException(){
        super("Author email already registred");
    }
}