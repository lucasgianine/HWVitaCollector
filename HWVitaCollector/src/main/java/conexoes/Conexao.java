package conexoes;

import helpers.Helper;
import helpers.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static  final String url = "jdbc:mysql://localhost:3306/vita";
    private static  final String user = "root";
    private static  final String password = "Odranoel@6969";
    //CONEXAO COM A MÁQUINA WINDOWS SERVER
    private static String urlNuvem= "jdbc:sqlserver://44.205.185.26:1433;databaseName=vita;user=sa;password=Odranoel@6969;encrypt=true;trustServerCertificate=true";


    public static Connection conn;

    public static Connection getConexao(){

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if(conn == null){
                //conn = DriverManager.getConnection(url,user,password);
                conn = DriverManager.getConnection(urlNuvem);
            }
            return conn;
        }catch (SQLException e) {
            String stackTrace = Helper.getStackTraceAsString(e);
            Logging.AddLogInfo(Logging.fileHandler,"Conexão local não foi estabelecida :" + stackTrace);
            System.out.println("Conexão com banco local não foi iniciada.");
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
