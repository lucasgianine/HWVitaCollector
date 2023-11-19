package entidades;

import com.github.britooo.looca.api.util.Conversor;
import helpers.Helper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@ToString
public class DiscoRegistro  extends Registro{
    private String model;
    private String espacoTotal;
    private String espacoLivre;

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
            File file;
            if(!systemInfo.getOperatingSystem().getFamily().toLowerCase().contains("windows")){
               file = new File("/");
            }else{
                file = new File(paths.get(i));
            }
           //System.out.println(file.getPath());
            String diskModel = HWDisk.getModel().replaceAll("[(Unidades de disco padrão)]", "");
            String espacoTotal = (Conversor.formatarBytes(HWDisk.getSize()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.'));
            String espacoLivre = Conversor.formatarBytes(file.getFreeSpace()).replaceAll("TiB","TB").replaceAll("MiB","MB").replaceAll("GiB","GB").replace(',','.');
            //System.out.println(espacoTotal);
            //System.out.println(espacoLivre);


            String dataFormatada = Helper.getDataFormatada();

            discoRegistro.setDtRegistro(dataFormatada);
            discoRegistro.setEspacoLivre(espacoLivre);
            discoRegistro.setModel(diskModel);
            discoRegistro.setEspacoTotal(espacoTotal);

            discoRegistros.add(discoRegistro);
        }
        return discoRegistros;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEspacoTotal() {
        return espacoTotal;
    }

    public void setEspacoTotal(String espacoTotal) {
        this.espacoTotal = espacoTotal;
    }

    public String getEspacoLivre() {
        return espacoLivre;
    }

    public void setEspacoLivre(String espacoLivre) {
        this.espacoLivre = espacoLivre;
    }
}
