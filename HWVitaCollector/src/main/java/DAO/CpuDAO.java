package DAO;

import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.CpuRegistro;
import helpers.Helper;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CpuDAO {


    public static void inserirRegistroCpu(CpuRegistro cpuRegistro){
            inserirRegistroCpuLocal(cpuRegistro);
            inserirRegistroCpuNuvem(cpuRegistro);
    }

    public static void inserirRegistroCpuLocal(CpuRegistro cpuRegistro){
        String sql = "INSERT INTO CpuRegistro (fkMaquina,dtRegistro,temperatura,usoPorcentagem) VALUES" +
                "(?,?,?,?)";
        PreparedStatement ps = null;


        try{

            ps = Conexao.conn.prepareStatement(sql);
            ps.setString(1, cpuRegistro.getFkMaquina());
            ps.setString(2,cpuRegistro.getDtRegistro());
            ps.setString(3, cpuRegistro.getTemperatura());
            ps.setString(4, cpuRegistro.getUsoPorcentagem());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de Cpu (Local)                                                                    |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,cpuRegistro));
            ps.close();
            //System.out.println("Executando a instrução sql \n" + sql);
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
           Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
    public static void inserirRegistroCpuNuvem(CpuRegistro cpuRegistro){
        String sql = "INSERT INTO CpuRegistro (fkMaquina,dtRegistro,temperatura,usoPorcentagem) VALUES" +
                "(?,?,?,?)";
        PreparedStatement ps = null;


        try{
            ps = ConexaoNuvem.conn.prepareStatement(sql);
            ps.setString(1, cpuRegistro.getFkMaquina());
            ps.setString(2,cpuRegistro.getDtRegistro());
            ps.setString(3, cpuRegistro.getTemperatura());
            ps.setString(4, cpuRegistro.getUsoPorcentagem());
            ps.execute();
            System.out.println(String.format(
                    """
             |---------------------------------------------------------------------------------------------------------|
             | Inserindo informações de Cpu (Nuvem)                                                                    |
             | %s                                                                                                       \s
             |---------------------------------------------------------------------------------------------------------|
             """,cpuRegistro));
            ps.close();
            //System.out.println("Executando a instrução sql \n" + sql);
        }catch (Exception e){
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }

}
