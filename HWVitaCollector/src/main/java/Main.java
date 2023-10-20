import DAO.*;
import com.github.britooo.looca.api.core.Looca;
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
        //Login.mainLogin();
        //HardwareExtractor.HardwareExtractorLoop(UUID);
        System.out.println(UUID);
     }
    }



