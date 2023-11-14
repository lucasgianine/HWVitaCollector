package DAO;

import conexoes.Conexao;
import entidades.ParametrosAlerta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class ParametrosAlertaDAO {
    public static ParametrosAlerta getParametros(int fkHospital){
        String sql = "SELECT * FROM ParametrosAlerta WHERE fkHospital = ?";
        PreparedStatement ps;
        try{
            if(Conexao.conn == null) throw new RuntimeException("Sem conexão, ParametrosAlerta inacessíveis");

            ps = Objects.requireNonNull(Conexao.conn).prepareStatement(sql);
            ps.setInt(1,fkHospital);
            ps.execute();
            ResultSet result = ps.getResultSet();
            ParametrosAlerta p = null;
            while (result.next()){
                p = new ParametrosAlerta();
                p.setFkHospital(result.getInt(2));
                p.setMaxTempProcessador(result.getString(3));
                p.setMaxUsoProcessador(result.getString(4));
                p.setMaxUsoMemoria(result.getString(5));
                p.setMinLivreDisco(result.getString(6));
                p.setMaxTempoDeAtividade(result.getString(7));
                p.setMinQtdUsb(result.getString(8));
                p.setProcessoMaxUsoRam(result.getString(9));
                p.setTempoParaAlertaUsoProcessador(result.getString(10));
                p.setTempoParaAlertaUsoMemoria(result.getString(11));
                p.setTempoParaAlertaTempProcessador(result.getString(12));
                p.setTempoParaAlertaUsoRamProcessos(result.getString(13));
            }
               return p;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("retornando null");
        return null;
    }
}
