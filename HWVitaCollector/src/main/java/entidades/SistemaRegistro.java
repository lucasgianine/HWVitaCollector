package entidades;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;
import oshi.SystemInfo;

public class SistemaRegistro {
    private int fkMaquina;
    private String dtRegistro;
    private String tempoDeAtividadeSO;
    private int qtdDisposivosUsbConectados;

    public SistemaRegistro(int fkMaquina, String dtRegistro, String tempoDeAtividadeSO, int qtdDisposivosUsbConectados) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.tempoDeAtividadeSO = tempoDeAtividadeSO;
        this.qtdDisposivosUsbConectados = qtdDisposivosUsbConectados;
    }

    public int getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(int fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getTempoDeAtividadeSO() {
        return tempoDeAtividadeSO;
    }

    public void setTempoDeAtividadeSO(String tempoDeAtividadeSO) {
        this.tempoDeAtividadeSO = tempoDeAtividadeSO;
    }

    public int getQtdDisposivosUsbConectados() {
        return qtdDisposivosUsbConectados;
    }

    public void setQtdDisposivosUsbConectados(int qtdDisposivosUsbConectados) {
        this.qtdDisposivosUsbConectados = qtdDisposivosUsbConectados;
    }

    public static String getSystemUptime(){
        return Conversor.formatarSegundosDecorridos(new SystemInfo().getOperatingSystem().getSystemUptime());
    }

    public static int getUsbGroupSize(){
        return new Looca().getDispositivosUsbGrupo().getTotalDispositvosUsbConectados();
        //método paia
    }
}
