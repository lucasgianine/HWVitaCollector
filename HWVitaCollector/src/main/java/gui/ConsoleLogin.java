package gui;
import DAO.*;
import entidades.*;
import helpers.*;

import java.util.Scanner;

public class ConsoleLogin {

    public static void handleConsoleLogin(String UUID) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Faça login com suas credenciais cadastradas");
        System.out.print("Digite seu email: ");
        String emailFuncionario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaFuncionario = scanner.nextLine();

        Funcionario funcionario = FuncionarioDAO.getFuncionario(emailFuncionario, senhaFuncionario);

        if (VerificacaoHelper.funcionarioIsAutenticado(funcionario)) {
            if (!VerificacaoHelper.maquinaIsCadastrada(UUID)) {
                System.out.println("╔════════════════════════════╗");
                System.out.println(String.format("║ %1$-26s ║", "Cadastre sua máquina:"));
                System.out.println("╠════════════════════════════╣");

                System.out.print("║ Local: ");
                String local = scanner.nextLine();
                System.out.print("║ Cadastre o responsável: ");
                String responsavel = scanner.nextLine();

                Maquina maquina = new Maquina(UUID, funcionario.getFkHospital(), local, responsavel);
                MaquinaDAO.registrarMaquina(maquina);

                System.out.println("╚════════════════════════════╝");
                System.out.println("Máquina cadastrada com sucesso!");
            }
        } else {
            System.out.println("╔════════════════════════════╗");
            System.out.println(String.format("║ %1$-26s ║", "Credenciais inválidas."));
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ Programa encerrado.        ║");
            System.out.println("╚════════════════════════════╝");
            System.exit(404);
        }
        Login.fkHospitalStatic = funcionario.getFkHospital();
        HardwareExtractor.HardwareExtractorLoop(UUID);
    }
}
