package DAO;

import conexoes.ConexaoNuvem;
import entidades.DiscoRegistro;
import conexoes.Conexao;
import entidades.ProcessoRegistro;
import helpers.Helper;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscoDAO {

public static void inserirRegistroDisco(DiscoRegistro discoRegistro){
        inserirRegistroDiscoLocal(discoRegistro);
        inserirRegistroDiscoNuvem(discoRegistro);
}
public static void inserirRegistroDiscoLocal(DiscoRegistro discoRegistro){
    String sql = "INSERT INTO DiscoRegistro (fkMaquina,modelo,dtRegistro,armazenamentoTotal,armazenamentoLivre) VALUES" +
            "(?,?,?,?,?)";
    PreparedStatement ps = null;
    try{

        ps = Conexao.conn.prepareStatement(sql);
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
    }catch (Exception e){
        String stackTrace = Helper.getStackTraceAsString(e);
        Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
    }
}
public static void inserirRegistroDiscoNuvem(DiscoRegistro discoRegistro){
    String sql = "INSERT INTO DiscoRegistro (fkMaquina,modelo,dtRegistro,armazenamentoTotal,armazenamentoLivre) VALUES" +
            "(?,?,?,?,?)";
    PreparedStatement ps = null;
    try{
        ps = ConexaoNuvem.conn.prepareStatement(sql);
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
    }catch (Exception e){
        String stackTrace = Helper.getStackTraceAsString(e);
        Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
    }
}

}
