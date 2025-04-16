package br.com.projeto.security_base.security.auth.controller;

import br.com.projeto.security_base.security.auth.requests.AuthenticationRequestDTO;
import br.com.projeto.security_base.security.auth.requests.RegisterRequestDTO;
import br.com.projeto.security_base.security.auth.responses.AuthenticationResponseDTO;
import br.com.projeto.security_base.security.auth.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller", description = "APIs relacionadas à autenticação")
public class AuthenticatioController {

    private final AuthenticationService service;

    public AuthenticatioController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticar Usuario", description = "Endpoint para autenticar um usuário")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
           @Valid @RequestBody AuthenticationRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.authenticate(requestDTO));
    }

    @PostMapping("/register-usuario")
    @Operation(summary = "Registrar usuario", description = "Endpoint para registrar um novo usuario")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthenticationResponseDTO> registerUsuario(
            @RequestBody RegisterRequestDTO requestDTO ) {
        return ResponseEntity.ok(service.registerUsuario(requestDTO));
    }
}
