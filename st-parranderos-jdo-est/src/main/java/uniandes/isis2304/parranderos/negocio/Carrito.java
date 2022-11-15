package uniandes.isis2304.parranderos.negocio;
import java.util.*;


public class Carrito implements VOCarrito{

    private List<Producto> productos;
    Date creacion;
    private Usuario usuario;

    public Carrito(Usuario usuario){
        this.productos = new ArrayList<Producto>();
        this.creacion = new Date();
        this.usuario = usuario;

    }

    public List<Producto> getProductos(){
        return productos;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

    public long getTotalPrecio(){
        long total = 0;
        for (Producto producto : this.productos) {
            total = total + producto.getPrecioUnidad();
        }
        return total;
    }

    public void vaciarCarrito(){
        this.productos = new ArrayList<Producto>();
    }

    public void eliminarElemento(Producto producto){
        this.productos.remove(producto);
    }

    public void addProducto(Producto producto){
        this.productos.add(producto);
    
    }

    public Date getDateCreacion(){
        return this.creacion;
    }

    public String nombreUsuario(){
        return this.usuario.getNombre();
    }

    public long documentoUsuario(){
        return this.usuario.getDocumento();
    }



    
    
}
