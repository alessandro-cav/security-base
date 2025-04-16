package br.com.projeto.security_base.security.auth.service;

import br.com.projeto.security_base.configs.validations.Validator;
import br.com.projeto.security_base.handlers.BadRequestException;
import br.com.projeto.security_base.security.auth.requests.AuthenticationRequestDTO;
import br.com.projeto.security_base.security.auth.requests.RegisterRequestDTO;
import br.com.projeto.security_base.security.auth.responses.AuthenticationResponseDTO;
import br.com.projeto.security_base.security.jwt.JwtService;
import br.com.projeto.security_base.usuario.enuns.Role;
import br.com.projeto.security_base.usuario.enuns.Status;
import br.com.projeto.security_base.usuario.models.Usuario;
import br.com.projeto.security_base.usuario.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UsuarioService usuarioService;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	private final Validator validator;

	@Transactional
	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
		this.validator.validarEmail(requestDTO.getEmail());
		var user = usuarioService.findByEmail(requestDTO.getEmail());

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getSenha()));
		var jwtToken = jwtService.generateToken((UserDetails) user);

		return AuthenticationResponseDTO.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponseDTO registerUsuario(RegisterRequestDTO requestDTO) {
		this.validator.validarEmail(requestDTO.getEmail());
		this.usuarioService.findByEmailOptional(requestDTO.getEmail()).ifPresent(email -> {
			throw new BadRequestException(requestDTO.getEmail() + " já cadastrado no sistema!");
		});
		var user = Usuario.builder()
				.nome(requestDTO.getNome())
				.email(requestDTO.getEmail())
				.senha(passwordEncoder.encode(requestDTO.getSenha()))
				.role(Role.buscarRole(requestDTO.getRole()))
				.statusUsuario(Status.ATIVO)
				.build();
		this.usuarioService.save(user);

		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponseDTO.builder()
				.token(jwtToken)
				.build();
	}
}
