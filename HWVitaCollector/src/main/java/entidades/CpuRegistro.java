package entidades;

import com.github.britooo.looca.api.group.processador.Processador;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import lombok.ToString;

import java.util.List;
@ToString
public class CpuRegistro extends Registro{
    private String temperatura;
    private String usoPorcentagem;

    public CpuRegistro(String fkMaquina, String dtRegistro, String temperatura, String usoPorcentagem) {
        this.fkMaquina = fkMaquina;
        this.dtRegistro = dtRegistro;
        this.temperatura = temperatura;
        this.usoPorcentagem = usoPorcentagem;
    }

    public static Double getCpuTemperatureValue(){
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if(cpus.isEmpty()){
            return 0.0;
        }
        List<Temperature> temperatures = cpus.get(0).sensors.temperatures;
        for (final Cpu cpu : cpus) {
            //System.out.println("Found CPU component: " + cpu.name);
            if (cpu.sensors.temperatures.isEmpty()) {
                System.out.println("Nenhum sensor de temperatura foi encontrado");
                return 0.0;
            }
        }
        Double packageTemperature = temperatures.get(temperatures.size()-1).value;
        return packageTemperature;
    }

    public static String getCpuUsePercentage(){
       return String.format("%.2f %%",new Processador().getUso());
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getUsoPorcentagem() {
        return usoPorcentagem;
    }

    public void setUsoPorcentagem(String usoPorcentagem) {
        this.usoPorcentagem = usoPorcentagem;
    }


}
