package DAO;

import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.MemoriaRegistro;
import helpers.Helper;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemoriaDAO {
    public static void inserirRegistroMemoria(MemoriaRegistro memoriaRegistro){
        try {
            inserirRegistroMemoriaLocal(memoriaRegistro);
        }catch (Exception e){
            System.out.println("Não foi possivel conectar no banco Local");
        }
        try {
            inserirRegistroMemoriaLocal(memoriaRegistro);
        }catch (Exception e){
            System.out.println("Não foi possivel conectar no banco em Nuvem");
        }

    }
    public static void inserirRegistroMemoriaLocal(MemoriaRegistro memoriaRegistro){
        String sql = "INSERT INTO MemoriaRegistro (fkMaquina,dtRegistro,qtdTotal,usoMemoria) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,memoriaRegistro.getFkMaquina());
            ps.setString(2,memoriaRegistro.getDtRegistro());
            ps.setString(3,memoriaRegistro.getQtdTotal());
            ps.setString(4,memoriaRegistro.getUsoMemoria());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de memória (Local)                                                                       |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,memoriaRegistro));
            ps.close();
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
    public static void inserirRegistroMemoriaNuvem(MemoriaRegistro memoriaRegistro){
        String sql = "INSERT INTO MemoriaRegistro (fkMaquina,dtRegistro,qtdTotal,usoMemoria) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            ps = ConexaoNuvem.getConexaoNuvem().prepareStatement(sql);
            ps.setString(1,memoriaRegistro.getFkMaquina());
            ps.setString(2,memoriaRegistro.getDtRegistro());
            ps.setString(3,memoriaRegistro.getQtdTotal());
            ps.setString(4,memoriaRegistro.getUsoMemoria());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de memória (Nuvem)                                                                |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,memoriaRegistro));
            ps.close();
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
}
