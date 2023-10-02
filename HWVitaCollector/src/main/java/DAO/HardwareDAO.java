package DAO;

import conexao.Conexao;
import entidades.Hardware;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HardwareDAO {

    public void inserirDadosHardware(Hardware hardware){
        String sql = "INSERT INTO HARDWARE (FkMaquina,UsoProcessador,TempProcessador,FreqProcessador,UsoMemoria,TempMemoria,FreqMemoria,ArmazenamentoLivre,ArmazenamentoTotal) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1,hardware.getFkMaquina());
        ps.setDouble(2,hardware.getUsoProcessador());
        ps.setDouble(3,hardware.getTempProcessador());
        ps.setDouble(4,hardware.getFreqProcessador());
        ps.setDouble(5,hardware.getUsoMemoria());
        ps.setDouble(6,hardware.getTempMemoria());
        ps.setDouble(7,hardware.getFreqMemoria());
        ps.setDouble(8,hardware.getArmazenamentoTotal());
        ps.setDouble(9,hardware.getArmazenamentoLivre());

        ps.execute();
            System.out.println("Executando Instrução sql " + sql);
        ps.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
