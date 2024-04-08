package controller;

import entity.Cliente;
import entity.Producto;
import model.ClienteModel;
import services.ClienteServices;

import javax.swing.*;
import java.util.List;

public class ClienteController {
    ClienteModel objClientMod = new ClienteModel();
    ClienteServices objClientServices = new ClienteServices();

    public  void agregarCliente (){
        Cliente objCliente = new Cliente();
        //Listo las tiendas para que elija cual tienda le va a agregar un producto



        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre cliente ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido cliente ");
        String email = JOptionPane.showInputDialog(null, "Ingrese el email cliente ");



        objCliente.setNombre(nombre);
        objCliente.setEmail(email);
        objCliente.setApellido(apellido);


        objClientMod.agregarCliente(objCliente);
    }
    public void listarClientes() {
        List<Cliente> listTemp = objClientMod.listarClientes();
        String productosStr = "";
        for (Cliente clientTemp : listTemp) {
            productosStr += clientTemp.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, productosStr);
    }
    public String listarInStr(){
        List<Cliente> listTemp = objClientMod.listarClientes();
        String list = "🤷‍♂️ Listar Clientes  \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Cliente  clienteTemp: listTemp) {


            //Concatenamos la información
            list += clienteTemp.toString() + "\n";
        }
        return list;
    }

    public void buscarClientePorNombre() {

        String nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del cliente que deseas buscar ");

        Cliente objCliente = objClientMod.buscarPorNombre(nombre);
        JOptionPane.showMessageDialog(null,objCliente.toString());
    }

    public  void actualizarCliente (){
        int idAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id que va a actualizar " + "\n" + this.listarInStr()));

        Cliente objClienteAct = objClientServices.buscarPorId(idAct);

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre cliente ", objClienteAct.getNombre());
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido cliente ", objClienteAct.getApellido());
        String email = JOptionPane.showInputDialog(null, "Ingrese el email cliente ", objClienteAct.getEmail());



        objClienteAct.setNombre(nombre);
        objClienteAct.setEmail(email);
        objClienteAct.setApellido(apellido);

        objClientMod.actualizarCliente(objClienteAct);
    }

    public void borrarCliente (){
        int idDel = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id que va a Eliminar " + "\n" + this.listarInStr()));
    objClientMod.borrarCliente(idDel);
    }

}
