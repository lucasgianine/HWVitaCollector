package DAO;

import com.github.britooo.looca.api.util.Conversor;
import conexao.Conexao;
import entidades.Disco;
import entidades.Processo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscoDAO {
    public void inserirDisco(Disco disco){
        String sql = "INSERT INTO DISCO (FkMaquina,Modelo,dtRegistro,ArmazenamentoTotal,ArmazenamentoLivre) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1,disco.getFkMaquina());
            ps.setString(2, disco.getModel());
            ps.setString(3,disco.getDtRegistro());
            ps.setString(4,disco.getArmazenamentoTotal());
            ps.setString(5,disco.getArmazenamentoLivre());
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
