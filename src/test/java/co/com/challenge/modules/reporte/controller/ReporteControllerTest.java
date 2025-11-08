package co.com.challenge.modules.reporte.controller;

import co.com.challenge.modules.detallepedido.dto.ProductoCantidadDTO;
import co.com.challenge.modules.pedido.dto.PedidoCantidadDTO;
import co.com.challenge.modules.reporte.manager.ReporteManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReporteControllerTest {

    @Mock
    private ReporteManagerImpl reporteService;

    @InjectMocks
    private ReporteController reporteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTop5MasVendidos() {
        // Configura el comportamiento del servicio mock
        ProductoCantidadDTO producto1 = new ProductoCantidadDTO(1, "Producto 1", 10L);
        ProductoCantidadDTO producto2 = new ProductoCantidadDTO(2, "Producto 2", 8L);
        List<ProductoCantidadDTO> productos = Arrays.asList(producto1, producto2);
        when(reporteService.getTop5MasVendidos()).thenReturn(productos);

        // Llama al método del controlador
        List<ProductoCantidadDTO> result = reporteController.getTop5MasVendidos();

        // Verifica el resultado
        assertEquals(productos, result);
    }

    @Test
    void testGetTop5ClientesFrecuentes() {
        // Configura el comportamiento del servicio mock
        PedidoCantidadDTO cliente1 = new PedidoCantidadDTO(1, "Cliente 1", 5L);
        PedidoCantidadDTO cliente2 = new PedidoCantidadDTO(2, "Cliente 2", 3L);
        List<PedidoCantidadDTO> clientes = Arrays.asList(cliente1, cliente2);
        when(reporteService.getTop5ClientesFrecuentes()).thenReturn(clientes);

        // Llama al método del controlador
        List<PedidoCantidadDTO> result = reporteController.getTop5ClientesFrecuentes();

        // Verifica el resultado
        assertEquals(clientes, result);
    }
}

