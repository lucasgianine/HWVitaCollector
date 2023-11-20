package entidades;

public class ParametrosAlerta {
    private int fkHospital;
    private String maxTempProcessador;
    private String maxUsoProcessador;
    private String maxUsoMemoria;
    private String minLivreDisco;
    private String maxTempoDeAtividade;
    private String minQtdUsb;
    private String porcentagemMaximaRamProcesso;
    private String tempoParaAlertaSec;

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


    public String getPorcentagemMaximaRamProcesso() {
        return porcentagemMaximaRamProcesso;
    }

    public void setPorcentagemMaximaRamProcesso(String porcentagemMaximaRamProcesso) {
        this.porcentagemMaximaRamProcesso = porcentagemMaximaRamProcesso;
    }

    public String getTempoParaAlertaSec() {
        return tempoParaAlertaSec;
    }

    public void setTempoParaAlertaSec(String tempoParaAlertaSec) {
        this.tempoParaAlertaSec = tempoParaAlertaSec;
    }


    @Override
    public String toString() {
        return "ParametrosAlerta{" +
                "fkHospital=" + fkHospital +
                ", maxTempProcessador='" + maxTempProcessador + '\'' +
                ", maxUsoProcessador='" + maxUsoProcessador + '\'' +
                ", maxUsoMemoria='" + maxUsoMemoria + '\'' +
                ", minLivreDisco='" + minLivreDisco + '\'' +
                ", maxTempoDeAtividade='" + maxTempoDeAtividade + '\'' +
                ", minQtdUsb='" + minQtdUsb + '\'' +
                ", porcentagemMaximaRamProcesso='" + porcentagemMaximaRamProcesso + '\'' +
                ", tempoParaAlertaSec='" + tempoParaAlertaSec + '\'' +
                '}';
    }
}
