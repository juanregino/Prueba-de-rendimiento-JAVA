package repository;

import entity.Compra;

import java.util.List;

public interface CompraCRUDRepository {
    void agregarCompra(Compra compra);

    List<Compra> buscarComprasDeProduct(int id);

    List<Compra> listarCompras();

    void actualizarCompra(Compra compra);

    void borrarCompra(String id);
}
