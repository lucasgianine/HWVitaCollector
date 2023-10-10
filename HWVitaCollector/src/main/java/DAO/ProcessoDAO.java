package DAO;

import com.github.britooo.looca.api.util.Conversor;
import conexao.Conexao;
import entidades.ProcessoRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessoDAO {
    public void inserirRegistroProcesso(ProcessoRegistro processoRegistro){
        String sql = "INSERT INTO processoregistro (fkMaquina,nome,dtRegistro,threads,usoMemoriaRam) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, processoRegistro.getFkMaquina());
            ps.setString(2, processoRegistro.getNome());
            ps.setString(3, processoRegistro.getDtRegistro());
            ps.setInt(4, processoRegistro.getThreads());
            ps.setString(5, Conversor.formatarBytes(processoRegistro.getResidentMemory()));
            ps.execute();
            System.out.println("Executando Instrução sql " + sql);
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

