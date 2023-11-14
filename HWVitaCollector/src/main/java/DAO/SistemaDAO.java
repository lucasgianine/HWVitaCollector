package DAO;

import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.SistemaRegistro;
import helpers.Helper;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SistemaDAO {
    public static void inserirRegistroSistema(SistemaRegistro sistemaRegistro){
        try {
            inserirRegistroSistemaLocal(sistemaRegistro);
        }catch (Exception e){
            System.out.println("Não foi possivel conectar no banco Local");
        }
        try {
            inserirRegistroSistemaNuvem(sistemaRegistro);
        }catch (Exception e){
            System.out.println("Não foi possivel conectar no banco em Nuvem");
        }

    }
    public static void inserirRegistroSistemaLocal(SistemaRegistro sistemaRegistro){
        String sql = "INSERT INTO SistemaRegistro (fkMaquina,dtRegistro,tempoDeAtividadeSistema,qtdDispositivosUsb) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            ps = Conexao.conn.prepareStatement(sql);
            ps.setString(1,sistemaRegistro.getFkMaquina());
            ps.setString(2,sistemaRegistro.getDtRegistro());
            ps.setString(3,sistemaRegistro.getTempoDeAtividadeSO());
            ps.setInt(4,sistemaRegistro.getQtdDisposivosUsbConectados());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de sistema (Local)                                                                |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,sistemaRegistro));
            ps.close();
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
    public static void inserirRegistroSistemaNuvem(SistemaRegistro sistemaRegistro){
        String sql = "INSERT INTO SistemaRegistro (fkMaquina,dtRegistro,tempoDeAtividadeSistema,qtdDispositivosUsb) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            ps = ConexaoNuvem.conn.prepareStatement(sql);
            ps.setString(1,sistemaRegistro.getFkMaquina());
            ps.setString(2,sistemaRegistro.getDtRegistro());
            ps.setString(3,sistemaRegistro.getTempoDeAtividadeSO());
            ps.setInt(4,sistemaRegistro.getQtdDisposivosUsbConectados());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de sistema (Nuvem)                                                                |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,sistemaRegistro));
            ps.close();
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
}

