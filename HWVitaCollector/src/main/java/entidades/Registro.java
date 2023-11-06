package entidades;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Registro {
    protected String fkMaquina;
    protected String dtRegistro;

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }
}
