package services;

import database.ConfigDB;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompraServices {
    public Compra buscarPorId(int id){
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Compra objCompra = new Compra();
        Cliente objCliente = new Cliente();
        Producto objProducto = new Producto();

        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM compra \n" +
                    "INNER JOIN cliente ON compra.id_cliente = cliente.id \n" +
                    "INNER JOIN producto ON compra.id_producto = producto.id  \n" +
                    "inner join tienda on producto.id_tienda = tienda.id\n" +
                    "WHERE compra.id =  ?;";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setInt(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {

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
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return objCompra;
    }
}
