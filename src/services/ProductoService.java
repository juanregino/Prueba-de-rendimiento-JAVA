package services;

import database.ConfigDB;
import entity.Producto;
import entity.Tienda;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

public  List<Tienda> listarTiendas (){
    List<Tienda> listTemp = new ArrayList<>();

    //1. Abrimos la conexion
    Connection objconnection = ConfigDB.openConnection();

    //try-catch ya que algo puede fallar
    try {
        // Creo la sentencia SQL
        String sql = "SELECT * FROM tienda; ";
        // Preparo el statement
        PreparedStatement objPrepared = objconnection.prepareStatement(sql);

        // Ejecutamos el query
        ResultSet objResult = objPrepared.executeQuery();
        while (objResult.next()) {
            //Instancio un product en null para o vacío para llenarlo con la respuesta
            Tienda objTienda = new Tienda();
            objTienda.setId(objResult.getInt("id"));
            objTienda.setNombre(objResult.getString("nombre"));
            objTienda.setUbicacion(objResult.getString("ubicacion"));


            listTemp.add(objTienda);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    // Cerramos la conexion
    ConfigDB.closeConnection();
    return listTemp;

}
    public Producto buscarPorId(int id){
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Producto objProducto = new Producto();
        Tienda objTienda = new Tienda();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM producto INNER JOIN tienda ON producto.id_tienda = tienda.id WHERE producto.id =  ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setInt(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setIdTienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                // Lleno los datos de la tienda
                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setTienda(objTienda);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return objProducto;
    }
    public void actualizarStock (Producto producto){
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "UPDATE producto SET stock = ? WHERE id = ? ;";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Asignar los '?'

            objPrepared.setInt(1, producto.getStock());
            objPrepared.setInt(2, producto.getId());


            //4. Ejecutamos el query o 'consulta'
            objPrepared.execute();

            JOptionPane.showMessageDialog(null, "Se actualizo el stock");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

}
