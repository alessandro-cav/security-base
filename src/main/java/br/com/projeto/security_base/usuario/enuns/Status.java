package br.com.projeto.security_base.usuario.enuns;

public enum Status {

    ATIVO("ATIVO"), INATIVO("INATIVO");

    private String descricao;

    public static Status buscarStatus(String sts) {
        Status status = null;
        for (Status status1 : Status.values()) {
            if (status1.getDescricao().equals(sts.toUpperCase())) {
                status = status1;
                break;
            }
        }
        return status;
    }

    private Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
