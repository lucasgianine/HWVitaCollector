package helpers;

import DAO.*;
import entidades.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HardwareExtractor {

    public static void HardwareExtractorLoop(String fkMaquina){
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try{
                Date dataAtual = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dataFormatada = dateFormat.format(dataAtual);

                List<ProcessoRegistro> processoRegistros = ProcessoRegistro.getProcessos();
                for (ProcessoRegistro processoRegistro: processoRegistros) {
                    processoRegistro.setFkMaquina(fkMaquina);
                    ProcessoDAO.inserirRegistroProcesso(processoRegistro);
                }

                List<DiscoRegistro> discoRegistros = DiscoRegistro.getDiscos();
                for (DiscoRegistro discoRegistro: discoRegistros) {
                    discoRegistro.setFkMaquina(fkMaquina);
                    DiscoDAO.inserirRegistroDisco(discoRegistro);
                }

                String temperaturaCpu = String.format("%.2f ºC", CpuRegistro.getCpuTemperatureValue());
                String usoCpu = CpuRegistro.getCpuUsePercentage();
                CpuRegistro cpuRegistro = new CpuRegistro(fkMaquina,dataFormatada,temperaturaCpu,usoCpu);
                CpuDAO.inserirRegistroCpu(cpuRegistro);

                String totalMemoria1 = MemoriaRegistro.getTotalMemory();
                String porcentagemUsoMemoria = MemoriaRegistro.getMemoryUsagePercentage();
                MemoriaRegistro memoriaRegistro = new MemoriaRegistro(fkMaquina,dataFormatada,totalMemoria1,porcentagemUsoMemoria);
                MemoriaDAO.inserirRegistroMemoria(memoriaRegistro);

                SistemaRegistro sistemaRegistro = new SistemaRegistro
                        (fkMaquina,dataFormatada,SistemaRegistro.getSystemUptime(),SistemaRegistro.getUsbGroupSize());
                SistemaDAO.inserirRegistroSistema(sistemaRegistro);

                System.out.println("Registrou Todos");
            }catch (Exception e){
                String stackTrace = Helper.getStackTraceAsString(e);
                Logging.AddLogInfo(Logging.fileHandler, "Erro no HardwareExtractor " + e.getMessage() + stackTrace);
            }

        };



        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }
}
