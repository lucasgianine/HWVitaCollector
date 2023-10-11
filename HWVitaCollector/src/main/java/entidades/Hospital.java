package entidades;

public class Hospital {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private String codHospital;

    public Hospital(int id, String nome, String telefone, String email, String cnpj, String codHospital) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.codHospital = codHospital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodHospital() {
        return codHospital;
    }

    public void setCodHospital(String codHospital) {
        this.codHospital = codHospital;
    }
}
