package controller;

import entity.Producto;
import entity.Tienda;
import model.ProductoModel;
import services.ProductoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    ProductoModel objProductMod = new ProductoModel();
    ProductoService objProductServices = new ProductoService();


    public String listarTiendas() {
        //Listo las tiendas para que elija cual tienda le va a agregar un producto
        List<Tienda> listTiendas = objProductServices.listarTiendas();
        String tiendasStr = "";
        for (Tienda tiendaTemp : listTiendas) {
            tiendasStr += tiendaTemp.toString() + "\n";
        }
        return tiendasStr;
    }

    public void agregarProducto() {
        Producto objProducto = new Producto();

        int idTienda = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el id de la tienda que vas a agregar un producto" + "\n" + this.listarTiendas()));
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto ");
        double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto "));

        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el stock del producto"));
        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setIdTienda(idTienda);
        objProducto.setStock(stock);

        objProductMod.agregarProducto(objProducto);
    }

    public void listarProductos() {
        List<Producto> listTemp = objProductMod.listarProductos();
        String productosStr = "";
        for (Producto productTemp : listTemp) {
            productosStr += productTemp.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, productosStr);
    }

    /**
     * <h3>Metodo para obteber la lista pero en String</h3>
     * <p>a diferencia de la anterior que la uso en la vista esta la utilizo en los demas metodos como un String</p>
     * @return la lista como String
     */
    public String listarInStr(){
        List<Producto> listTemp = objProductMod.listarProductos();
        String list = "🤷‍♂️ List Products \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Producto  productoTemp: listTemp) {


            //Concatenamos la información
            list += productoTemp.toString() + "\n";
        }
        return list;
    }

    public void buscarProductosPorTienda() {

        int idTienda = Integer.parseInt(JOptionPane.showInputDialog(null ,"Ingrese el id de la tienda para buscar sus productos" + "\n" + this.listarTiendas()));

        List<Producto> listTemp = objProductMod.buscarPorTienda(idTienda);
        String str = "";
        for(Producto productoTemp : listTemp ){
            str += productoTemp.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,str);
    }
    public void actualizarProducto (){
      int idAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id que va a actualizar " + "\n" + this.listarInStr()));

      Producto objProductoAct = objProductServices.buscarPorId(idAct);

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto ",objProductoAct.getNombre());
        double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto ",objProductoAct.getPrecio()));

        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el stock del producto",objProductoAct.getStock()));

        objProductoAct.setNombre(nombre);
        objProductoAct.setPrecio(precio);
        objProductoAct.setStock(stock);

        objProductMod.actualizarProducto(objProductoAct);

    }
    public  void borrarProducto(){
        int idDel = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id que va a eliminar " + "\n" + this.listarInStr()));

        objProductMod.borrarProducto(idDel);

    }
}
