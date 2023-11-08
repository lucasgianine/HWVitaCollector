import DAO.*;
import entidades.*;
import gui.Login;
import helpers.AsciiHelper;
import helpers.HardwareExtractor;
import helpers.Logging;
import helpers.VerificacaoHelper;
import integracaoSlack.Alertas;
import oshi.SystemInfo;
import java.util.*;
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void main(String[] args) {
        try {
            Logging logging = new Logging();
            logging.CreateLog();
        }catch (Exception e){
            e.fillInStackTrace();
        }

        String UUID = new SystemInfo().getHardware().getComputerSystem().getHardwareUUID();
        boolean hasInterface = true;
        AsciiHelper.vitaHealthAscii();
        try {
            Login.mainLogin();
            HardwareExtractor.HardwareExtractorLoop(UUID);
            Alertas.AlertasLoop(UUID);
        }catch (Exception e) {
            hasInterface = false;
        }
        if (!hasInterface) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Faça login com suas credenciais cadastradas");
            System.out.println("Digite seu email: ");
            String emailFuncionario = scanner.nextLine();
            System.out.println("Digite sua senha: ");
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
                Alertas.AlertasLoop(UUID);
            }
        }
    }