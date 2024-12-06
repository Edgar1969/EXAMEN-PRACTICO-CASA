import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Inventario.cargarProductosDesdeArchivo("productos/productos.txt");
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                
                switch (opcion) {
                    case 1 -> agregarProducto(scanner);
                    case 2 -> actualizarProducto(scanner);
                    case 3 -> {
                        System.out.print("Ingrese ID del producto a eliminar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        if (Inventario.eliminarProducto(id)) {
                            System.out.println("Producto eliminado con éxito.");
                        } else {
                            System.out.println("No se encontró un producto con el ID '" + id + "'.");
                        }
                        Inventario.guardarProductosEnArchivo("productos/productos.txt"); // Guardar cambios en el archivo
                    }
                    case 4 -> buscarPorCategoria(scanner); // Implementa este método si es necesario
                    case 5 -> Inventario.generarReporte();
                    case 6 -> Inventario.cantidadPorCategoria();
                    case 7 -> productoMasCostoso();
                    case 8 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no valida. Intente de nuevo.");
                }
            } while (opcion != 8);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("Menu:");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar por categoria");
        System.out.println("5. Generar reporte");
        System.out.println("6. Cantidad de productos por categoria");
        System.out.println("7. Producto mas costoso");
        System.out.println("8. Salir");
        System.out.println();
        System.out.print("Seleccione una opcion: ");
    }

    private static void agregarProducto(Scanner scanner) {
        System.out.print("Ingrese ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese precio: ");
        int precio = scanner.nextInt(); // Cambiado a double
        System.out.print("Ingrese cantidad disponible: ");
        int cantidad = scanner.nextInt();
        Inventario.agregarProducto(new Producto(id, nombre, categoria, precio, cantidad));
        Inventario.guardarProductosEnArchivo("productos/productos.txt"); // Guardar cambios en el archivo
        System.out.println("Producto agregado.");
    }

    private static void actualizarProducto(Scanner scanner) {
        System.out.print("Ingrese ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nueva categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Ingrese nuevo precio: ");
        int precio = (int) scanner.nextDouble(); // Cambiado a double
        System.out.print("Ingrese nueva cantidad disponible: ");
        int cantidad = scanner.nextInt();
        Inventario.actualizarProducto(new Producto(id, nombre, categoria, precio, cantidad));
        Inventario.guardarProductosEnArchivo("productos/productos.txt"); // Guardar cambios en el archivo
        System.out.println("Producto actualizado.");
    }

    private static void productoMasCostoso() {
        Producto masCostoso = Inventario.productoMasCostoso();
        if (masCostoso != null) {
            System.out.println("Producto mas costoso: " + masCostoso);
        } else {
            System.out.println("No hay productos disponibles.");
        }
    }

    private static void buscarPorCategoria(Scanner scanner) {
        System.out.print("Ingrese la categora a buscar: ");
        String categoria = scanner.nextLine();
        List<Producto> productosEncontrados = Inventario.buscarPorCategoria(categoria);
        
        if (productosEncontrados.isEmpty()) {
            System.out.println("No se encontraron productos en la categoria '" + categoria + "'.");
        } else {
            System.out.println("Productos encontrados en la categoria '" + categoria + "':");
            for (Producto producto : productosEncontrados) {
                System.out.println(producto);
            }
        }
    }

}
