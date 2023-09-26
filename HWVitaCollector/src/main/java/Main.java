import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Disk;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import org.checkerframework.checker.units.qual.C;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Processador processador = new Processador();
        Components components = JSensors.get.components();
        Memoria memoria = new Memoria();


/*
        // Temperatura do núcleo do Processador
        Hardwares.Cpu cpu = new Hardwares.Cpu();
        Double temperatura = cpu.getCpuTemperature();
        System.out.printf("Temperatura do processador %.2f°C\n", temperatura);

        // Uso do processador
        Double usoProcessador = processador.getUso();
        System.out.printf("Uso do processador: %.2f%%\n", usoProcessador);

        // Uso da memória atual, convertida para Double prontinha para enviar ao Banco
        String usoMemoria = Conversor.formatarBytes(memoria.getEmUso()).replaceAll("GiB","").replace(',','.');
        Double usoMemoriaDouble = Double.valueOf(usoMemoria);
        System.out.println(usoMemoriaDouble);
*/

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        SystemInfo systemInfo = new SystemInfo();
        List<HWDiskStore> discos = new ArrayList<>();

        int qtdDiscos = systemInfo.getHardware().getDiskStores().size();
        for (int i = 0; i < qtdDiscos; i++) {
            HWDiskStore disco = systemInfo.getHardware().getDiskStores().get(i);
            System.out.println(disco.getName());
            discos.add(disco);
        }


        Runnable task = () -> {
            for (int i = 0; i < discos.size(); i++) {
                HWDiskStore disco = systemInfo.getHardware().getDiskStores().get(i);
                System.out.printf("Nome do Disco %s \n",disco.getModel());
                System.out.printf("Armazenamento total %s\n",Conversor.formatarBytes(disco.getSize()).replaceAll("GiB","GB"));
                if(i == 0){
                    File file = new File("C:\\");
                    double size = file.getFreeSpace() / (1024.0 * 1024 * 1024);
                    System.out.println();
                    System.out.printf( "Armazenamento disponível %.2f GB\n", size);
                } else if (i==1) {
                    File file = new File("D:\\");
                    double size = file.getFreeSpace() / (1024.0 * 1024 * 1024);
                    System.out.printf( "%.2f GB\n", size);
                }
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);



    }
}


