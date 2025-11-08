package co.com.challenge.modules.producto.controller;

import co.com.challenge.modules.producto.entity.Producto;
import co.com.challenge.modules.producto.manager.ProductoManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductoControllerTest {

    @Mock
    private ProductoManagerImpl productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductos() {
        // Configura el comportamiento del servicio mock
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Producto 1");
        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Producto 2");
        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(productoService.obtenerTodosLosProductos()).thenReturn(productos);

        // Llama al método del controlador
        List<Producto> result = productoController.getAllProductos();

        // Verifica el resultado
        assertEquals(productos, result);
    }
}
