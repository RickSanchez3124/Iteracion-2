package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.*;
import org.apache.*;
import org.apache.log4j.Logger;
import org.junit.Test.None;

import com.google.gson.*;
import uniandes.isis2304.parranderos.persistencia.*;

public class superandes {

    private static Logger log = Logger.getLogger(superandes.class.getName());
    private PersistenciaSuperandes pp;

    public superandes(){
        pp = PersistenciaSuperandes.getInstance();
    }

    public superandes(JsonObject tableConfig){
        pp = PersistenciaSuperandes.getInstance(tableConfig);
    }

    /**Cierra la conexión con la base de datos */
    public void cerrarPersistencia(){
        pp.cerrarUnidadPersistencia();
    }
    /** Método para manejar Usuario */
    public Usuario adicionarUsuario(long documento, String nombre,String rol,String tipoDocumento, String correo, String key_word){
        log.info("Adicionando usuario [" + documento + ", " + nombre + ", " + rol + "]");
        Usuario usuario = pp.adicionarUsuario(documento, nombre, rol, tipoDocumento, correo, key_word);
        log.info("Adicionando usuario:" + usuario);
        return usuario;
    }

    public long eliminarUsuarioPorDocumento(long documento)
    {
        log.info("Eliminando usuario por documento: " + documento);
        long resp = pp.eliminarUsuarioPorDocumento(documento);
        log.info("Eliminando usuario por documento: "+ documento);
        return resp;
    }
    
    public Usuario darUsuarioPorDocumento(long documento){
        log.info("Dar información del usuario con el documento: " + documento);
        Usuario usuario = pp.darUsuarioPorDocumento(documento);
        log.info("Buscando al usuario por id: "+ documento != null ? documento: "No existe");
        return usuario;
    }

    public List<Usuario> darUsuarios(){
        log.info("Consultando usuarios");
        List<Usuario> usuario = pp.darUsuarios();
        log.info("Consultando usuarios: " + usuario.size() + "existentes");
        return usuario;
    }

    public List<VOUsuario> darVOUsuario(){
        log.info("Generando VO de usuario");
        List<VOUsuario> voUsuario = new LinkedList<VOUsuario>();
        for (Usuario us : pp.darUsuarios()){
            voUsuario.add(us);
        }
        return voUsuario;
    }

    /** Método para manejar Supermercado */

    public Supermercado adicionarSupermercado(String nombre, long nit_proveedor){
        log.info("Adicionando supermercado [" + nombre + "]");
        Supermercado supermercado = pp.adicionarSupermercado(nombre, nit_proveedor);
        log.info("Adicionando supermercado: "+ supermercado);
        return supermercado;
    }

    public long eliminarSupermercadoPorNombre(String nombre){
        log.info("Eliminando el supermercado: " + nombre);
        long resp = pp.eliminarSupermercadoPorNombre(nombre);
        log.info("Eliminando el supermercado: " + nombre);
        return resp;
    }

    public Supermercado darSupermercadoPorNombre(String nombre){
        log.info("Dar información del supermercado con el nombre: " + nombre);
        Supermercado supermercado = pp.darSupermercadoPorNombre(nombre);
        log.info("Consultando supermercado por nombre: " + nombre != null ? nombre: "No existe");
        return supermercado;
    }

    public List<Supermercado> darSupermercados(){
        log.info("Consultando supermercados");
        List<Supermercado> supermercado = pp.darSupermercados();
        log.info("Consultado supermercados: " + supermercado.size() + " existentes"); 
        return supermercado;
    }

    public List<VOSupermercado> darVOSupermercado(){
        log.info("Generando VO de supermercado");
        List<VOSupermercado> voSupermercado = new LinkedList<VOSupermercado>();
        for (Supermercado us : pp.darSupermercados()){
            voSupermercado.add(us);
        }
        return voSupermercado;
    }

    /** Métodos para manejar Sucursal */

    public Sucursal adicionarSucursal(String nombre, String direccion, String ciudad, String nombre_supermercado, long id_contenedor, long id_compra, long documento_usuario){
        log.info("Adicionando la sucursal [" + nombre + "]");
        Sucursal sucursal = pp.adicionarSucursal(nombre, direccion, ciudad, nombre_supermercado, id_contenedor, id_compra, documento_usuario);
        log.info("Adicionando supermercado: "+ sucursal);
        return sucursal;
    }

    public long eliminarSucursalPorNombre(String nombre){
        log.info("Eliminando la sucursal: " + nombre);
        long resp = pp.eliminarSucursalPorNombre(nombre);
        log.info("Eliminando la sucursal: " + nombre);
        return resp;
    }

    public Sucursal darSucursalPorNombre(String nombre){
        log.info("Dar información de la sucursal con el nombre: " + nombre);
        Sucursal sucursal = pp.darSucursalPorNombre(nombre);
        log.info("Consultando sucursal por nombre: " + nombre != null ? nombre: "No existe");
        return sucursal;
    }

    public List<Sucursal> darSucursales(){
        log.info("Consultando sucursales");
        List<Sucursal> sucursal = pp.darSucursales();
        log.info("Consultado supermercados: " + sucursal.size() + " existentes"); 
        return sucursal;
    }

    public List<VOSucursal> darVOSucursal(){
        log.info("Generando VO de sucursal");
        List<VOSucursal> voSucursal = new LinkedList<VOSucursal>();
        for (Sucursal us : pp.darSucursales()){
            voSucursal.add(us);
        }
        return voSucursal;
    }

    /** Métodos para manejar Proveedor */

    public Proveedor adicionarProveedor(long nit, String rol, String nombre, String correo_e, String producto_proveedor, String fecha_entregapedido, long calificacion){
        log.info("Adicionando el proveedor [" + nombre + "]");
        Proveedor proveedor = pp.adicionarProveedor(nit, rol, nombre, correo_e, producto_proveedor, fecha_entregapedido, calificacion);
        log.info("Adicionando supermercado: "+ proveedor);
        return proveedor;
    }

    public long eliminarProveedorPorNIT(long nit){
        log.info("Eliminando la el proveedor con nit: " + nit);
        long resp = pp.eliminarProveedorPorNIT(nit);
        log.info("Eliminando la el proveedor con nit: " + nit);
        return resp;
    }

    public Proveedor darProveedorPorNIT(long nit){
        log.info("Dar información del proveedor con el nit: " + nit);
        Proveedor proveedor = pp.darProveedorPorNIT(nit);
        log.info("Consultando proveedor por nit: " + nit != null ? nit: "No existe");
        return proveedor;
    }

    public List<Proveedor> darProveedores(){
        log.info("Consultando proveedores");
        List<Proveedor> proveedor = pp.darProveedores();
        log.info("Consultado proveedores: " + proveedor.size() + " existentes"); 
        return proveedor;
    }

    public List<VOProveedor> darVOProveedor(){
        log.info("Generando VO de proveedor");
        List<VOProveedor> voProveedor = new LinkedList<VOProveedor>();
        for (Proveedor us : pp.darProveedores()){
            voProveedor.add(us);
        }
        return voProveedor;
    }

    /** Métodos para manejar Promocion */

    public Promocion adicionarPromocion(long tipo){
        log.info("Adicionando promocion de tipo [" + tipo + "]");
        Promocion promocion = pp.adicionarPromocion(tipo);
        log.info("Adicionando promoción: "+ tipo);
        return promocion;
    }

    public long eliminarPromocionPorTipo(long tipo){
        log.info("Eliminando promocion por tipo: " + tipo);
        long resp = pp.eliminarPromocionPorTipo(tipo);
        log.info("Eliminando promocion por tipo: " + tipo);
        return resp;
    }

    public Promocion darPromocionPorTipo(long tipo){
        log.info("Dar información de la promoción de tipo: " + tipo);
        Promocion promocion = pp.darPromocionPorTipo(tipo);
        log.info("Consultando proveedor por nit: " + tipo != null ? tipo: "No existe");
        return promocion;
    }

    public List<Promocion> darPromociones(){
        log.info("Consultando promociones");
        List<Promocion> promocion = pp.darPromociones();
        log.info("Consultado promociones: " + promocion.size() + " existentes"); 
        return promocion;
    }

    public List<VOPromocion> darVOPromocion(){
        log.info("Generando VO de promoción");
        List<VOPromocion> voPromocion = new LinkedList<VOPromocion>();
        for (Promocion us : pp.darPromociones()){
            voPromocion.add(us);
        }
        return voPromocion;
    }

    /** Métodos para manejar Producto */

    public Producto adicionarProducto(String nombre, String marca, long precioU, String presentacion, long precio_unimed, long cantidadPresente, String unimed, String espEmpacado, String codBarra, long promocion){
        log.info("Adicionando el producto [" + nombre + "]");
        Producto producto = pp.adicionarProducto(nombre, marca, precioU, presentacion, precio_unimed, cantidadPresente, unimed, espEmpacado, codBarra, promocion);
        log.info("Adicionando el producto: "+ nombre);
        return producto;
    }

    public long eliminarProductoPorCodBarras(String codBarra){
        log.info("Eliminando producto por código de barras: " + codBarra);
        long resp = pp.eliminarProductoPorCodBarras(codBarra);
        log.info("Eliminando promocion por tipo: " + codBarra);
        return resp;
    }

    public Producto darProductoPorCodBarras(String codBarras){
        log.info("Dar información del producto con código de barras: " + codBarras);
        Producto producto = pp.darProductoPorCodBarra(codBarras);
        log.info("Consultando producto por código de barras: " + codBarras != null ? codBarras: "No existe");
        return producto;
    }

    public List<Producto> darProductos(){
        log.info("Consultando productos");
        List<Producto> producto = pp.darProductos();
        log.info("Consultado productos: " + producto.size() + " existentes"); 
        return producto;
    }

    public List<VOProducto> darVOProducto(){
        log.info("Generando VO de producto");
        List<VOProducto> voProducto = new LinkedList<VOProducto>();
        for (Producto us : pp.darProductos()){
            voProducto.add(us);
        }
        return voProducto;
    }    



    /** Método para limpiar la base de datos */

    public long [] limpiarSuperAndes(){
        log.info ("Limpiando la base de datos de superandes");
        long[] borrados = pp.limpiarSuperAndes();
        return borrados;
    }
}
