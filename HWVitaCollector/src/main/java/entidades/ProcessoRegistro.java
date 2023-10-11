package entidades;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessoRegistro {

    private String FkMaquina;
    private String nome;
    private String dtRegistro;
    private Integer threads;

    private long residentMemory;

    private long virtualMemory;

    public ProcessoRegistro(String nome, Integer threads, long residentMemory, String dtRegistro) {
        this.nome = nome;
        this.threads = threads;
        this.residentMemory = residentMemory;
        this.dtRegistro = dtRegistro;
    }

    public String getFkMaquina() {
        return FkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        FkMaquina = fkMaquina;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
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

    public static List<ProcessoRegistro> getProcessos(){
        SystemInfo systemInfo = new SystemInfo();
        List<OSProcess> processos = systemInfo.getOperatingSystem().getProcesses();
        List<ProcessoRegistro> listaProcessoRegistros = new ArrayList<>();

        for (OSProcess processo : processos) {
            boolean processoJaFoiColhido = false;

            for (ProcessoRegistro p : listaProcessoRegistros) {
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
                listaProcessoRegistros.add(new ProcessoRegistro(processo.getName(), processo.getThreadCount(), processo.getResidentSetSize(),dataFormatada));
            }

        }

        return bubbleSort(listaProcessoRegistros);
    }


    static List<ProcessoRegistro> bubbleSort(List<ProcessoRegistro> listaProcessoRegistros) {
        int n = listaProcessoRegistros.size();
        long tempRM = 0;
        String tempNome = "";
        long tempVM = 0;
        int tempThreads = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (listaProcessoRegistros.get(j - 1).getResidentMemory() < listaProcessoRegistros.get(j).getResidentMemory()) {
                    //swap elements
                    tempRM = listaProcessoRegistros.get(j - 1).getResidentMemory();
                    listaProcessoRegistros.get(j - 1).setResidentMemory(listaProcessoRegistros.get(j).getResidentMemory());
                    listaProcessoRegistros.get(j).setResidentMemory(tempRM);

                    tempNome = listaProcessoRegistros.get(j - 1).getNome();
                    listaProcessoRegistros.get(j - 1).setNome(listaProcessoRegistros.get(j).getNome());
                    listaProcessoRegistros.get(j).setNome(tempNome);

                    tempVM = listaProcessoRegistros.get(j - 1).getVirtualMemory();
                    listaProcessoRegistros.get(j - 1).setVirtualMemory(listaProcessoRegistros.get(j).getVirtualMemory());
                    listaProcessoRegistros.get(j).setVirtualMemory(tempVM);

                    tempThreads = listaProcessoRegistros.get(j - 1).getThreads();
                    listaProcessoRegistros.get(j - 1).setThreads(listaProcessoRegistros.get(j).getThreads());
                    listaProcessoRegistros.get(j).setThreads(tempThreads);


                }
            }
        }
        //top 5 Processos Quando esse bloco não está comentado ele ta pegando apenas uma parte dos processos
        List<ProcessoRegistro> listaTopProcessoRegistros = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            listaTopProcessoRegistros.add(listaProcessoRegistros.get(k));
        }
        return listaTopProcessoRegistros;
    }

}

