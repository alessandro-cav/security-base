package br.com.projeto.security_base.security.auth.controller;

import br.com.projeto.security_base.security.auth.requests.LoginRequestDTO;
import br.com.projeto.security_base.security.auth.requests.SenhasRequestDTO;
import br.com.projeto.security_base.security.auth.responses.MensagemResponseDTO;
import br.com.projeto.security_base.security.email.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recuperar")
@Tag(name = "recuperar Controller", description = "APIs relacionadas à recuperar")
public class RecuperarSenhaController {

    private final EmailService service;

    public RecuperarSenhaController(EmailService service) {
        this.service = service;
    }

    @Operation(summary = "Recuperar", description = "Endpoint para recuperar senha")
    @PostMapping("/forgot_password")
    public ResponseEntity<Void> esqueciMinhaSenha(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        this.service.esqueciMinhaSenha(loginRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Nova senha", description = "Endpoint para inclusão da nova senha")
    @PostMapping("/reset_password")
    public ResponseEntity<MensagemResponseDTO> resetarSenha(@RequestParam String token,
                                                            @RequestBody @Valid SenhasRequestDTO senhasRequestDTO) {
        return ResponseEntity.ok(this.service.resetarSenha(token, senhasRequestDTO));
    }
}
