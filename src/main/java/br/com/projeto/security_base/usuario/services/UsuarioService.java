package br.com.projeto.security_base.usuario.services;

import br.com.projeto.security_base.handlers.BadRequestException;
import br.com.projeto.security_base.usuario.models.Usuario;
import br.com.projeto.security_base.usuario.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(() -> new BadRequestException("Usuário não encontrado!"));
    }

    public Optional<Usuario> findByEmailOptional(String email) {
        return this.repository.findByEmail(email);
    }

    @Transactional
    public void save(Usuario usuario) {
        this.repository.save(usuario);
    }
}
