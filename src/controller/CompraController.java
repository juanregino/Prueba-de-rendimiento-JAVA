package controller;

import entity.Cliente;
import entity.Compra;
import entity.Producto;
import model.CompraModel;
import services.ClienteServices;
import services.CompraServices;
import services.ProductoService;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class CompraController {
    CompraModel objCompraMod = new CompraModel();
    ProductoService objProductService = new ProductoService();
    ClienteServices objClienteService = new ClienteServices();
    CompraServices objCompraService = new CompraServices();

    public void agregarCompra(int idCliente, int idProducto) {
        Compra objCompra = new Compra();


        objCompra.setIdCliente(idCliente);
        objCompra.setIdProducto(idProducto);

        Producto objProduct = objProductService.buscarPorId(idProducto);
        Cliente objCliente = objClienteService.buscarPorId(idCliente);
        //Metodo para obtener la fecha en la cual se genera la compra
        long ms = System.currentTimeMillis();
        Date date = new Date(ms);
        System.out.println(date);

        objCompra.setFechaCompra(date);

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad"));

        objCompra.setCantidad(cantidad);

        if (objProduct.getStock() >= cantidad) {

            objCompraMod.agregarCompra(objCompra);

            int stockActualizado = objProduct.getStock() - cantidad;
            objProduct.setStock(stockActualizado);
            objProductService.actualizarStock(objProduct);

            //VAMOS A GUARDAR LA INFORMACION PARA IMPRIMIR LA FACTURA
            String nombreProducto = objProduct.getNombre();
            String precioProducto = String.valueOf(objProduct.getPrecio());
            String nombreTienda = objProduct.getTienda().getNombre();
            String ubicacionTienda = objProduct.getTienda().getUbicacion();
            String nombreCLiente = objCliente.getNombre();
            String apellidoCliente = objCliente.getApellido();
            String emailCliente = objCliente.getEmail();
            //Calculo el subtotal y el total
            double subtotal = objProduct.getPrecio() * cantidad;
            double total = subtotal + (subtotal * 0.19);
            //Los convierto a String para poderlos imprimir
            String totalStr = String.valueOf(total);
            String subtotalStr = String.valueOf(subtotal);
            //IMPRIMAMOS LA FACTURA
            JOptionPane.showMessageDialog(null, "FACTURA" + "\n" +
                    "Nombre del producto :" + nombreProducto + "\n" +
                    "Precio del producto :" + precioProducto + "\n" +
                    "Nombre de la tienda :" + nombreTienda + "\n" +
                    "Ubicacion de la tienda : " + ubicacionTienda + "\n" +
                    "Nombre del cliente : " + nombreCLiente + "\n" +
                    "Apellido del cliente :" + apellidoCliente + "\n" +
                    "Email del Cliente : " + emailCliente + "\n" +
                    "-------------------------------------------" + "\n" +
                    "Subtotal :" + subtotalStr + "\n" +
                    "Iva : 19 %" + "\n" +
                    "Total :" + totalStr + "\n"
            );
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad supera el stock");
        }


    }

    public void listarProductos() {
        List<Compra> listTemp = objCompraMod.listarCompras();
        String compraStr = "";
        for (Compra compraTemp : listTemp) {
            compraStr += compraTemp.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, compraStr);
    }

    public String listarInStr() {
        List<Compra> listTemp = objCompraMod.listarCompras();

        String list = "🤷‍♂️ List Products \n";
        //Iteramos sobre la lista que devuelve el método find All
        for (Compra compraTemp : listTemp) {
            //Concatenamos la información
            list += compraTemp.toString() + "\n";
        }
        return list;
    }

    public void buscarComprasDeProducto(int idProducto) {
        List<Compra> listComprasXProduct = objCompraMod.buscarComprasDeProduct(idProducto);
        String compraStr = "";
        String nombreProducto = "";
        int contador = 0;
        for (Compra compraTemp : listComprasXProduct) {
            compraStr += compraTemp.toString() + "\n";
            contador++;
            nombreProducto = compraTemp.getProducto().getNombre();
        }
        String contadorStr = String.valueOf(contador);
        JOptionPane.showMessageDialog(null, "Lista de Compras del producto :  " + nombreProducto + "\n" + compraStr +
                "------------------------------" + "\n" +
                "Total De Compras de este Producto : " + contadorStr

        );
    }

    public void actualizarCompra() {
        //1 . Listo las comprar para que el usuario elija el id
        int idAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id que va a actualizar " + "\n" + this.listarInStr()));

        //Instancio una compra donde voy a guardar la compra que me retorna el servicio buscar por id
        Compra objCompraAct = objCompraService.buscarPorId(idAct);
        //Guardo la cantidad en String para pasarle en el modal para que la actualice
        String cantidadStr = String.valueOf(objCompraAct.getCantidad());
        //Guardo la respuesta del usuario de la cantidad actualizada
        int cantidadActulizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Actualiza la cantidad del producto" + objCompraAct.getProducto().getNombre() + "\n" + "Que va a comprar : " + objCompraAct.getCliente().getNombre(), cantidadStr));
        // Creo una variable en la cual voy a almacenar la cantidad que me va a servir para  calcular la actualizacion del stock
        int cantidadParaStock = 0;
        // Creo una variable para guardar la cantidad de stock actualizada que voy a enviar al Servicio
        int stockActualizado = 0;

        //Si la cantidad a actualizar es menor a la cantidad que trae la compra
        //Entonces le resto a la cantidad que ya traía, la cantidad que el usuario ingreso
        //y luego al stock que trae el producto hasta ese momento le sumo los que libero el usuario
        if (cantidadActulizar < objCompraAct.getCantidad()) {
            cantidadParaStock = objCompraAct.getCantidad() - cantidadActulizar;
            stockActualizado = objCompraAct.getProducto().getStock() + cantidadParaStock;
        }
        //Si la cantidad a actualizar es mayor a la cantidad que trae la compra
        //Entonces le sumo a la cantidad que ya traía, la cantidad que el usuario ingreso
        //y luego al stock que trae el producto hasta ese momento le resto los que tomó el usuario
        else if (cantidadActulizar > objCompraAct.getCantidad()) {
            cantidadParaStock = objCompraAct.getCantidad() + cantidadActulizar;
            stockActualizado = objCompraAct.getProducto().getStock() - cantidadParaStock;
        }

        //Instancia un producto donde voy a almacenar el producto que me trajo la compra
        //Este mismo producto es al que le voy a actualizar el stock

        Producto objProducto = objCompraAct.getProducto();
        objProducto.setStock(stockActualizado);

        //Este es el servicio que me actualiza el stock
        objProductService.actualizarStock(objProducto);
        //Seteo la cantidad que actualizo el usuario
        objCompraAct.setCantidad(cantidadActulizar);

        objCompraMod.actualizarCompra(objCompraAct);

    }


}
