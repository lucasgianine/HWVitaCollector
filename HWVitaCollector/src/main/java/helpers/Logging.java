package helpers;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    public static FileHandler fileHandler;
    private static Logger logger;

    public static void AddLogInfo(FileHandler xx, String mensagem) {
        if (logger == null || fileHandler == null) {
            return;
        }

        logger.info(mensagem);
    }

    public void CreateLog() throws IOException {
        logger = Logger.getLogger("MeuLog");
        logger.setUseParentHandlers(false);

        if (fileHandler == null) {
            HWDiskStore disco = new SystemInfo().getHardware().getDiskStores().get(0);
            String caminhoDaPasta = disco.getPartitions().get(0).getMountPoint();

            String nomeDaPasta = "HWVitaCollectorLog";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String dataHoraAtual = dateFormat.format(new Date());
            String nomeArquivoLog = dataHoraAtual + "_log.txt";

            File pasta = new File(caminhoDaPasta, nomeDaPasta);
            File arquivoLog = new File(pasta.getPath(), nomeArquivoLog);

            if (!pasta.exists() && pasta.mkdirs()) {
                System.out.println("Pasta criada com sucesso.");
            } else if (!pasta.exists()) {
                System.err.println("Falha ao criar a pasta.");
                return;
            }

            fileHandler = new FileHandler(arquivoLog.getPath(), true); // O true aqui especifica que vai ser em append
            SimpleFormatter formatter = new SimpleFormatter() {
                @Override
                public String format(java.util.logging.LogRecord record) {
                    String date_record = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - " + record.getMessage();
                    return date_record + "\n";
                }
            };
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        }
    }
}
