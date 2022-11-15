package uniandes.isis2304.parranderos.negocio;

import java.util.*;

public interface VOCarrito {
    public List<Producto> getProductos();
    public void setProductos(List<Producto> productos);
    public long getTotalPrecio();
    public void vaciarCarrito();
    public void eliminarElemento(Producto producto);
    public void addProducto(Producto producto);
    public String nombreUsuario();
    public long documentoUsuario();
}
