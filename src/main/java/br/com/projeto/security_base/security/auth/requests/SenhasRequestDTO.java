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
public class SenhasRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "O campo nova senha é obrigatória.")
    @NotBlank(message = "O campo nova senha  não pode ser vazia.")
    private String  newPassword;

    @NotNull(message = "O confirmar seha  é obrigatória.")
    @NotBlank(message = "O confirmar senha  não pode ser vazia.")
    private String confirmPassword;

    public  String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public  String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
