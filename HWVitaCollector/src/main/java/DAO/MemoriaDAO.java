package DAO;

import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.MemoriaRegistro;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage());
        }
    }
    public static void inserirRegistroMemoriaNuvem(MemoriaRegistro memoriaRegistro){
        String sql = "INSERT INTO MemoriaRegistro (fkMaquina,dtRegistro,qtdTotal,usoMemoria) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage());
        }
    }
}
