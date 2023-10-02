package entidades;

public class Hardware {
    private int FkMaquina;
    private String UsoProcessador;
    private String TempProcessador;
    private String FreqProcessador;
    private String UsoMemoria;
    private String TempMemoria;
    private String FreqMemoria;
    private String ArmazenamentoTotal;
    private String ArmazenamentoLivre;

    public int getFkMaquina() {
        return FkMaquina;
    }

    public void setFkMaquina(int fkMaquina) {
        FkMaquina = fkMaquina;
    }

    public String getUsoProcessador() {
        return UsoProcessador;
    }

    public void setUsoProcessador(String usoProcessador) {
        UsoProcessador = usoProcessador;
    }

    public String getTempProcessador() {
        return TempProcessador;
    }

    public void setTempProcessador(String tempProcessador) {
        TempProcessador = tempProcessador;
    }

    public String getFreqProcessador() {
        return FreqProcessador;
    }

    public void setFreqProcessador(String freqProcessador) {
        FreqProcessador = freqProcessador;
    }

    public String getUsoMemoria() {
        return UsoMemoria;
    }

    public void setUsoMemoria(String usoMemoria) {
        UsoMemoria = usoMemoria;
    }

    public String getTempMemoria() {
        return TempMemoria;
    }

    public void setTempMemoria(String tempMemoria) {
        TempMemoria = tempMemoria;
    }

    public String getFreqMemoria() {
        return FreqMemoria;
    }

    public void setFreqMemoria(String freqMemoria) {
        FreqMemoria = freqMemoria;
    }

    public String getArmazenamentoTotal() {
        return ArmazenamentoTotal;
    }

    public void setArmazenamentoTotal(String armazenamentoTotal) {
        ArmazenamentoTotal = armazenamentoTotal;
    }

    public String getArmazenamentoLivre() {
        return ArmazenamentoLivre;
    }

    public void setArmazenamentoLivre(String armazenamentoLivre) {
        ArmazenamentoLivre = armazenamentoLivre;
    }
}
