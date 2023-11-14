package entidades;

public class ParametrosAlerta {
    private int fkHospital;
    private String maxTempProcessador;
    private String maxUsoProcessador;
    private String maxUsoMemoria;
    private String minLivreDisco;
    private String maxTempoDeAtividade;
    private String minQtdUsb;
    private String processoMaxUsoRam;
    private String tempoParaAlertaUsoProcessador;
    private String tempoParaAlertaUsoMemoria;
    private String tempoParaAlertaTempProcessador;
    private String tempoParaAlertaUsoRamProcessos;


    public ParametrosAlerta() {
    }

    public int getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(int fkHospital) {
        this.fkHospital = fkHospital;
    }

    public String getMaxTempProcessador() {
        return maxTempProcessador;
    }

    public void setMaxTempProcessador(String maxTempProcessador) {
        this.maxTempProcessador = maxTempProcessador;
    }

    public String getMaxUsoProcessador() {
        return maxUsoProcessador;
    }

    public void setMaxUsoProcessador(String maxUsoProcessador) {
        this.maxUsoProcessador = maxUsoProcessador;
    }

    public String getMaxUsoMemoria() {
        return maxUsoMemoria;
    }

    public void setMaxUsoMemoria(String maxUsoMemoria) {
        this.maxUsoMemoria = maxUsoMemoria;
    }

    public String getMinLivreDisco() {
        return minLivreDisco;
    }

    public void setMinLivreDisco(String minLivreDisco) {
        this.minLivreDisco = minLivreDisco;
    }

    public String getMaxTempoDeAtividade() {
        return maxTempoDeAtividade;
    }

    public void setMaxTempoDeAtividade(String maxTempoDeAtividade) {
        this.maxTempoDeAtividade = maxTempoDeAtividade;
    }

    public String getMinQtdUsb() {
        return minQtdUsb;
    }

    public void setMinQtdUsb(String minQtdUsb) {
        this.minQtdUsb = minQtdUsb;
    }

    public String getProcessoMaxUsoRam() {
        return processoMaxUsoRam;
    }

    public void setProcessoMaxUsoRam(String processoMaxUsoRam) {
        this.processoMaxUsoRam = processoMaxUsoRam;
    }

    public String getTempoParaAlertaUsoProcessador() {
        return tempoParaAlertaUsoProcessador;
    }

    public void setTempoParaAlertaUsoProcessador(String tempoParaAlertaUsoProcessador) {
        this.tempoParaAlertaUsoProcessador = tempoParaAlertaUsoProcessador;
    }

    public String getTempoParaAlertaUsoMemoria() {
        return tempoParaAlertaUsoMemoria;
    }

    public void setTempoParaAlertaUsoMemoria(String tempoParaAlertaUsoMemoria) {
        this.tempoParaAlertaUsoMemoria = tempoParaAlertaUsoMemoria;
    }

    public String getTempoParaAlertaTempProcessador() {
        return tempoParaAlertaTempProcessador;
    }

    public void setTempoParaAlertaTempProcessador(String tempoParaAlertaTempProcessador) {
        this.tempoParaAlertaTempProcessador = tempoParaAlertaTempProcessador;
    }

    public String getTempoParaAlertaUsoRamProcessos() {
        return tempoParaAlertaUsoRamProcessos;
    }

    public void setTempoParaAlertaUsoRamProcessos(String tempoParaAlertaUsoRamProcessos) {
        this.tempoParaAlertaUsoRamProcessos = tempoParaAlertaUsoRamProcessos;
    }
}
