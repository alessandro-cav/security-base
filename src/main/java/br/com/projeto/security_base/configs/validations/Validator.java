package br.com.projeto.security_base.configs.validations;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.projeto.security_base.handlers.BadRequestException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Validator {

    private final List<BadRequestException> erros = new ArrayList<>();

    public void validarCnpjEEmail(String cnpj, String email) {
        validaCNPJ(cnpj);
        validaEmail(email);
        verificarErros();
    }

    public void validarCpfEEmail(String cpf, String email) {
        validaCPF(cpf);
        validaEmail(email);
        verificarErros();
    }

    public void validarEmail(String email) {
        validaEmail(email);
        verificarErros();
    }

    private void validaCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            erros.add(new BadRequestException("CPF inválido"));
        }
    }

    private void validaCNPJ(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        try {
            cnpjValidator.assertValid(cnpj);
        } catch (InvalidStateException e) {
            erros.add(new BadRequestException("CNPJ inválido"));
        }
    }

    private void validaEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(email)) {
            erros.add(new BadRequestException("Email inválido"));
        }
    }

    private void verificarErros() {
        if (!erros.isEmpty()) {
            StringBuilder mensagemError = new StringBuilder();
            for (BadRequestException error : erros) {
                mensagemError.append(error.getMessage()).append("; ");
            }
            throw new BadRequestException(mensagemError.toString());
        }
    }
}

