package br.com.projeto.security_base.handlers;

public class ObjetoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjetoNotFoundException(String message) {
        super(message);
    }

}