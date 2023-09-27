package Hardwares;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import oshi.hardware.CentralProcessor;
import oshi.hardware.common.AbstractCentralProcessor;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;
import com.sun.jna.platform.win32.WinNT.HANDLE;


import java.util.List;

public class Cpu {

    public Double getCpuTemperature(){
        Components components = JSensors.get.components();
        List<com.profesorfalken.jsensors.model.components.Cpu> cpus = components.cpus;
        List<Temperature> temperatures = cpus.get(0).sensors.temperatures;
        List<Load> loads = cpus.get(0).sensors.loads;

      /*  if (cpus != null) {
            for (final com.profesorfalken.jsensors.model.components.Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }

                    //Print fan speed
                    List<Fan> fans = cpu.sensors.fans;
                    for (final Fan fan : fans) {
                        System.out.println(fan.name + ": " + fan.value + " RPM");
                    }
                }else {
                    System.out.println("Nenhum sensor foi encontrado");
                }
            }
        }*/
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




    public void getCpuCurrentFrequency(){
        Kernel32 kernel32 = Kernel32.INSTANCE;
        SYSTEM_INFO sysInfo = new SYSTEM_INFO();
        kernel32.GetSystemInfo(sysInfo);


        int processorSpeed = sysInfo.dwProcessorType.intValue();
        System.out.println("Processor Speed: " + kernel32.GetCurrentProcess() + " MHz");
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
