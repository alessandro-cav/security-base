package br.com.projeto.security_base.security.email;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EnviaEmail {

    private final JavaMailSender javaMailSender;

    public EnviaEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void emailAlterarSenha(String destino, String nome, String link) {
        try {
            String titulo = "Redefinição de Senha";

            String conteudo = "Olá " + nome + ", Houve uma solicitação para alterar sua senha de email! \n\n "
                    + "Se você não fez essa solicitação, ignore este e-mail."
                    + "Caso contrário, clique neste link para alterar sua senha: \n\n" + "Link: " + link;

            extracted(destino, titulo, conteudo);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }


    private void extracted(String destino, String titulo, String conteudo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destino);
        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);

        javaMailSender.send(mensagem);
    }

    public void enviarEmail(String destino, String nome, String link) {
        try {
            String titulo = "Solicitação de Redefinição de Senha";

            String conteudo = "Olá " + nome + ",\n\n"
                    + "Recebemos uma solicitação para redefinir a senha da sua conta. Se você não fez essa solicitação, por favor, desconsidere este e-mail.\n\n"
                    + "Caso contrário, clique no link abaixo para alterar sua senha:\n\n"
                    + link + "\n\n"
                    + "Atenciosamente,\n"
                    + "Equipe de Suporte";

            enviarMensagemDeEmail(destino, titulo, conteudo);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

    private void enviarMensagemDeEmail(String destino, String titulo, String conteudo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destino);
        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
    }
}
