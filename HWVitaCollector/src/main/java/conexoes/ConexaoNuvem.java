package conexoes;

import com.mysql.cj.log.Log;
import helpers.Helper;
import helpers.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoNuvem {
    private static  final String urlNuvem = "jdbc:mysql://database-1.czxa9gxppmzn.us-east-1.rds.amazonaws.com:3306/vita";
    private static  final String userNuvem = "root";
    private static  final String passwordNuvem = "grupovitadb";

    private static  final String url = "jdbc:mysql://localhost:3306/vita";
    private static  final String user = "root";
    private static  final String password = "grupo06vitadb";

    public static Connection conn;
    public static void getConexaoNuvem(){
        try{
            if(conn == null){
                //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //conn = DriverManager.getConnection(urlNuvem);
                conn = DriverManager.getConnection(url,user,password);
            }
        }catch (SQLException e) {
            System.out.println("Conexão com banco em nuvem não foi iniciada.");
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,"Erro na conexão com o banco em nuvem na classe ConexãoNuvem Código de erro: " + e.getSQLState() + stackTrace);

        }
    }
}
