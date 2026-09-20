package sv.edu.utec.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

// Clase RespuestaProductos
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaProductos {
    private List<ProductoApi> products;

    // Constructor sin argumentos
    public RespuestaProductos() {
    }

    // Getter y Setter
    public List<ProductoApi> getProducts() {
        return products;
    }

    public void setProducts(List<ProductoApi> products) {
        this.products = products;
    }
}
