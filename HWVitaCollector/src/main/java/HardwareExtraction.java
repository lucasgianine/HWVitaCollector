import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import entidades.DiscoRegistro;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HardwareExtraction {
    SystemInfo systemInfo = new SystemInfo();
    Double cpuTemperature;
    Double memoryLoadPercentage;

    List<DiscoRegistro> discoRegistros = new ArrayList<>();


    //Método para pegar todas as informações de disco necessárias e armazená-las em uma lista de discos






    public Double getMemoryLoadPercentage(){
        Components components = JSensors.get.components();
        List<Load> loads = components.cpus.get(0).sensors.loads;

        for (Load load:loads) {
            if (load.name.contains("Memory")){
                return load.value;
            }
        }
        return 0.0;
    }




}
