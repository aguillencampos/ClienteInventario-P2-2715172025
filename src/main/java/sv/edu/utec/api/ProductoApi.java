package sv.edu.utec.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import sv.edu.utec.modelo.Producto;


// Clase ProductoApi
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoApi {
    private int id;
    private String title;
    private int stock;

    // Constructor sin argumentos
    public ProductoApi() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método de conversión a Producto (sin anotaciones en Producto)
    public Producto aProducto() {
        Producto producto = new Producto();
        producto.setId(this.id);

        // Recortar el título si excede 50 caracteres
        if (this.title != null && this.title.length() > 50) {
            producto.setNombre(this.title.substring(0, 50));
        } else {
            producto.setNombre(this.title);
        }

        producto.setStock(this.stock);
        return producto;
    }
}
