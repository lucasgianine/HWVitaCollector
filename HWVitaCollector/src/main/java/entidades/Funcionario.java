package entidades;

import lombok.ToString;

@ToString
public class Funcionario {
    @ToString.Exclude private Integer id;
    @ToString.Exclude private Integer fkHospital;
    private String email;
    private String senha;
    private String nome;
    private String telefone;
    private String funcao;




    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(int fkHospital) {
        this.fkHospital = fkHospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
