package entidades;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;
import lombok.ToString;
import oshi.SystemInfo;
@ToString
public class SistemaRegistro extends Registro{
    private String tempoDeAtividadeSO;
    private int qtdDisposivosUsbConectados;

    public SistemaRegistro(String fkMaquina, String dtRegistro, String tempoDeAtividadeSO, int qtdDisposivosUsbConectados) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.tempoDeAtividadeSO = tempoDeAtividadeSO;
        this.qtdDisposivosUsbConectados = qtdDisposivosUsbConectados;
    }
    public static String getSystemUptime(){
        return Conversor.formatarSegundosDecorridos(new SystemInfo().getOperatingSystem().getSystemUptime());
    }

    public static int getUsbGroupSize(){
        return new Looca().getDispositivosUsbGrupo().getTotalDispositvosUsbConectados();
        //método paia
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

}
