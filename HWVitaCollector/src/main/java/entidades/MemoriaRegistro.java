package entidades;

import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Load;
import lombok.ToString;

import java.util.List;
@ToString
public class MemoriaRegistro {
    @ToString.Exclude String fkMaquina;
    String dtRegistro;
    String qtdTotal;
    String usoMemoria;

    public MemoriaRegistro(String fkMaquina, String dtRegistro, String qtdTotal, String usoMemoria) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.qtdTotal = qtdTotal;
        this.usoMemoria = usoMemoria;
    }

    public static String getMemoryUsagePercentage () {
        Memoria memoria = new Memoria();
        Components components = JSensors.get.components();
        if(components.cpus.isEmpty()){
            String usoMemoriaPorcentagem = String.format("%s%%",Conversor.formatarBytes(memoria.getEmUso() / memoria.getTotal() * 100).replaceAll("GiB","").replace(',','.'));
            return usoMemoriaPorcentagem;
        }
        List<Load> loads = components.cpus.get(0).sensors.loads;
        for (Load load : loads) {
            if (load.name.contains("Memory")) {
                return String.format("%.2f %%", load.value).replace(',','.');
            }
        }
        return "Sensor de memória não encontrado";
    }

    public static String getTotalMemory(){
        Memoria memoria = new Memoria();
        return Conversor.formatarBytes(memoria.getTotal()).replaceAll("GiB","GB").replace(',','.');
    }


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

    public String getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(String qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public String getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(String usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

}
