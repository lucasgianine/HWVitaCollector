package DAO;

import conexoes.Conexao;
import conexoes.ConexaoNuvem;
import entidades.CpuRegistro;
import helpers.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CpuDAO {


    public static void inserirRegistroCpu(CpuRegistro cpuRegistro){

        try {
            inserirRegistroCpuLocal(cpuRegistro);
        }catch (Exception e){
            Logging.AddLogInfo(Logging.fileHandler,"Falha ao inserir registro de Cpu Local " + e.getMessage());
            System.out.println("Não foi possivel conectar no banco Local");
        }
        try {
            inserirRegistroCpuNuvem(cpuRegistro);
        }catch (Exception e){
            System.out.println("Não foi possivel conectar no banco em Nuvem");
        }

    }
    public static void inserirRegistroCpuLocal(CpuRegistro cpuRegistro){
        String sql = "INSERT INTO CpuRegistro (fkMaquina,dtRegistro,temperatura,usoPorcentagem) VALUES" +
                "(?,?,?,?)";
        PreparedStatement ps = null;


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
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
           Logging.AddLogInfo(Logging.fileHandler,e.getMessage());
        }
    }
    public static void inserirRegistroCpuNuvem(CpuRegistro cpuRegistro){
        String sql = "INSERT INTO CpuRegistro (fkMaquina,dtRegistro,temperatura,usoPorcentagem) VALUES" +
                "(?,?,?,?)";
        PreparedStatement ps = null;


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = ConexaoNuvem.getConexaoNuvem().prepareStatement(sql);
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
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage());
        }
    }

}
