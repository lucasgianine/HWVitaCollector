package helpers;

import DAO.FuncionarioDAO;
import DAO.MaquinaDAO;
import entidades.Funcionario;
import entidades.Maquina;

public class VerificacaoHelper {
    public static boolean funcionarioIsAutenticado(Funcionario funcionario){
        if(funcionario == null) return false;
        String email = funcionario.getEmail();
        String senha = funcionario.getSenha();
        Funcionario f = FuncionarioDAO.getFuncionario(email, senha);
        return f != null && f.getNome() != null && !f.getNome().isEmpty();
    }

    public static boolean maquinaIsCadastrada(String UUID){
        Maquina maquina = MaquinaDAO.getMaquinaByUUID(UUID);
        return maquina != null;
    }
}
