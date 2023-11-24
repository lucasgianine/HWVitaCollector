import DAO.*;
import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.*;
import gui.ConsoleLogin;
import gui.Login;
import helpers.*;
import integracaoSlack.Alertas;
import oshi.SystemInfo;

import java.util.Scanner;

@SuppressWarnings("SpellCheckingInspection")
public class Main {

    public static void main(String[] args) {
        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();

        // Bloco try para criação dos logs.
        try {
            Logging logging = new Logging();
            logging.CreateLog();
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        // Bloco try para fazer as conexões necessárias.
        try {
            ConexaoNuvem.getConexaoNuvem();
            Conexao.getConexao();
        } catch (Exception e) {
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler, e.getMessage() + stackTrace);
            System.out.println("Erro ao estabelecer conexões. Detalhes: " + e.getMessage());
        }

        // Verifica se a conexão principal, a conexão que faz as validações, está nula.
        // Se estiver, gera um log e encerra o programa.
        if (Conexao.conn == null) {
            Logging.AddLogInfo(Logging.fileHandler, "Conexão com banco principal não foi estabelecida, programa interrompido: Main: 31");
            System.exit(0);
        }

        // Bloco de login e iniciação do programa via interface.
        boolean hasInterface = true;
        AsciiHelper.vitaHealthAscii();

        try {
            Login.mainLogin();
            HardwareExtractor.HardwareExtractorLoop(UUID);

        } catch (Exception e) {
            hasInterface = false;
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler, stackTrace);
        }

        if (!hasInterface) {
            ConsoleLogin.handleConsoleLogin(UUID);
        }
    }
}