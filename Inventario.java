import java.io.*;
import java.util.*;

public class Inventario {
    private static final List<Producto> productos = new ArrayList<>();

    public static void cargarProductosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                String categoria = datos[2].trim();
                int precio = Integer.parseInt(datos[3].trim());
                int cantidad = Integer.parseInt(datos[4].trim());
                productos.add(new Producto(id, nombre, categoria, precio, cantidad));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
    }

    public static void guardarProductosEnArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto p : productos) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }

    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public static void actualizarProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.id == producto.id) {
                p.nombre = producto.nombre;
                p.categoria = producto.categoria;
                p.precio = producto.precio;
                p.cantidad = producto.cantidad;
                return;
            }
        }
    }

    public static boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.id == id);
    }

    public static List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> productosEnCategoria = new ArrayList<>();
        for (Producto p : productos) {
            if (p.categoria.equalsIgnoreCase(categoria)) {
                productosEnCategoria.add(p);
            }
        }
        return productosEnCategoria;
    }

    public static void generarReporte() {
        System.out.println("Reporte de productos:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public static void cantidadPorCategoria() {
        Map<String, Integer> cantidadPorCategoria = new HashMap<>();
        for (Producto p : productos) {
            cantidadPorCategoria.put(p.categoria, cantidadPorCategoria.getOrDefault(p.categoria, 0) + p.cantidad);
        }
        System.out.println("Cantidad de productos por categoria:");
        for (Map.Entry<String, Integer> entry : cantidadPorCategoria.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Producto productoMasCostoso() {
        if (productos.isEmpty()) {
            return null;
        }
        return Collections.max(productos, Comparator.comparingInt(p -> p.precio));
    }
}
