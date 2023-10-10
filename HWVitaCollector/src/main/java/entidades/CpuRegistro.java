package entidades;

import com.github.britooo.looca.api.group.processador.Processador;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;

import java.util.List;

public class CpuRegistro{
    private int fkMaquina;
    private String dtRegistro;
    private String temperatura;
    private String usoPorcentaegem;

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

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getUsoPorcentaegem() {
        return usoPorcentaegem;
    }

    public void setUsoPorcentaegem(String usoPorcentaegem) {
        this.usoPorcentaegem = usoPorcentaegem;
    }

    public CpuRegistro(int fkMaquina, String dtRegistro, String temperatura, String usoPorcentaegem) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.temperatura = temperatura;
        this.usoPorcentaegem = usoPorcentaegem;
    }

    public static Double getCpuTemperatureValue(){
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        List<Temperature> temperatures = cpus.get(0).sensors.temperatures;

        if (cpus != null) {
            for (final com.profesorfalken.jsensors.model.components.Cpu cpu : cpus) {
                //System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors.temperatures.isEmpty()) {
                    System.out.println("Nenhum sensor de temperatura foi encontrado");
                    return 0.0;
                }
            }
        }
        Double packageTemperature = temperatures.get(temperatures.size()-1).value;

        return packageTemperature;
    }

    public static String getCpuUsePercentage(){
        return String.format("%.2f %%",new Processador().getUso());
    }
}
