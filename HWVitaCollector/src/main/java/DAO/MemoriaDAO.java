package DAO;

import com.github.britooo.looca.api.group.memoria.Memoria;
import conexao.Conexao;
import entidades.MemoriaRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemoriaDAO {
    public static void inserirRegistroMemoria(MemoriaRegistro memoriaRegistro){
        String sql = "INSERT INTO memoriaRegistro (fkMaquina,dtRegistro,qtdTotal,usoMemoria) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1,memoriaRegistro.getFkMaquina());
        ps.setString(2,memoriaRegistro.getDtRegistro());
        ps.setString(3,memoriaRegistro.getQtdTotal());
        ps.setString(4,memoriaRegistro.getUsoMemoria());
        ps.execute();
            System.out.println("Executou memoria");
        ps.close();
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}
