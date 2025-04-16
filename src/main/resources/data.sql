INSERT INTO tb_usuario (email, nome, role, senha, status_usuario)
SELECT 'root@gmail.com', 'root', 'USUARIO', '$2a$12$W4ga0wBDtSMXGY0y41/PAuY8o0E.SGucCpddVRY6gvZhPxA16bpGi', 'ATIVO'
WHERE NOT EXISTS (SELECT 1 FROM tb_usuario WHERE email = 'root@gmail.com');
