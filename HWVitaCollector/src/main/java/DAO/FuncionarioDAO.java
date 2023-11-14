package DAO;

import conexoes.Conexao;
import entidades.Funcionario;
import helpers.Helper;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FuncionarioDAO {
    public static Funcionario getFuncionario(String emailFuncionario, String senhaFuncionario){
        String sql = "SELECT * FROM Funcionario WHERE email = ? AND senha = ?";
        PreparedStatement ps = null;
        try {
            if(Conexao.getConexao() == null){
                return null;
            }
            ps = Objects.requireNonNull(Conexao.conn).prepareStatement(sql);
            ps.setString(1,emailFuncionario);
            ps.setString(2,senhaFuncionario);
            ps.execute();
            ResultSet result = ps.getResultSet();
            Funcionario f = null;
            while (result.next()) {
               f = new Funcionario();
               f.setId(result.getInt(1));
               f.setFkHospital(result.getInt(2));
               f.setEmail(result.getString(3));
               f.setSenha(result.getString(4));
               f.setNome(result.getString(5));
               f.setTelefone(result.getString(6));
               f.setFuncao(result.getString(7));
            }
            ps.close();
            return f;
        }catch (Exception e){
            System.out.println("Não foi possivel acessar o banco de dados que contém o registro de funcionários.");
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage()+stackTrace);
        }
            return  null;
    }
}
