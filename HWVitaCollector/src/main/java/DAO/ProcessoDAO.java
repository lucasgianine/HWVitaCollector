package DAO;

import com.github.britooo.looca.api.util.Conversor;
import conexao.Conexao;
import entidades.Processo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessoDAO {
    public void inserirProcesso(Processo processo){
        String sql = "INSERT INTO PROCESSO (FkMaquina,Nome,DtCaptura,Threads,UsoMemoriaRam) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1,processo.getFkMaquina());
            ps.setString(2,processo.getNome());
            ps.setString(3,processo.getDataCaptura());
            ps.setInt(4,processo.getThreads());
            ps.setString(5, Conversor.formatarBytes(processo.getResidentMemory()));
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

