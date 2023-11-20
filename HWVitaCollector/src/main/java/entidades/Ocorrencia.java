package entidades;

public class Ocorrencia {
    private String fkMaquina;
    private String dtOcorrencia;
    private String categoria;
    private String componente;
    private String metrica;
    private String descricao;

    public Ocorrencia(String fkMaquina, String dtOcorrencia, String categoria, String componente, String metrica, String descricao) {        this.fkMaquina = fkMaquina;
        this.dtOcorrencia = dtOcorrencia;
        this.categoria = categoria;
        this.componente = componente;
        this.metrica = metrica;
        this.descricao = descricao;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public String getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(String dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
