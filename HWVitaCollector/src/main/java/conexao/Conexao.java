package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static  final String url = "jdbc:mysql://localhost:3306/vita";
    private static  final String user = "root";
    private static  final String password = "Odranoel@6969";
//    private static  final String url = "jdbc:mysql://database-1.czxa9gxppmzn.us-east-1.rds.amazonaws.com:3306/vita";
//    private static  final String user = "root";
//    private static  final String password = "grupovitadb";


    private static Connection conn;

    public static Connection getConexao(){

        try{
            if(conn == null){
                conn = DriverManager.getConnection(url,user,password);
                return conn;
            }else{
                return conn;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }

    }
}
