import DAO.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import entidades.*;
import helpers.VerificacaoHelper;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        String UUID = systemInfo.getHardware().getComputerSystem().getHardwareUUID();

        Funcionario funcionario = FuncionarioDAO.getFuncionario("leo@gmail.com","senha@123");
        assert funcionario != null;
        boolean taVerificado = VerificacaoHelper.funcionarioIsAutenticado(funcionario);
           boolean maquinaRegistrada = false;
        if(taVerificado){
            maquinaRegistrada = VerificacaoHelper.maquinaIsCadastrada(UUID);
        }
        System.out.println("Usuário verificado? "+ taVerificado);
        System.out.println("Maquina cadastrada? "+ maquinaRegistrada);
        System.out.println(UUID);


        // Apenas enquanto o método de identificação e cadastramento de máquina não está pronto
        int fkMaquina = 400;
        Runnable task = () -> {
            Date dataAtual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataFormatada = dateFormat.format(dataAtual);

               List<ProcessoRegistro> processoRegistros = ProcessoRegistro.getProcessos();
            for (ProcessoRegistro processo: processoRegistros) {
                processo.setFkMaquina(fkMaquina);
                ProcessoDAO.inserirRegistroProcesso(processo);
            }

              List<DiscoRegistro> discoRegistros = DiscoRegistro.getDiscos();
            for (DiscoRegistro discoRegistro: discoRegistros) {
                discoRegistro.setFkMaquina(fkMaquina);
                DiscoDAO.inserirRegistroDisco(discoRegistro);
            }

            String temperaturaCpu = String.format("%.2f ºC",CpuRegistro.getCpuTemperatureValue());
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
        };



      // scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }
}


