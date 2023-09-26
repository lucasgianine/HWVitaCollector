import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import org.checkerframework.checker.units.qual.C;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.awt.*;
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

        Runnable task = () -> {

        };

        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);



    }
}


