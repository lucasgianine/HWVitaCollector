package DAO;

import conexao.Conexao;
import entidades.CpuRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CpuDAO {


    public static void inserirRegistroCpu(CpuRegistro cpuRegistro){
        String sql = "INSERT INTO CpuRegistro (fkMaquina,dtRegistro,temperatura,usoPorcentagem) VALUES" +
                "(?,?,?,?)";
        PreparedStatement ps = null;


        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpuRegistro.getFkMaquina());
            ps.setString(2,cpuRegistro.getDtRegistro());
            ps.setString(3, cpuRegistro.getTemperatura());
            ps.setString(4, cpuRegistro.getUsoPorcentaegem());
            ps.execute();
            System.out.println("Inserindo informações de CPU");
            ps.close();
            //System.out.println("Executando a instrução sql \n" + sql);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
