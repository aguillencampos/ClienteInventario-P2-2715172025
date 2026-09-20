package sv.edu.utec.servicio;

import sv.edu.utec.api.ProveedorAPI;
import sv.edu.utec.modelo.Producto;
import sv.edu.utec.dao.ProductoDAO;

import java.io.IOException;
import java.util.List;

public class SincronizacionService {

    private final ProveedorAPI proveedorAPI;
    private final ProductoDAO productoDAO;

    // 1. Recibir por constructor un ProveedorAPI y un ProductoDAO
    public SincronizacionService(ProveedorAPI proveedorAPI, ProductoDAO productoDAO) {
        this.proveedorAPI = proveedorAPI;
        this.productoDAO = productoDAO;
    }

    // 2. Método sincronizar
    public int[] sincronizar(int limite) throws IOException, InterruptedException {
        int insertados = 0;
        int actualizados = 0;

        // Obtener productos del proveedor
        List<Producto> productos = proveedorAPI.obtenerProductos(limite);

        for (Producto producto : productos) {
            if (productoDAO.existe(producto.getId())) {
                // Actualizar si ya existe
                productoDAO.actualizar(producto);
                actualizados++;
            } else {
                // Insertar si no existe
                productoDAO.insertar(producto);
                insertados++;
            }
        }

        // 3. Devolver resultados en un arreglo int[]
        return new int[]{insertados, actualizados};
    }
}
