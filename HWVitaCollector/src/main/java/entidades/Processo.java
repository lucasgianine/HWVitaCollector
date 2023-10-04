package entidades;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processo {

    private Integer FkMaquina;
    private String nome;
    private String dataCaptura;
    private Integer threads;

    private long residentMemory;

    private long virtualMemory;

    public Processo(String nome, Integer threads, long residentMemory, String dataCaptura) {
        this.nome = nome;
        this.threads = threads;
        this.residentMemory = residentMemory;
        this.dataCaptura = dataCaptura;
    }

    public Integer getFkMaquina() {
        return FkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        FkMaquina = fkMaquina;
    }

    public String getDataCaptura() {
        return dataCaptura;
    }

    public void setDataCaptura(String dataCaptura) {
        this.dataCaptura = dataCaptura;
    }

    public long getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(long virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public long getResidentMemory() {
        return residentMemory;
    }

    public void setResidentMemory(long residentMemory) {
        this.residentMemory = residentMemory;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public void incrementThreads(Integer threads) {
        this.threads += threads;
    }

    public void incrementResidentSize(long residentMemory) {
        this.residentMemory += residentMemory;
    }

    public void incrementVirtualMemory(long virtualMemory) {
        this.virtualMemory += virtualMemory;
    }

    public static List<Processo> getProcessos(){
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
                Date dataAtual = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Formata a data atual no formato desejado
                String dataFormatada = dateFormat.format(dataAtual);
                listaProcessos.add(new Processo(processo.getName(), processo.getThreadCount(), processo.getResidentSetSize(),dataFormatada));
            }

        }

        return bubbleSort(listaProcessos);
    }


    static List<Processo> bubbleSort(List<Processo> listaProcessos) {
        int n = listaProcessos.size();
        long tempRM = 0;
        String tempNome = "";
        long tempVM = 0;
        int tempThreads = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (listaProcessos.get(j - 1).getResidentMemory() < listaProcessos.get(j).getResidentMemory()) {
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
        //top 5 Processos Quando esse bloco não está comentado ele ta pegando apenas uma parte dos processos
        List<Processo> listaTopProcessos = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            listaTopProcessos.add(listaProcessos.get(k));
        }
        return listaTopProcessos;
    }

}

