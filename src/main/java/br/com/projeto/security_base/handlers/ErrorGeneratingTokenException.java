package br.com.projeto.security_base.handlers;

public class ErrorGeneratingTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public ErrorGeneratingTokenException(String message) {
        super(message);
    }
}
