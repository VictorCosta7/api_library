package br.com.victorcosta.libraryapi.exeptions;

public class AutorNotFoundException extends RuntimeException {
      public  AutorNotFoundException(){
        super("Author not found");
    }
}
