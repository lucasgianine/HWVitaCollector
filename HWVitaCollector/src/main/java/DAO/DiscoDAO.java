package DAO;

import conexoes.ConexaoNuvem;
import entidades.DiscoRegistro;
import conexoes.Conexao;
import entidades.ProcessoRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscoDAO {

public static void inserirRegistroDisco(DiscoRegistro discoRegistro){
    inserirRegistroDiscoLocal(discoRegistro);
    //inserirRegistroDiscoNuvem(discoRegistro);
}
public static void inserirRegistroDiscoLocal(DiscoRegistro discoRegistro){
    String sql = "INSERT INTO DiscoRegistro (fkMaquina,modelo,dtRegistro,armazenamentoTotal,armazenamentoLivre) VALUES" +
            "(?,?,?,?,?)";
    PreparedStatement ps = null;
    try{

        Class.forName("com.mysql.cj.jdbc.Driver");
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setString(1, discoRegistro.getFkMaquina());
        ps.setString(2, discoRegistro.getModel());
        ps.setString(3, discoRegistro.getDtRegistro());
        ps.setString(4, discoRegistro.getEspacoTotal());
        ps.setString(5, discoRegistro.getEspacoLivre());
        ps.execute();
        //System.out.println("Executando a instrução sql \n" + sql);
        System.out.println(String.format(
                """
                |---------------------------------------------------------------------------------------------------------|
                | Inserindo informações de Disco (Local)                                                                  |
                | %s                                                                                                       \s
                |---------------------------------------------------------------------------------------------------------|
                """,discoRegistro));
        ps.close();
    }catch (SQLException | ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
}
public static void inserirRegistroDiscoNuvem(DiscoRegistro discoRegistro){
    String sql = "INSERT INTO DiscoRegistro (fkMaquina,modelo,dtRegistro,armazenamentoTotal,armazenamentoLivre) VALUES" +
            "(?,?,?,?,?)";
    PreparedStatement ps = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        ps = ConexaoNuvem.getConexaoNuvem().prepareStatement(sql);
        ps.setString(1, discoRegistro.getFkMaquina());
        ps.setString(2, discoRegistro.getModel());
        ps.setString(3, discoRegistro.getDtRegistro());
        ps.setString(4, discoRegistro.getEspacoTotal());
        ps.setString(5, discoRegistro.getEspacoLivre());
        ps.execute();
        //System.out.println("Executando a instrução sql \n" + sql);
        System.out.println(String.format(
                """
                |---------------------------------------------------------------------------------------------------------|
                | Inserindo informações de Disco (Nuvem)                                                                  |
                | %s                                                                                                       \s
                |---------------------------------------------------------------------------------------------------------|
                """,discoRegistro));
        ps.close();
    }catch (SQLException | ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
}

}
