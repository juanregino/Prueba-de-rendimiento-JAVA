package services;

import database.ConfigDB;
import entity.Cliente;
import entity.Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteServices {

    public Cliente buscarPorId(int id) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Cliente objCliente = new Cliente();

        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM cliente  WHERE id =  ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setInt(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                objCliente.setId(objResult.getInt("id"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return objCliente;
    }
}
