package helpers;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Logging {

        public static void main(String[] args) {
            try{
                throw new RuntimeException("Aqui é os palhaço muito loco muhaahahaha");
            }catch (Exception e){
                Log(e.getMessage());
            }
        }
        public static void Log (String mensagem){
            HWDiskStore disco = new SystemInfo().getHardware().getDiskStores().get(0);
            String caminhoDaPasta = disco.getPartitions().get(0).getMountPoint();


            String nomeDaPasta = "HWVitaCollectorLog";
            String nomeBaseArquivoLog = "log";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String dataHoraAtual = dateFormat.format(new Date());
            String nomeArquivoLog = dataHoraAtual + "_" + nomeBaseArquivoLog + ".log";

            File pasta = new File(caminhoDaPasta, nomeDaPasta);
            File arquivoLog = new File(pasta, nomeArquivoLog);

            Logger logger = Logger.getLogger("MeuLog");
            FileHandler fileHandler;

            try {
                if (!pasta.exists()) {
                    if (pasta.mkdirs()) {
                        System.out.println("Pasta criada com sucesso.");
                    } else {
                        System.err.println("Falha ao criar a pasta.");
                        return;
                    }
                }

                fileHandler = new FileHandler(arquivoLog.getPath());

                SimpleFormatter formatter = new SimpleFormatter() {
                    @Override
                    public String format(java.util.logging.LogRecord record) {
                        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    }
                };

                fileHandler.setFormatter(formatter);
                logger.addHandler(fileHandler);

                while (true) { //só para rodar o log mais de uma vez, só para quando finalizar
                    logger.info(mensagem);
                    Thread.sleep(10000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
