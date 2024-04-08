package model;

import database.ConfigDB;
import entity.*;
import entity.Compra;
import entity.Compra;
import repository.CompraCRUDRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CompraCRUDRepository {
    @Override
    public void agregarCompra(Compra compra) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO compra (id_cliente,id_producto,fecha_compra, cantidad) VALUES(?,?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar los '?'

            objPrepared.setInt(1, compra.getIdCliente());
            objPrepared.setInt(2, compra.getIdProducto());
            objPrepared.setDate(3, compra.getFechaCompra());
            objPrepared.setInt(4, compra.getCantidad());


            //4. Ejecutamos el query o 'consulta'
            objPrepared.execute();

            JOptionPane.showMessageDialog(null, "Compra agregada correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public List<Compra>  buscarComprasDeProduct(int id) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio una lista de compra en null para o vacío para llenarlo con la respuesta
        List<Compra> listComprasProducto = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM compra \n" +
                    "INNER JOIN cliente ON compra.id_cliente = cliente.id \n" +
                    "INNER JOIN producto ON compra.id_producto = producto.id  \n" +
                    "inner join tienda on producto.id_tienda = tienda.id\n" +
                    "WHERE producto.id =  ?;";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setInt(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Compra objCompra = new Compra();
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();
                objCompra.setId(objResult.getInt("id"));
                objCompra.setIdCliente(objResult.getInt("id_cliente"));
                objCompra.setIdProducto(objResult.getInt("id_producto"));
                objCompra.setCantidad(objResult.getInt("cantidad"));
                objCompra.setFechaCompra(objResult.getDate("fecha_compra"));
                //Lleno los datos del cliente
                objCliente.setId(objResult.getInt("cliente.id"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                //Lleno los datos del producto
                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setIdTienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                //inserto el cliente y el producto a la compra
                objCompra.setCliente(objCliente);
                objCompra.setProducto(objProducto);

                listComprasProducto.add(objCompra);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return listComprasProducto;
    }

    @Override
    public List<Compra> listarCompras() {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        List<Compra> listCompras = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            // Creo la sentencia SQL
            String sql ="SELECT * FROM compra \n" +
                    "INNER JOIN cliente ON compra.id_cliente = cliente.id \n" +
                    "INNER JOIN producto ON compra.id_producto = producto.id  \n" +
                    "inner join tienda on producto.id_tienda = tienda.id\n" +
                    ";";
            // Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);

            // Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {

                Compra objCompra = new Compra();
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();
                objCompra.setId(objResult.getInt("compra.id"));
                objCompra.setIdCliente(objResult.getInt("compra.id_cliente"));
                objCompra.setIdProducto(objResult.getInt("compra.id_producto"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objCompra.setFechaCompra(objResult.getDate("compra.fecha_compra"));

                //Lleno los datos del cliente
                objCliente.setId(objResult.getInt("cliente.id"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                //Lleno los datos del producto
                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setIdTienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                //inserto el cliente y el producto a la compra
                objCompra.setCliente(objCliente);
                objCompra.setProducto(objProducto);

                listCompras.add(objCompra);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return listCompras;
    }

    @Override
    public void actualizarCompra(Compra compra) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE compra SET id_cliente = ? , id_producto = ? , fecha_compra = ?, cantidad = ? WHERE id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //Asignar los '?'

            objPrepared.setInt(1, compra.getIdCliente());
            objPrepared.setInt(2, compra.getIdProducto());
            objPrepared.setDate(3, compra.getFechaCompra());
            objPrepared.setInt(4, compra.getCantidad());
            objPrepared.setInt(5, compra.getId());


            //5. Ejecutamos el query
            int rowAffect = objPrepared.executeUpdate();
            if (rowAffect > 0) {
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //6. Cerramos la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public void borrarCompra(String id) {
//1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
            //2. Escribir la sentencia sql
            String sql = "DELETE FROM compra WHERE id = ? ;";
            //3. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Asignamos el valor al ?
            objPrepared.setString(1, id);

            //5. Ejecutamos el query
            objPrepared.execute();

            JOptionPane.showMessageDialog(null,
                    "Borrado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //6. Cerramos la conexión
        ConfigDB.closeConnection();
    }
}
