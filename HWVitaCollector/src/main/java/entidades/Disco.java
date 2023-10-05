package entidades;

import com.github.britooo.looca.api.util.Conversor;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Disco {

    private int fkMaquina;
    private String modelo;

    private String dtRegistro;
    private String armazenamentoTotal;
    private String armazenamentoLivre;


    public String getModel() {
        return modelo;
    }

    public int getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(int fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getArmazenamentoTotal() {
        return armazenamentoTotal;
    }

    public void setArmazenamentoTotal(String armazenamentoTotal) {
        this.armazenamentoTotal = armazenamentoTotal;
    }

    public String getArmazenamentoLivre() {
        return armazenamentoLivre;
    }

    public void setArmazenamentoLivre(String armazenamentoLivre) {
        this.armazenamentoLivre = armazenamentoLivre;
    }


    public static List<Disco> getDiscos(){
        SystemInfo systemInfo = new SystemInfo();
        List<Disco> discos = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        int qtdDiscos =  systemInfo.getHardware().getDiskStores().size();
        HWDiskStore HWDisk;
        for (int i = 0; i < qtdDiscos; i++) {
            Disco disco = new Disco();
            HWDisk = systemInfo.getHardware().getDiskStores().get(i);
            paths.add(HWDisk.getPartitions().get(0).getMountPoint());
            File file = new File(paths.get(i));
            String diskModel = HWDisk.getModel().replaceAll("[(Unidades de disco padrão)]", "");
            String totalSpace = (Conversor.formatarBytes(HWDisk.getSize()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.'));
            String freeSpace = Conversor.formatarBytes(file.getFreeSpace()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.');

            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);

            disco.setArmazenamentoLivre(freeSpace);
            disco.setModelo(diskModel);
            disco.setArmazenamentoTotal(totalSpace);
            disco.setDtRegistro(dataFormatada);

            discos.add(disco);
        }
        return discos;
    }


}
