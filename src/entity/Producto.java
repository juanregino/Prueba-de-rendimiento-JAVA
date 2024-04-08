package entity;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int idTienda;
    private int stock;

    private  Tienda tienda ;


    public Producto() {
    }

    public Producto(int id, String nombre, double precio, int idTienda, int stock, Tienda tienda) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idTienda = idTienda;
        this.stock = stock;
        this.tienda = tienda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    @Override
    public String toString() {
        return "Producto ->>" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", idTienda=" + idTienda +
                ", stock=" + stock +
                ", tienda=" + tienda +
                "-<< ";
    }
}
