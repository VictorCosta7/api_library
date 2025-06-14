package br.com.victorcosta.libraryapi.exeptions;

public class UserNotFoundException extends RuntimeException {
      public  UserNotFoundException() {
        super("User not found");
    }
}
