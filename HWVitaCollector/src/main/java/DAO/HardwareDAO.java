package DAO;

import conexao.Conexao;
import entidades.Hardware;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HardwareDAO {

    public void inserirDadosHardware(Hardware hardware){
        String sql = "INSERT INTO HARDWARE (FkMaquina,UsoProcessador,TempProcessador,FreqProcessador,UsoMemoria,TempMemoria,FreqMemoria,ArmazenamentoTotal,ArmazenamentoLivre) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1,hardware.getFkMaquina());
        ps.setString(2,hardware.getUsoProcessador());
        ps.setString(3,hardware.getTempProcessador());
        ps.setString(4,hardware.getFreqProcessador());
        ps.setString(5,hardware.getUsoMemoria());
        ps.setString(6,hardware.getTempMemoria());
        ps.setString(7,hardware.getFreqMemoria());
        ps.setString(8,hardware.getArmazenamentoTotal());
        ps.setString(9,hardware.getArmazenamentoLivre());

        ps.execute();
            System.out.println("Executando Instrução sql " + sql);
        ps.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
