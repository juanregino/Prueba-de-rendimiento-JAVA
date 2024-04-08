import controller.ClienteController;
import controller.CompraController;
import controller.ProductController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ProductController objProductCont = new ProductController();
        ClienteController objClienteCont = new ClienteController();
        CompraController objCompraCont = new CompraController();
        int opcionPrinc = -1;
        int opcionProduct = -1;
        int opcionCliente= -1;
        int opcionCompra= -1;
        do {

            opcionPrinc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                    BIENVENIDO A DE MODA
                                        
                    1. Gestionar Productos
                    2. Gestionar Clientes
                    3. Gestionar Compras
                    4. Salir
                    """));

            switch (opcionPrinc) {
                case 1:
                    do {

                        opcionProduct = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                GESTION DE PRODUCTOS
                                                    
                                1. Agregar un  Producto
                                2. Listar Los Productos
                                3. Buscar Productos por tienda
                                4. Actualizar 
                                5. Borrar Producto
                                6. Salir
                                """));
                        switch (opcionProduct) {
                            case 1:
                                objProductCont.agregarProducto();
                                break;
                            case 2 :
                                objProductCont.listarProductos();
                                break;
                            case 3 :
                                objProductCont.buscarProductosPorTienda();
                                break ;
                            case 4 :
                                objProductCont.actualizarProducto();
                                break ;
                            case 5 :
                                objProductCont.borrarProducto();
                                break ;

                        }
                    } while (opcionProduct != 6);
                    break;
                case 2 :
                    do {

                        opcionCliente = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                GESTION DE CLIENTES
                                                    
                                1. Agregar un Cliente
                                2. Listar Los Clientes
                                3. Buscar Cliente por nombre
                                4. Actualizar Cliente
                                5. Borrar Cliente
                                6. Salir
                                """));
                        switch (opcionCliente) {
                            case 1:
                                objClienteCont.agregarCliente();
                                break;
                            case 2 :
                                objClienteCont.listarClientes();
                                break;
                            case 3 :
                                objClienteCont.buscarClientePorNombre();
                                break ;
                            case 4 :
                                objClienteCont.actualizarCliente();
                                break ;
                            case 5 :
                              objClienteCont.borrarCliente();
                                break ;

                        }
                    } while (opcionCliente != 6);
                    break;
                case 3 :
                    do {

                        opcionCompra = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                GESTION DE COMPRAS
                                                    
                                1. Agregar un Compra
                                2. Listar Las Compras
                                3. Buscar Compras De un Producto
                                4. Actualizar Compra
                                5. Borrar Compra
                                6. Salir
                                """));
                        switch (opcionCompra) {
                            case 1:
                                int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el id del cliente que esta comprando " + "\n" + objClienteCont.listarInStr()));
                                int idProduct = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el id del producto que esta comprando " + "\n" + objProductCont.listarInStr()));
                                objCompraCont.agregarCompra(idCliente,idProduct);
                                break;
                            case 2 :
                                objCompraCont.listarProductos();
                                break;
                            case 3 :
                                int idBuscProduct = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el id del producto que quiere revisar sus compras " + "\n" + objProductCont.listarInStr()));
                                objCompraCont.buscarComprasDeProducto(idBuscProduct);
                                break ;
                            case 4 :
                                objCompraCont.actualizarCompra();
                                break ;
                            case 5 :
                                objClienteCont.borrarCliente();
                                break ;

                        }
                    } while (opcionCompra != 6);
                    break;

            }
        } while (opcionPrinc != 4);
    }
}