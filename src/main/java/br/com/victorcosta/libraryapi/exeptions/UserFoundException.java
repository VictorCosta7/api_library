package br.com.victorcosta.libraryapi.exeptions;

public class UserFoundException extends RuntimeException {
      public  UserFoundException(){
        super("User already exists");
    }
}
