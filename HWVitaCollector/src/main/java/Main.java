import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.model.components.Cpu;
import componentes.Disco;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        Processador processador = new Processador();
        Memoria memoria = new Memoria();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


        Hardware hardware = new Hardware();
        hardware.getDiskInformation();

        for (Disco disco: hardware.discos) {
           // System.out.println(disco.getModel());
           // System.out.println(disco.getTotalSpace());
           // System.out.println(disco.getFreeSpace());
        }

        Runnable task = () -> {

            verticalLinesSout();
            textoFormatado("MÁQUINA");
            System.out.println("UUID "+systemInfo.getHardware().getComputerSystem().getHardwareUUID());

            verticalLinesSout();

            // Temperatura do núcleo do Processador
            verticalLinesSout();
            textoFormatado("PROCESSADOR");
            Double temperatura = hardware.getCpuTemperature();
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
            Double usoMemoriaPorcentagem = hardware.getMemoryLoadPercentage();
            Double totalMemoria = usoMemoriaDouble * 100 / usoMemoriaPorcentagem;
            textoFormatado(String.format("Memória total: %.2fGB",totalMemoria));
            textoFormatado(String.format("Uso da memória: %.2fGB",usoMemoriaDouble));
            textoFormatado(String.format("Porcentagem de uso: %.2f%%",usoMemoriaPorcentagem));
            verticalLinesSout();
            //MEMORIA - FIM

            // DISCO

            List<String> paths = new ArrayList<>();
            HWDiskStore disco ;
            int qtdDiscos = systemInfo.getHardware().getDiskStores().size();
            for (int i = 0; i < qtdDiscos; i++) {
                disco = systemInfo.getHardware().getDiskStores().get(i);
                textoFormatado("DISCO");
                textoFormatado(String.format("Armazenamento %s",Conversor.formatarBytes(disco.getSize()).replaceAll("GiB","GB")));
                paths.add(disco.getPartitions().get(0).getMountPoint());
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

      // scheduler.scheduleAtFixedRate(task, 0, 8, TimeUnit.SECONDS);
    }





    // Métodos apenas para formatação e embelezamento :)
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


