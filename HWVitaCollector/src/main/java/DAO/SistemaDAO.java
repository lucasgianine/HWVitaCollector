package DAO;

import conexao.Conexao;
import entidades.MemoriaRegistro;
import entidades.SistemaRegistro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SistemaDAO {
    public static void inserirRegistroSistema(SistemaRegistro sistemaRegistro){
        String sql = "INSERT INTO sistemaRegistro (fkMaquina,dtRegistro,tempoDeAtividadeSistema,qtdDispositivosUsb) VALUES" +
                "(?,?,?,?) ";

        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,sistemaRegistro.getFkMaquina());
            ps.setString(2,sistemaRegistro.getDtRegistro());
            ps.setString(3,sistemaRegistro.getTempoDeAtividadeSO());
            ps.setInt(4,sistemaRegistro.getQtdDisposivosUsbConectados());
            ps.execute();
            ps.close();
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}

