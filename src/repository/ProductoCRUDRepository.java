package repository;

import entity.Producto;

import java.util.List;

public interface ProductoCRUDRepository {
    void agregarProducto (Producto producto);
    List<Producto> buscarPorTienda (int id);

    List<Producto> listarProductos ();

    void actualizarProducto (Producto producto);

    void borrarProducto (int id);
}
