package com.ecommerce.backend_api.service;

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

    public boolean agregarProducto(Long idCarrito, Producto producto, int cantidad) {

        Producto productoEncontrado =productoService.buscarProducto(producto.getNombre());
        Carrito carrito = carritoRepository.findById(idCarrito)
                                            .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con ID: " + idCarrito));
        
        if (productoEncontrado == null) {
            throw new IllegalArgumentException("Producto no registrado");
        }

        CarritoItems nuevoItem = new CarritoItems();

        nuevoItem.setProducto(productoEncontrado);
        nuevoItem.setCantidad(cantidad);
        nuevoItem.setSubTotal(productoEncontrado.getPrecio() * cantidad);

        carrito.getItems().add(nuevoItem);
        carrito.setTotal(carrito.getTotal() + nuevoItem.getSubTotal());
        carritoRepository.save(carrito);
        return true;
    }

    public Long crearCarrito(){
        Carrito carrito = new Carrito();
        carritoRepository.save(carrito);
        return carrito.getId();
    }

    public boolean eliminarProducto(String nombre) {
        Producto producto = productoService.buscarProducto(nombre);
        if(producto == null){
            return false;
        }
        return true;
    }

}
