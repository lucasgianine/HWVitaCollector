import DAO.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import entidades.*;
import gui.Login;
import helpers.HardwareExtractor;
import helpers.VerificacaoHelper;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();

        /*System.out.println("antes de abrir o login");
        Login.mainLogin();
        HardwareExtractor.HardwareExtractorLoop(UUID);
        */


        System.out.println(UUID);
        //BLOCO PARA MÁQUINAS SEM INTERFACE --
            Funcionario funcionario = FuncionarioDAO.getFuncionario("leo@gmail.com","senha@123");
            if(VerificacaoHelper.maquinaIsCadastrada(UUID)){
                System.out.println("Inicializando programa...");
            }else{
                String apelido = "Máquina Recepção, GUICHE 5";
                Maquina maquina = new Maquina(UUID,funcionario.getFkHospital(),apelido,funcionario.getNome());
                MaquinaDAO.registrarMaquina(maquina);
                System.out.println("Cadastrando maquina...");
            }
            HardwareExtractor.HardwareExtractorLoop(UUID);
        }
     }




