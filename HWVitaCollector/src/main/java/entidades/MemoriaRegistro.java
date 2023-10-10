package entidades;

import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Load;

import java.util.List;

public class MemoriaRegistro {
    int fkMaquina;
    String dtRegistro;
    String qtdTotal;
    String usoMemoria;

    public MemoriaRegistro(int fkMaquina, String dtRegistro, String qtdTotal, String usoMemoria) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.qtdTotal = qtdTotal;
        this.usoMemoria = usoMemoria;
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

    public static String getMemoryUsagePercentage () {
        Components components = JSensors.get.components();
        List<Load> loads = components.cpus.get(0).sensors.loads;
        System.out.println("Loads " + loads.get(0));
        for (Load load : loads) {
            if (load.name.contains("Memory")) {
                return String.format("%.2f %%", load.value);
            }
        }
        return "Sensor de memória não encontrado";
    }

    public static String getTotalMemory(){
        Memoria memoria = new Memoria();
        String usoMemoria = Conversor.formatarBytes(memoria.getEmUso()).replaceAll("GiB","").replace(',','.');
        double usoMemoriaDouble = Double.parseDouble(usoMemoria);
        double usoMemoriaPorcentagem = Double.valueOf(getMemoryUsagePercentage().replace(',', '.').replace("%",""));
        return String.format("%.2f GB",usoMemoriaDouble * 100 / usoMemoriaPorcentagem);
    }
}
