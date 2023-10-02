package entidades;

public class Hardware {
    private int FkMaquina;
    private double UsoProcessador;
    private double TempProcessador;
    private double FreqProcessador;
    private double UsoMemoria;
    private double TempMemoria;
    private double FreqMemoria;
    private double ArmazenamentoTotal;
    private double ArmazenamentoLivre;

    public int getFkMaquina() {
        return FkMaquina;
    }

    public void setFkMaquina(int fkMaquina) {
        FkMaquina = fkMaquina;
    }

    public double getUsoProcessador() {
        return UsoProcessador;
    }

    public void setUsoProcessador(double usoProcessador) {
        UsoProcessador = usoProcessador;
    }

    public double getTempProcessador() {
        return TempProcessador;
    }

    public void setTempProcessador(double tempProcessador) {
        TempProcessador = tempProcessador;
    }

    public double getFreqProcessador() {
        return FreqProcessador;
    }

    public void setFreqProcessador(double freqProcessador) {
        FreqProcessador = freqProcessador;
    }

    public double getUsoMemoria() {
        return UsoMemoria;
    }

    public void setUsoMemoria(double usoMemoria) {
        UsoMemoria = usoMemoria;
    }

    public double getTempMemoria() {
        return TempMemoria;
    }

    public void setTempMemoria(double tempMemoria) {
        TempMemoria = tempMemoria;
    }

    public double getFreqMemoria() {
        return FreqMemoria;
    }

    public void setFreqMemoria(double freqMemoria) {
        FreqMemoria = freqMemoria;
    }

    public double getArmazenamentoTotal() {
        return ArmazenamentoTotal;
    }

    public void setArmazenamentoTotal(double armazenamentoTotal) {
        ArmazenamentoTotal = armazenamentoTotal;
    }

    public double getArmazenamentoLivre() {
        return ArmazenamentoLivre;
    }

    public void setArmazenamentoLivre(double armazenamentoLivre) {
        ArmazenamentoLivre = armazenamentoLivre;
    }
}
