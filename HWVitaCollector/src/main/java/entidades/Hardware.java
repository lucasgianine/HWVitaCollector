package entidades;

public class Hardware {
    private int FkMaquina;
    private String UsoProcessador;
    private String TempProcessador;
    private String UsoMemoria;
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



    public String getUsoMemoria() {
        return UsoMemoria;
    }

    public void setUsoMemoria(String usoMemoria) {
        UsoMemoria = usoMemoria;
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
