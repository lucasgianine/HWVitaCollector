import DAO.*;
import entidades.*;
import gui.Login;
import helpers.AsciiHelper;
import helpers.HardwareExtractor;
import helpers.Logging;
import helpers.VerificacaoHelper;
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
                    String apelido = "Máquina Recepção, GUICHE 5";
                    Maquina maquina = new Maquina(UUID, funcionario.getFkHospital(), apelido, funcionario.getNome());
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