public class Producto {
    int id;
    String nombre;
    String categoria;
    int precio;
    int cantidad;

    public Producto(int id, String nombre, String categoria, int precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return id + ", " + nombre + ", " + categoria + ", " + precio + ", " + cantidad;
    }
}
