package DAO;

import entidades.DiscoRegistro;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscoDAO {

public static void inserirRegistroDisco(DiscoRegistro discoRegistro){

    String sql = "INSERT INTO DiscoRegistro (fkMaquina,modelo,dtRegistro,armazenamentoTotal,armazenamentoLivre) VALUES" +
            "(?,?,?,?,?)";
    PreparedStatement ps = null;

    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setString(1, discoRegistro.getFkMaquina());
        ps.setString(2, discoRegistro.getModel());
        ps.setString(3, discoRegistro.getDtRegistro());
        ps.setString(4, discoRegistro.getTotalSpace());
        ps.setString(5, discoRegistro.getFreeSpace());
        ps.execute();
        ps.close();
        System.out.println("Executando a instrução sql \n" + sql);
    }catch (SQLException | ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
}
}
