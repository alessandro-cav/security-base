package br.com.projeto.security_base.handlers;

public class EmailSendFailureException extends RuntimeException {

    public EmailSendFailureException(String message) {
        super(message);
    }
}
