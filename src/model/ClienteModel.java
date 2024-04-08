package model;

import database.ConfigDB;
import entity.Cliente;
import entity.Cliente;
import entity.Cliente;
import repository.ClienteCRUDRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements ClienteCRUDRepository {
    @Override
    public void agregarCliente(Cliente cliente) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO cliente (nombre,apellido,email) VALUES(?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar los '?'

            objPrepared.setString(1, cliente.getNombre());
            objPrepared.setString(2, cliente.getApellido());
            objPrepared.setString(3, cliente.getEmail());



            //4. Ejecutamos el query o 'consulta'
            objPrepared.execute();

            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public Cliente buscarPorNombre(String nombre) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Cliente objCliente = new Cliente();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM cliente WHERE nombre =  ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setString(1, nombre);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                objCliente.setId(objResult.getInt("id"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return objCliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        List<Cliente> listClientes = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            // Creo la sentencia SQL
            String sql = "SELECT * FROM cliente ; ";
            // Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);

            // Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                //Instancio un product en null para o vacío para llenarlo con la respuesta
                Cliente objCliente = new Cliente();
                objCliente.setId(objResult.getInt("id"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

                listClientes.add(objCliente);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // Cerramos la conexion
        ConfigDB.closeConnection();
        return listClientes;
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE cliente SET nombre = ? , apellido = ? , email = ? WHERE id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //Asignar los '?'
            objPrepared.setString(1, cliente.getNombre());
            objPrepared.setString(2, cliente.getApellido());
            objPrepared.setString(3, cliente.getEmail());
            objPrepared.setInt(4, cliente.getId());


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
    public void borrarCliente(int id) {
//1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
            //2. Escribir la sentencia sql
            String sql = "DELETE FROM cliente WHERE id = ? ;";
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
