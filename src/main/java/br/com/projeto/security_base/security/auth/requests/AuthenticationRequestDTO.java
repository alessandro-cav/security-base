package br.com.projeto.security_base.security.auth.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    @NotNull(message = "O campo email  é obrigatório.")
    @NotBlank(message = "O campo email  não pode ser vazio.")
    private String email;

    @NotNull(message = "O campo senha é obrigatória.")
    @NotBlank(message = "O campo senha não pode ser vazia.")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha) {
        this.senha = senha;
    }
}
