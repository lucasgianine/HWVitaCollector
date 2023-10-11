package entidades;

public class Maquina {
    private String uuid;
    private int fkHospital;
    private String apelido;
    private String responsavel;

    public Maquina(String uuid, int fkHospital, String apelido, String responsavel) {
        this.uuid = uuid;
        this.fkHospital = fkHospital;
        this.apelido = apelido;
        this.responsavel = responsavel;
    }
    public Maquina(){}

    public String getUuid() {
        return uuid;
    }

    public void setId(String uuid) {
        this.uuid = uuid;
    }

    public int getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(int fkHospital) {
        this.fkHospital = fkHospital;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
