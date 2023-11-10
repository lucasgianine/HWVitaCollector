package helpers;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Logging {
    public static FileHandler fileHandler;

    public static void AddLogInfo(FileHandler fileHandler ,String mensagem) {
        if(fileHandler == null) return;
        Logger logger = Logger.getLogger("MeuLog");
        SimpleFormatter formatter = new SimpleFormatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                String date_record = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - " + record.getMessage();
                return date_record + "\n";
            }
        };

        for(Handler h:logger.getHandlers())
        {
            h.close();   //must call h.close or a .LCK file will remain.
        }


        fileHandler.setFormatter(formatter);
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        logger.info(mensagem);


    }

    public void CreateLog() throws IOException {
        HWDiskStore disco = new SystemInfo().getHardware().getDiskStores().get(0);
        String caminhoDaPasta = disco.getPartitions().get(0).getMountPoint();


        String nomeDaPasta = "HWVitaCollectorLog";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataHoraAtual = dateFormat.format(new Date());
        String nomeArquivoLog = dataHoraAtual + "log_.txt";


        File pasta = new File(caminhoDaPasta, nomeDaPasta);
        File arquivoLog = new File(pasta.getPath(), nomeArquivoLog);


        FileHandler filo;


        if (!pasta.exists()) {
            if (pasta.mkdirs()) {
                System.out.println("Pasta criada com sucesso.");
            } else {
                System.err.println("Falha ao criar a pasta.");
            }
        }


        filo = new FileHandler(arquivoLog.getPath());

        fileHandler = filo;

    }
}
