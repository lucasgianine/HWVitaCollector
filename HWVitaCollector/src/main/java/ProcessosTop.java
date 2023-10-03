import com.github.britooo.looca.api.core.Looca;
import oshi.SystemInfo;
import oshi.hardware.platform.windows.WindowsPowerSource;
import oshi.software.os.OSProcess;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProcessosTop {
    public static void main(String[] args) {
        Looca looca = new Looca();
        SystemInfo systemInfo = new SystemInfo();
      //  System.out.println(looca.getGrupoDeProcessos().getProcessos());

        List<OSProcess> processos = systemInfo.getOperatingSystem().getProcesses();
        List<String> nomeProcessos = new ArrayList<>();
        boolean processoJaFoiColhido = false;
        int threadCountChrome = 0;
        boolean primeiraRepeticao = true;
        for (OSProcess processo:processos) {
            if(primeiraRepeticao){
                nomeProcessos.add(processo.getName());
                primeiraRepeticao = false;
            }
            for (int i = 0; i < nomeProcessos.size(); i++) {
                if(nomeProcessos.get(i).equals(processo.getName())){
                    processoJaFoiColhido = true;
                }
                if(!processoJaFoiColhido){
                    nomeProcessos.add(processo.getName());
                }
            }

            System.out.println("Id processo " + processo.getProcessID());
            System.out.println("Nome processo " +processo.getName());
            System.out.println("Bytes read ?? " +processo.getBytesRead());
            System.out.println("Thread Count " + processo.getThreadCount());
            System.out.println("----------------------------");
            processoJaFoiColhido = false;

        //incompleto
        }



    }
}
