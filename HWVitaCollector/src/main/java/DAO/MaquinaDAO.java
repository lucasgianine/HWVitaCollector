package DAO;

import conexao.Conexao;
import entidades.Funcionario;
import entidades.Maquina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MaquinaDAO {

    public static Maquina getMaquinaByUUID(String UUID){
        String sql = "SELECT * FROM MAQUINA WHERE uuid = ?;";
        PreparedStatement ps = null;
        try{
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setString(1,UUID);
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        Maquina maquina = null;
        while(resultSet.next()){
            maquina = new Maquina();
            maquina.setId(resultSet.getString(1));
            maquina.setFkHospital(resultSet.getInt(2));
            maquina.setApelido(resultSet.getString(3));
            maquina.setResponsavel(resultSet.getString(4));
        }
        ps.close();
        return maquina;
        }catch (SQLException e){
            e.fillInStackTrace();
            return null;
        }
    }

    public static void registrarMaquina(Maquina maquina){
        PreparedStatement ps = null;
        String sql = "INSERT INTO MAQUINA (UUID,FKHOSPITAL,APELIDO,RESPONSAVEL) VALUES (?,?,?,?)";
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,maquina.getUuid());
            ps.setInt(2,maquina.getFkHospital());
            ps.setString(3, maquina.getApelido());
            ps.setString(4, maquina.getResponsavel());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            e.fillInStackTrace();
        }
    }
}
