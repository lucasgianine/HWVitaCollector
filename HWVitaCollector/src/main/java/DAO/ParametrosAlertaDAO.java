package DAO;

import com.mysql.cj.log.Log;
import conexoes.Conexao;
import entidades.ParametrosAlerta;
import helpers.Helper;
import helpers.Logging;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParametrosAlertaDAO {
    public static ParametrosAlerta getParametros(int fkHospital){
        String sql = "SELECT * FROM ParametrosAlerta WHERE fkHospital = ?";
        PreparedStatement ps;
        try{
            if(Conexao.conn == null){
                Logging.AddLogInfo(Logging.fileHandler,"Parametros de alerta inacessíveis");
            }

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
                p.setPorcentagemMaximaRamProcesso(result.getString(9));
                p.setTempoParaAlertaSec(result.getString(10));
            }
               return p;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("retornando null");
        return null;
    }

    public static List<Double> getAvgsByTime(String UUID, int segundosParaAlerta){
        List<Double> valueList = new ArrayList<>();
        String sql = "SELECT AVG(usoPorcentagem) as avgUsoProcessador, AVG(temperatura) as avgTempProcessador, AVG(usoMemoria) as avgUsoMemoria from CpuRegistro as c JOIN MemoriaRegistro ON c.fkMaquina = MemoriaRegistro.fkMaquina WHERE TIMESTAMPDIFF(second,c.dtRegistro,now()) < ? and c.fkMaquina = ?;";

        if(Conexao.conn == null){
            Logging.AddLogInfo(Logging.fileHandler,"Parametros de alerta inacessíveis");
        }
        PreparedStatement ps;
        try {
            ps = Objects.requireNonNull(Conexao.conn).prepareStatement(sql);
            ps.setInt(1,segundosParaAlerta);
            ps.setString(2,UUID);
            ps.execute();
            ResultSet result = ps.getResultSet();
            if(result.next()){
                valueList.add(result.getDouble(1));
                valueList.add(result.getDouble(2));
                valueList.add(result.getDouble(3));
            }
            return valueList;
        }catch (Exception e){
            String stacktrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,"Erro ao pegar médias das métricas " + stacktrace);
        }
        return null;
    }

}
