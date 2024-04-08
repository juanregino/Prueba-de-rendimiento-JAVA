package repository;

import entity.Cliente;

import java.util.List;

public interface ClienteCRUDRepository {
    void agregarCliente (Cliente cliente);
    Cliente buscarPorNombre (String nombre);

    List<Cliente> listarClientes ();

    void actualizarCliente (Cliente cliente);

    void borrarCliente ( int id);
}
