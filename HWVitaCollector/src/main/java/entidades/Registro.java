package entidades;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Registro {
    protected String fkMaquina;
    protected String dtRegistro;

}
