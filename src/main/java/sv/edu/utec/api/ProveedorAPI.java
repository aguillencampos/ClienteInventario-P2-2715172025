package sv.edu.utec.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ProveedorAPI {

    public List<Producto> obtenerProductos(int limite) throws IOException, InterruptedException {
        // 1. Construir la URL con el parámetro limite
        String url = "https://dummyjson.com/products?limit=" + limite + "&select=title,stock";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // 2. Enviar solicitud y validar código de estado
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener productos. Código recibido: " + response.statusCode());
        }

        // 3. Deserializar el cuerpo a RespuestaProductos
        ObjectMapper mapper = new ObjectMapper();
        RespuestaProductos respuesta = mapper.readValue(response.body(), RespuestaProductos.class);

        // 4. Convertir cada ProductoApi con aProducto()
        List<Producto> productos = new ArrayList<>();
        if (respuesta.getProducts() != null) {
            for (ProductoApi productoApi : respuesta.getProducts()) {
                productos.add(productoApi.aProducto());
            }
        }

        return productos;
    }
}
