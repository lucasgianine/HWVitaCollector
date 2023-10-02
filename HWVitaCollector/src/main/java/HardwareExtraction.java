import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import componentes.Disco;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HardwareExtraction {
    SystemInfo systemInfo = new SystemInfo();
    Double cpuTemperature;
    Double memoryLoadPercentage;

    List<Disco> discos = new ArrayList<>();


    //Método para pegar todas as informações de disco necessárias e armazená-las em uma lista de discos
    public void  getDiskInformation(){
        List<String> paths = new ArrayList<>();
        int qtdDiscos =  systemInfo.getHardware().getDiskStores().size();
        HWDiskStore HWDisk;
        for (int i = 0; i < qtdDiscos; i++) {
            Disco disco = new Disco();
            HWDisk = systemInfo.getHardware().getDiskStores().get(i);
            paths.add(HWDisk.getPartitions().get(0).getMountPoint());
            File file = new File(paths.get(i));
            String diskModel = HWDisk.getModel().replaceAll("[(Unidades de disco padrão)]", "");
            Double totalSpace = Double.parseDouble(Conversor.formatarBytes(HWDisk.getSize()).replaceAll("TiB","").replaceAll("MiB","").replaceAll("GiB","").replace(',','.'));
            Double freeSpace = file.getFreeSpace() / (1024.0 * 1024 * 1024);


            disco.setFreeSpace(freeSpace);
            disco.setModel(diskModel);
            disco.setTotalSpace(totalSpace);

            discos.add(disco);
        }


    }



    public Double getCpuTemperature(){
        Components components = JSensors.get.components();
        List<com.profesorfalken.jsensors.model.components.Cpu> cpus = components.cpus;
        List<Temperature> temperatures = cpus.get(0).sensors.temperatures;
        List<Load> loads = cpus.get(0).sensors.loads;

          if (cpus != null) {
            for (final com.profesorfalken.jsensors.model.components.Cpu cpu : cpus) {
                //System.out.println("Found CPU component: " + cpu.name);
                if (!cpu.sensors.temperatures.isEmpty()) {
                    //System.out.println("Sensors: ");

                    //Print temperatures
                   /* List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }

                    //Print fan speed
                    List<Fan> fans = cpu.sensors.fans;
                    for (final Fan fan : fans) {
                        System.out.println(fan.name + ": " + fan.value + " RPM");
                    }*/
                }else {
                    System.out.println("Nenhum sensor de temperatura foi encontrado");
                    return 0.0;
                }
            }
        }
        /*
        for (Temperature temp:
             temperatures) {
            System.out.println(temp.name+ " " + temp.value);
        }

        for (Load load: loads) {
            System.out.println(load.name+" "+load.value+"%");
        }

*/

        String lastTemperatureName = temperatures.get(temperatures.size()-1).name;
        Double packageTemperature = temperatures.get(temperatures.size()-1).value;
        // System.out.printf("%s %.2f\n",lastTemperatureName,packageTemperature);

        return packageTemperature;
    }



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
