package DAO;

import conexao.Conexao;
import entidades.Hardware;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HardwareDAO {

    public void inserirDadosHardware(Hardware hardware){
        String sql = "INSERT INTO HARDWARE (FkMaquina,UsoProcessador,TempProcessador,UsoMemoria,ArmazenamentoTotal,ArmazenamentoLivre) VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1,hardware.getFkMaquina());
        ps.setString(2,hardware.getUsoProcessador());
        ps.setString(3,hardware.getTempProcessador());
        ps.setString(4,hardware.getUsoMemoria());
        ps.setString(5,hardware.getArmazenamentoTotal());
        ps.setString(6,hardware.getArmazenamentoLivre());
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
