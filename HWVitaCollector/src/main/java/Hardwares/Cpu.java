package Hardwares;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
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

        /*
        Pegando todas as temperaturas
        for (Temperature temp:
             temperatures) {
            System.out.println(temp.name+ " " + temp.value);
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


}
