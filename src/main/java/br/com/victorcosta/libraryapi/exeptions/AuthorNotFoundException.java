package br.com.victorcosta.libraryapi.exeptions;

public class AuthorNotFoundException extends RuntimeException {
      public  AuthorNotFoundException(){
        super("Author not found");
    }
}
