package entidades;

import com.github.britooo.looca.api.util.Conversor;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscoRegistro {

    private String fkMaquina;

    private String dtRegistro;
    private String model;
    private String totalSpace;
    private String freeSpace;

    public String getModel() {
        return model;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }


    public static List<DiscoRegistro> getDiscos(){
        List<DiscoRegistro> discoRegistros = new ArrayList<>();
        SystemInfo systemInfo = new SystemInfo();
        List<String> paths = new ArrayList<>();
        int qtdDiscos =  systemInfo.getHardware().getDiskStores().size();
        HWDiskStore HWDisk;
        for (int i = 0; i < qtdDiscos; i++) {
            DiscoRegistro discoRegistro = new DiscoRegistro();
            HWDisk = systemInfo.getHardware().getDiskStores().get(i);
            paths.add(HWDisk.getPartitions().get(0).getMountPoint());
            File file = new File(paths.get(i));
            String diskModel = HWDisk.getModel().replaceAll("[(Unidades de disco padrão)]", "");
            String totalSpace = (Conversor.formatarBytes(HWDisk.getSize()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.'));
            String freeSpace = Conversor.formatarBytes(file.getFreeSpace()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.');



            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);

            discoRegistro.setDtRegistro(dataFormatada);
            discoRegistro.setFreeSpace(freeSpace);
            discoRegistro.setModel(diskModel);
            discoRegistro.setTotalSpace(totalSpace);

            discoRegistros.add(discoRegistro);
        }
        return discoRegistros;


    }
}
