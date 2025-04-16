package br.com.projeto.security_base.security.auth.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class MensagemResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String messagem;

    public static MensagemResponseDTO getMenssagem(String mensagem) {
        return new MensagemResponseDTO(mensagem);
    }
}
