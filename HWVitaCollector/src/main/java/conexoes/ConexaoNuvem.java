package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoNuvem {
    private static  final String urlNuvem = "jdbc:mysql://database-1.czxa9gxppmzn.us-east-1.rds.amazonaws.com:3306/vita";
    private static  final String userNuvem = "root";
    private static  final String passwordNuvem = "grupovitadb";

    private static Connection conn;
    public static Connection getConexaoNuvem(){
        try{
            if(conn == null){
                conn = DriverManager.getConnection(urlNuvem,userNuvem,passwordNuvem);
            }
            return conn;
        }catch (SQLException e) {
            System.out.println("Conexão com banco em nuvem não foi iniciada.");
            return null;
        }
    }
}
