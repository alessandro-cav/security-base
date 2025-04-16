package br.com.projeto.security_base.usuario.responses;

import br.com.projeto.security_base.usuario.enuns.Role;
import br.com.projeto.security_base.usuario.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String email;

    private Role role;

    private Status statusUsuario;
}
