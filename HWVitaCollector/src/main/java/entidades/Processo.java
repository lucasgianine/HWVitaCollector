package entidades;

public class Processo {
    String nome;
    Integer threads;

    long residentMemory;

    long virtualMemory;

    public Processo(String nome, Integer threads, long residentMemory, long virtualMemory) {
        this.nome = nome;
        this.threads = threads;
        this.residentMemory = residentMemory;
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


}

