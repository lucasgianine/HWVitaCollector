import DAO.*;
import com.mysql.cj.log.Log;
import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.*;
import gui.Login;
import helpers.*;
import integracaoSlack.Alertas;
import oshi.SystemInfo;
import java.util.*;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();
        // BLOCO TRY PARA CRIAÇÃO DOS LOGS.
        try {
            Logging logging = new Logging();
            logging.CreateLog();
        }catch (Exception e){
            e.fillInStackTrace();
        }

        //BLOCO TRY PARA FAZER AS CONEXÕES NECESSÁRIAS
        try{
            ConexaoNuvem.getConexaoNuvem();
            Conexao.getConexao();
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
            System.out.println(e.getMessage());
        }


        //IF PARA VERIFICAR SE CONEXÃO PRINCIPAL, A CONEXÃO QUE FAZ AS VALIDAÇÕES ESTA NULA, SE ESTIVER, GERAMOS UM LOG E ENCERRAMOS O PROGRAMA
        if(Conexao.conn == null){
            Logging.AddLogInfo(Logging.fileHandler,"Conexão com banco principal não foi estabelecida, programa interrompido: Main: 31");
            System.exit(0);
        }

        //BLOCO DE LOGIN E INICIAÇÃO DO PROGRAMA VIA INTERFACE
        boolean hasInterface = true;
        AsciiHelper.vitaHealthAscii();
        try {
            Login.mainLogin();
            HardwareExtractor.HardwareExtractorLoop(UUID);
        }catch (Exception e) {
            hasInterface = false;
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,stackTrace);
        }

        if (!hasInterface) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Faça login com suas credenciais cadastradas");
            System.out.print("Digite seu email: ");
            String emailFuncionario = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            String senhaFuncionario = scanner.nextLine();

            Funcionario funcionario = FuncionarioDAO.getFuncionario(emailFuncionario, senhaFuncionario);
            if (VerificacaoHelper.funcionarioIsAutenticado(funcionario)) {
                if (!VerificacaoHelper.maquinaIsCadastrada(UUID)) {
                    System.out.println("Cadastre sua máquina: ");
                    System.out.print("Local: ");
                    String local = scanner.nextLine();
                    System.out.print("Cadastre o responsável: ");
                    String responsavel = scanner.nextLine();
                    Maquina maquina = new Maquina(UUID, funcionario.getFkHospital(), local, responsavel);
                    MaquinaDAO.registrarMaquina(maquina);
                }
            } else {
                System.out.println("Registro não encontrado");
                System.exit(404);
            }
                HardwareExtractor.HardwareExtractorLoop(UUID);
            }
        }
    }