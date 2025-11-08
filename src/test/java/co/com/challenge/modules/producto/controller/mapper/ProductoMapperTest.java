package co.com.challenge.modules.producto.controller.mapper;

import co.com.challenge.modules.producto.dto.ProductoDTO;
import co.com.challenge.modules.producto.entity.Producto;
import co.com.challenge.modules.producto.mapper.ProductoMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoMapperTest {

    @Test
    void testConvertirADTO() {
        // Crea una instancia de Producto
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Producto 1");
        producto.setStock(100);
        producto.setPrecio(new BigDecimal("19.99"));
        producto.setActivo(true);

        // Convierte a ProductoDTO
        ProductoDTO productoDTO = ProductoMapper.convertirADTO(producto);

        // Verifica los valores
        assertEquals(producto.getId(), productoDTO.getId());
        assertEquals(producto.getNombre(), productoDTO.getNombre());
        assertEquals(producto.getStock(), productoDTO.getStock());
        assertEquals(producto.getPrecio(), productoDTO.getPrecio());
        assertEquals(producto.isActivo(), productoDTO.isActivo());
    }

    @Test
    void testConvertirAEntidad() {
        // Crea una instancia de ProductoDTO
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        productoDTO.setNombre("Producto 1");
        productoDTO.setStock(100);
        productoDTO.setPrecio(new BigDecimal("19.99"));
        productoDTO.setActivo(true);

        // Convierte a Producto
        Producto producto = new ProductoMapper().convertirAEntidad(productoDTO);

        // Verifica los valores
        assertEquals(productoDTO.getId(), producto.getId());
        assertEquals(productoDTO.getNombre(), producto.getNombre());
        assertEquals(productoDTO.getStock(), producto.getStock());
        assertEquals(productoDTO.getPrecio(), producto.getPrecio());
        assertEquals(productoDTO.isActivo(), producto.isActivo());
    }
}
