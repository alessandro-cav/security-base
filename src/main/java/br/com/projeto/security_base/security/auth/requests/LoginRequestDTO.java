package br.com.projeto.security_base.security.auth.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "O campo email  é obrigatório.")
    @NotBlank(message = "O campo email  não pode ser vazio.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }
}
