package model;

import database.ConfigDB;
import entity.Producto;
import entity.Tienda;
import repository.ProductoCRUDRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements ProductoCRUDRepository {


    @Override
    public void agregarProducto(Producto producto) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO producto (nombre,precio,id_tienda,stock) VALUES(?,?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar los '?'

            objPrepared.setString(1, producto.getNombre());
            objPrepared.setDouble(2, producto.getPrecio());
            objPrepared.setInt(3, producto.getIdTienda());
            objPrepared.setInt(4, producto.getStock());


            //4. Ejecutamos el query o 'consulta'
            ResultSet objResult = objPrepared.getGeneratedKeys();
            objPrepared.execute();





            JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public List<Producto> buscarPorTienda(int idTienda) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        List<Producto> listProduct = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM producto INNER JOIN tienda ON producto.id_tienda = tienda.id WHERE producto.id_tienda =  ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setInt(1, idTienda);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();
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

                listProduct.add(objProducto);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return listProduct;


    }


    @Override
    public List<Producto> listarProductos() {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        List<Producto> listProductos = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            // Creo la sentencia SQL
            String sql = "SELECT * FROM producto INNER JOIN tienda ON producto.id_tienda = tienda.id ; ";
            // Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);

            // Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                //Instancio un product en null para o vacío para llenarlo con la respuesta
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();
                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setIdTienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("stock"));

                // Lleno los datos de la tienda
                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setTienda(objTienda);

                listProductos.add(objProducto);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return listProductos;

    }

    @Override
    public void actualizarProducto(Producto producto) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE producto SET nombre = ? , precio = ? , id_tienda = ? ,stock = ? WHERE id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //Asignar los '?'
            objPrepared.setString(1, producto.getNombre());
            objPrepared.setDouble(2, producto.getPrecio());
            objPrepared.setInt(3, producto.getIdTienda());
            objPrepared.setInt(4, producto.getStock());
            objPrepared.setInt(5, producto.getId());


            //5. Ejecutamos el query
            int rowAffect = objPrepared.executeUpdate();
            if (rowAffect > 0) {
                JOptionPane.showMessageDialog(null,"Se actualizo correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //6. Cerramos la conexion
        ConfigDB.closeConnection();
    }


    @Override
    public void borrarProducto(int id) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
            //2. Escribir la sentencia sql
            String sql = "DELETE FROM producto WHERE id = ? ;";
            //3. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Asignamos el valor al ?
            objPrepared.setInt(1, id);

            //5. Ejecutamos el query
            objPrepared.execute();

            JOptionPane.showMessageDialog(null,
                    "Borrado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        //6. Cerramos la conexión
        ConfigDB.closeConnection();
    }
}
