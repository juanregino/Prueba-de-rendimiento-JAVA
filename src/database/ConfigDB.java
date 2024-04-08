package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Variable que va a obtener el estado de la conexion

    static Connection objConnection  = null ;

    public static Connection openConnection (){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/demodaoutlet";
            String user = "root";
            String password = "";

            objConnection =(Connection) DriverManager.getConnection(url,user,password);
            System.out.println("ME CONECTE CORRECTAMENTE");
        }catch (ClassNotFoundException e){
            System.out.println("DRIVER NO INSTALADO");
        }catch (SQLException e){
            System.out.println("NO SE PUDO ESTABLECER UNA CONEXION");
        }

        return objConnection;
    }

    public static void closeConnection (){
        try{
            if (objConnection != null) objConnection.close();
        }catch (SQLException e ){
            System.out.println("ERROR" + e.getMessage());
        }
    }
}
