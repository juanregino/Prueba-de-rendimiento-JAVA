package entity;

import java.sql.Date;

public class Compra {
    private int  id ;
    private int  idCliente ;
    private int  idProducto ;
    private Date fechaCompra;
    private int cantidad ;

    private Cliente cliente;
    private Producto producto;

    public Compra() {
    }

    public Compra(int id, int idCliente, int idProducto, Date fechaCompra, int cantidad, Cliente cliente, Producto producto) {

        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String  toString() {
        return "Compra ->>" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idProducto=" + idProducto +
                ", fechaCompra=" + fechaCompra +
                ", cantidad=" + cantidad +
                ", cliente=" + cliente +
                ", producto=" + producto +
                " -<<";
    }
}
