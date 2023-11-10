package entidades;

import lombok.ToString;

@ToString
public class Maquina {
    @ToString.Exclude private String uuid;
    @ToString.Exclude private int fkHospital;
    private String local;
    private String responsavel;

    public Maquina(String uuid, int fkHospital, String local, String responsavel) {
        this.uuid = uuid;
        this.fkHospital = fkHospital;
        this.local = local;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
