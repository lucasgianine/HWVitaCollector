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
import oshi.hardware.HWPartition;

import java.awt.*;
import java.awt.image.DirectColorModel;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        Looca looca = new Looca();
        Processador processador = new Processador();
        Components components = JSensors.get.components();
        Memoria memoria = new Memoria();
        HWDiskStore desk = systemInfo.getHardware().getDiskStores().get(0);



        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        List<HWDiskStore> discos = new ArrayList<>();

        int qtdDiscos = systemInfo.getHardware().getDiskStores().size();
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < qtdDiscos; i++) {
            HWDiskStore disco = systemInfo.getHardware().getDiskStores().get(i);
            discos.add(disco);
            paths.add(disco.getPartitions().get(0).getMountPoint());
        }



        Runnable task = () -> {


            // Temperatura do núcleo do Processador
            verticalLinesSout();
            textoFormatado("PROCESSADOR");
            Hardwares.Cpu cpu = new Hardwares.Cpu();
            Double temperatura = cpu.getCpuTemperature();
            textoFormatado(String.format("Temperatura %.2f°C", temperatura));


            // Uso do processador
            Double usoProcessador = processador.getUso();
            textoFormatado(String.format("Uso do processador: %.2f%%", usoProcessador));
            verticalLinesSout();

            //PROCESSADOR - FIM


            // Uso da memória atual, convertida para Double prontinha para enviar ao Banco
            textoFormatado("MEMORIA");
            String usoMemoria = Conversor.formatarBytes(memoria.getEmUso()).replaceAll("GiB","").replace(',','.');
            Double usoMemoriaDouble = Double.valueOf(usoMemoria);
            textoFormatado(String.format("Uso da memória: %.2f GB",usoMemoriaDouble));
            verticalLinesSout();

            //MEMORIA - FIM

            // DISCO
            for (int i = 0; i < discos.size(); i++) {
                textoFormatado("DISCO");
                HWDiskStore disco = systemInfo.getHardware().getDiskStores().get(i);
                textoFormatado(String.format("Armazenamento %s",Conversor.formatarBytes(disco.getSize()).replaceAll("GiB","GB")));

                File file = new File(paths.get(i));
                double size = file.getFreeSpace() / (1024.0 * 1024 * 1024);
                textoFormatado(String.format("Espaço livre %.2f GB", size));

                textoFormatado(String.format(disco.getModel().replaceAll("(Unidades de disco padrão)", "")));
                verticalLinesSout();
            }
            // FIM - DISCO
            System.out.println();
            System.out.println();
        };

        scheduler.scheduleAtFixedRate(task, 0, 8, TimeUnit.SECONDS);



    }


    static void verticalLinesSout(){
        System.out.println("*-------------------------------*");
    }
    static void textoFormatado(String texto){
        System.out.printf("|  %s",texto);
        if (texto.length() > 26){
            for (int i = texto.length() + 3; i < 32; i++) {
                System.out.print(" ");
            }
        }
        for (int i = texto.length() + 3; i < 32; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}


