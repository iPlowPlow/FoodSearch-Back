package fr.iplowplow.foodsearch.exceptions;

public class AuthenticateException extends RuntimeException {

    public AuthenticateException(String message){
        super(message);
    }
}
