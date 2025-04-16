package br.com.projeto.security_base.usuario.enuns;

public enum Role {

     ADMINISTRADOR("ADMINISTRADOR"), USUARIO("USUARIO"), ROOT("ROOT");

     private String descricao;

     public static Role buscarRole(String rl) {
          Role role = null;
          for (Role role1 : Role.values()) {
               if (role1.getDescricao().equals(rl.toUpperCase())) {
                    role = role1;
                    break;
               }
          }
          return role;
     }

     private Role(String descricao) {
          this.descricao = descricao;
     }

     public String getDescricao() {
          return descricao;
     }
}
