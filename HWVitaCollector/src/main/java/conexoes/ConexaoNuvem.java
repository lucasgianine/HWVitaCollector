package conexoes;

import com.mysql.cj.log.Log;
import helpers.Helper;
import helpers.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoNuvem {
    //private static  final String urlNuvem = "jdbc:mysql://database-1.czxa9gxppmzn.us-east-1.rds.amazonaws.com:3306/vita";
    //private static  final String userNuvem = "root";
    //private static  final String passwordNuvem = "grupovitadb";
   private static String urlNuvem= "jdbc:sqlserver://44.205.185.26:1433;databaseName=vita;user=sa;password=Odranoel@6969;encrypt=true;trustServerCertificate=true";

    public static Connection conn;
    public static void getConexaoNuvem(){
        try{
            if(conn == null){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(urlNuvem);
            }
        }catch (SQLException e) {
            System.out.println("Conexão com banco em nuvem não foi iniciada.");
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,"Erro na conexão com o banco em nuvem na classe ConexãoNuvem Código de erro: " + e.getSQLState() + stackTrace);

        } catch (ClassNotFoundException e) {
            String stackTrace = Helper.getStackTraceAsString(e);
            System.out.println("class.forName Exception");
            Logging.AddLogInfo(Logging.fileHandler,e.getMessage() + stackTrace);
        }
    }
}
