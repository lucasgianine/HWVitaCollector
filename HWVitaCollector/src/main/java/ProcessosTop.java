import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import entidades.Processo;
import oshi.SystemInfo;
import oshi.hardware.platform.windows.WindowsPowerSource;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProcessosTop {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        List<OSProcess> processos = systemInfo.getOperatingSystem().getProcesses();
        List<Processo> listaProcessos = new ArrayList<>();

        for (OSProcess processo : processos) {
            boolean processoJaFoiColhido = false;

            for (Processo p : listaProcessos) {
                if (processo.getName().contains(p.getNome())) {
                    p.incrementThreads(processo.getThreadCount());
                    p.incrementResidentSize(processo.getResidentSetSize());
                    p.incrementVirtualMemory(processo.getVirtualSize());

                    processoJaFoiColhido = true;
                    break;
                }
            }

            if (!processoJaFoiColhido) {
                listaProcessos.add(new Processo(processo.getName(), processo.getThreadCount(), processo.getResidentSetSize(), processo.getVirtualSize()));
            }

        }

        List<Processo> processosOrdenadosPorMemoria = bubbleSort(listaProcessos);
        Integer threadsTotais = 0;
        long usoMemoriaTotal = 0;
        for (Processo processo : processosOrdenadosPorMemoria) {
            threadsTotais += processo.getThreads() ;
            usoMemoriaTotal += processo.getResidentMemory();
            System.out.println("Nome do processo: " + processo.getNome());
            System.out.println("Total de Threads: " + processo.getThreads());
            System.out.println("Total de ResidentMemory : " + Conversor.formatarBytes(processo.getResidentMemory()));
            System.out.println("----------------------------");
        }

        System.out.println("Total Threads " + threadsTotais);
        System.out.println("Uso memoria total processos " + Conversor.formatarBytes(usoMemoriaTotal));
        System.out.println(String.format("Uso Cpu %.2f%% ", new Processador().getUso()));
        System.out.println("Uso memória Looca " +Conversor.formatarBytes(new Memoria().getEmUso()));




    }


    static List<Processo> bubbleSort(List<Processo> listaProcessos) {
        int n = listaProcessos.size();
        long tempRM = 0;
        String tempNome = "";
        long tempVM = 0;
        int tempThreads = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (listaProcessos.get(j - 1).getResidentMemory() > listaProcessos.get(j).getResidentMemory()) {
                    //swap elements
                    tempRM = listaProcessos.get(j - 1).getResidentMemory();
                    listaProcessos.get(j - 1).setResidentMemory(listaProcessos.get(j).getResidentMemory());
                    listaProcessos.get(j).setResidentMemory(tempRM);

                    tempNome = listaProcessos.get(j - 1).getNome();
                    listaProcessos.get(j - 1).setNome(listaProcessos.get(j).getNome());
                    listaProcessos.get(j).setNome(tempNome);

                    tempVM = listaProcessos.get(j - 1).getVirtualMemory();
                    listaProcessos.get(j - 1).setVirtualMemory(listaProcessos.get(j).getVirtualMemory());
                    listaProcessos.get(j).setVirtualMemory(tempVM);

                    tempThreads = listaProcessos.get(j - 1).getThreads();
                    listaProcessos.get(j - 1).setThreads(listaProcessos.get(j).getThreads());
                    listaProcessos.get(j).setThreads(tempThreads);
                }
            }
            }
        return listaProcessos;
        }
    }
