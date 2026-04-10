package com.ecommerce.backend_api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ecommerce.backend_api.model.Carrito;
import com.ecommerce.backend_api.model.CarritoItems;
import com.ecommerce.backend_api.model.Producto;
import com.ecommerce.backend_api.repository.CarritoRepository;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoService productoService;

    public CarritoService(CarritoRepository carritoRepository, ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;

    }

    private float total = 0;

    public float getTotal() {
        return total;
    }

    public boolean agregarItem(Long idCarrito, Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto no registrado");
        }

        Producto productoEncontrado = productoService.buscarProducto(producto.getNombre());

        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + idCarrito));

        if (productoEncontrado == null) {
            throw new IllegalArgumentException("Producto no registrado");
        }

        CarritoItems nuevoItem = new CarritoItems();

        nuevoItem.setProducto(productoEncontrado);
        nuevoItem.setCantidad(cantidad);
        nuevoItem.setSubTotal(productoEncontrado.getPrecio() * cantidad);

        nuevoItem.setCarrito(carrito);

        carrito.getItems().add(nuevoItem);
        carrito.setTotal(carrito.getTotal() + nuevoItem.getSubTotal());
        carritoRepository.save(carrito);
        return true;
    }

    public Long crearCarrito() {
        Carrito carrito = new Carrito();
        carritoRepository.save(carrito);
        return carrito.getId();
    }

    public boolean eliminarItem(Long carritoId, Producto producto) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + carritoId));

        carrito.getItems().removeIf(item -> item.getProducto().getNombre().equals(producto.getNombre()));

        float nuevoTotal = carrito.getItems().stream()
                .map(CarritoItems::getSubTotal)
                .reduce(0f, Float::sum);

        carrito.setTotal(nuevoTotal);
        carritoRepository.save(carrito);
        return true;
    }

    public List<CarritoItems> getItems(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + carritoId));

        return carrito.getItems();
    }

    public float obtenerTotal(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + carritoId));

        return carrito.getTotal();
    }

}
