package br.com.projeto.security_base.security.email;

import br.com.projeto.security_base.configs.validations.Validator;
import br.com.projeto.security_base.handlers.BadRequestException;
import br.com.projeto.security_base.handlers.EmailSendFailureException;
import br.com.projeto.security_base.security.auth.requests.LoginRequestDTO;
import br.com.projeto.security_base.security.auth.requests.SenhasRequestDTO;
import br.com.projeto.security_base.security.auth.responses.MensagemResponseDTO;
import br.com.projeto.security_base.security.jwt.JwtService;
import br.com.projeto.security_base.usuario.models.Usuario;
import br.com.projeto.security_base.usuario.repositories.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final UsuarioRepository repository;

    private final EnviaEmail email;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncod;

    private final Validator validar;



    public void esqueciMinhaSenha(LoginRequestDTO loginRequestDTO) {
       this.validar.validarEmail(loginRequestDTO.getEmail());
        Usuario user = this.repository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("Usuário com o email " + loginRequestDTO.getEmail() + " não foi encontrado"));

        Map<String, Object> extraClaims = new HashMap<>();
        Date expirationTime = new Date(System.currentTimeMillis() + 1800000); // 30 minutos em milissegundos
        String token = Jwts.builder().setClaims(extraClaims).setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(expirationTime)
                .signWith(this.jwtService.getSignInKey(), SignatureAlgorithm.HS256).compact();

        String link = "http://localhost:4200/reset-password?token=" + token;

        try {
            email.enviarEmail(user.getEmail(), user.getNome(), link);
        } catch (Exception e) {
            throw new EmailSendFailureException("Erro ao enviar email de redefinição de senha");
        }
    }

    public MensagemResponseDTO resetarSenha(String token, SenhasRequestDTO senhasRequestDTO) {

        try {
            boolean expired = this.jwtService.isTokenExpired(token);
        } catch (Exception e) {
            throw new RuntimeException("Token expirado: " + e.getMessage());
        }

            if (!senhasRequestDTO.getNewPassword().trim().equals(senhasRequestDTO.getConfirmPassword().trim())) {
                throw new BadRequestException("Senhas diferentes");
            }
            final String username = this.jwtService.extractUsername(token);
            Optional<Usuario> usuario = this.repository.findByEmail(username);

            String novaSenhaCodificada = passwordEncod.encode(senhasRequestDTO.getNewPassword().trim());
            usuario.get().setSenha(novaSenhaCodificada);
            this.repository.saveAndFlush(usuario.get());

            String mensagem = "Senha alterada com sucesso.";
            return MensagemResponseDTO.getMenssagem(mensagem);
    }
}
