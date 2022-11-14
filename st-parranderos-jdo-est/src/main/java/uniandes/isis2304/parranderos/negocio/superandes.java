package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.*;
import org.apache.*;
import org.apache.log4j.Logger;
import java.sql.Date;

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

    /** Método para limpiar la base de datos */

    public long [] limpiarSuperAndes(){
        log.info ("Limpiando la base de datos de superandes");
        long[] borrados = pp.limpiarSuperAndes();
        return borrados;
    }

     /* ****************************************************************
     *          Métodos para manejar las categorias
     *****************************************************************/

    public Categoria adicionarCategoria (String  tipo, String nombreProducto) 
    {
        log.info ("Adicionando categoria [" + tipo + ", " + nombreProducto + "]");
        Categoria categoria = pp.adicionarCategoria (tipo, nombreProducto);        
        log.info ("Adicionando categoria: " + categoria);
        return categoria;
    }

    public long eliminarCategoriaPorTipo (String  tipo)
    {
        log.info ("Eliminando categoria por tipo: " + tipo);
        long resp = pp.eliminarCategoriaPorTipo (tipo);        
        log.info ("Eliminando categoria por tipo: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Categoria darCategoriaPorTipo (String  tipo)
    {
        log.info ("Dar información de una categoria por tipo: " + tipo);
        Categoria categoria = pp.darCategoriaPorTipo (tipo);
        log.info ("Buscando una categoria por tipo: " + categoria != null ? categoria : "NO EXISTE");
        return categoria;
    }

    public List<Categoria> darCategoriasPorTipo (String  tipo)
    {
        log.info ("Dar información de las categorias por tipo: " + tipo);
        List<Categoria> categorias = pp.darCategoriasPorTipo (tipo);
        log.info ("Buscando categorias por tipo: " + categorias != null ? categorias : "NO EXISTE");
        return categorias;
    }

    public List<Categoria> darCategorias ()
    {
        log.info ("Consultando categorias");
        List<Categoria> categoria = pp.darCategorias ();    
        log.info ("Consultando categorias: " + categoria.size() + " existentes");
        return categoria;
    }

      /* ****************************************************************
     *          Métodos para manejar las compras
     *****************************************************************/

    public Compra adicionarCompra (long id, Date fecha, String nombreProducto) 
    {
        log.info ("Adicionando compra [" + id + ", " + fecha + ", " + nombreProducto + "]");
        Compra compra = pp.adicionarCompra (id, fecha, nombreProducto);        
        log.info ("Adicionando compra: " + compra);
        return compra;
    }

    public long eliminarCompraPorId (long  id)
    {
        log.info ("Eliminando compra por id: " + id);
        long resp = pp.eliminarCompraPorId (id);        
        log.info ("Eliminando compra por id: " + resp + " tuplas eliminadas");
        return resp;
    }

    public List<Compra> darCompras ()
    {
        log.info ("Consultando compras");
        List<Compra> compra = pp.darCompras ();    
        log.info ("Consultando compras: " + compra.size() + " existentes");
        return compra;
    }

    public List<Compra> darComprasIgual (Date fecha, String nombreProducto )
    {
        log.info ("Dar información de las compras por fecha y nombre producto: " + fecha + nombreProducto);
        List<Compra> compra = pp.darComprasIgual (fecha,nombreProducto);
        log.info ("Buscando compras por fecha y nombre producto: " + fecha != null ? fecha : "NO EXISTE" + nombreProducto != null ? nombreProducto : "NO EXISTE");
        return compra;
    }

      /* ****************************************************************
     *          Métodos para manejar los compradores
     *****************************************************************/

    public Comprador adicionarComprador (String tipo, String nombre_comprador, long id_doc) 
    {
        log.info ("Adicionando comprador [" + tipo + ", " + nombre_comprador + ", " + id_doc + "]");
        Comprador comprador = pp.adicionarComprador (tipo, nombre_comprador, id_doc);        
        log.info ("Adicionando comprador: " + comprador);
        return comprador;
    }

    public long eliminarCompradorPorId (long  id_doc)
    {
        log.info ("Eliminando comprador por id: " + id_doc);
        long resp = pp.eliminarCompradorPorId (id_doc);        
        log.info ("Eliminando comprador por id: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Comprador darCompradorPorId (long  id_doc)
    {
        log.info ("Dar información de un comprador por id: " + id_doc);
        Comprador comprador = pp.darCompradorPorId (id_doc);
        log.info ("Buscando un comprador por id: " + comprador != null ? comprador : "NO EXISTE");
        return comprador;
    }

    public List<Comprador> darCompradoresPorNombre (String  nombre_comprador)
    {
        log.info ("Dar información de los compradores por nombre: " + nombre_comprador);
        List<Comprador> compradores = pp.darCompradoresPorNombre (nombre_comprador);
        log.info ("Buscando compradores por nombre: " + compradores != null ? compradores : "NO EXISTE");
        return compradores;
    }

    public List<Comprador> darCompradores ()
    {
        log.info ("Consultando compradores");
        List<Comprador> comprador = pp.darCompradores ();    
        log.info ("Consultando compradores: " + comprador.size() + " existentes");
        return comprador;
    }

    public List<VOComprador> darVOCompradores ()
    {
        log.info ("Generando los VO de compradores");        
        List<VOComprador> voComprador = new LinkedList<VOComprador> ();
        for (Comprador tb : pp.darCompradores ())
        {
            voComprador.add(tb);
        }
        log.info ("Generando los VO de categorias: " + voComprador.size() + " existentes");
        return voComprador;

    }

      /* ****************************************************************
     *          Métodos para manejar los contenedores
     *****************************************************************/

    public Contenedor adicionarContenedor (long id, String tipo, long capacidad, String categoria) 
    {
        log.info ("Adicionando contenedor [" + id + ", " + tipo + ", " + capacidad + ", " + categoria + "]");
        Contenedor contenedor = pp.adicionarContenedor (id, tipo, capacidad, categoria);        
        log.info ("Adicionando contenedor: " + contenedor);
        return contenedor;
    }

    public long eliminarContenedorPorId (long  id)
    {
        log.info ("Eliminando contenedor por id: " + id);
        long resp = pp.eliminarContenedorPorId (id);        
        log.info ("Eliminando contenedor por id: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Contenedor darContenedorPorId (long  id)
    {
        log.info ("Dar información de un contenedor por id: " + id);
        Contenedor contenedor = pp.darContenedorPorId (id);
        log.info ("Buscando un contenedor por id: " + contenedor != null ? contenedor : "NO EXISTE");
        return contenedor;
    }

    public List<Contenedor> darContendoresPorTipo (String  tipo)
    {
        log.info ("Dar información de los compradores por tipo: " + tipo);
        List<Contenedor> contenedores = pp.darContenedoresPorTipo (tipo);
        log.info ("Buscando contenedores por tipo: " + contenedores != null ? contenedores : "NO EXISTE");
        return contenedores;
    }

    public List<Contenedor> darContenedores ()
    {
        log.info ("Consultando contenedores");
        List<Contenedor> contenedor = pp.darContenedores ();    
        log.info ("Consultando contenedores: " + contenedor.size() + " existentes");
        return contenedor;
    }

    public long actualizarCapacidadC (long capacidad, long id)
	{
        log.info ("Actualizando la capacidad del contenedor: " + id);
        long resp = pp.actualizarCapacidadC(capacidad, id) ;
        log.info ("Actualizando la capacidad del contenedor: " + resp + " tuplas actualizadas");
        return resp;
	}

    public List<VOContenedor> darVOContenedores ()
    {
        log.info ("Generando los VO de contenedores");        
        List<VOContenedor> voContenedor = new LinkedList<VOContenedor> ();
        for (Contenedor tb : pp.darContenedores ())
        {
            voContenedor.add(tb);
        }
        log.info ("Generando los VO de contenedores: " + voContenedor.size() + " existentes");
        return voContenedor;
    }

      /* ****************************************************************
     *          Métodos para manejar las facturas
     *****************************************************************/

    public Factura adicionarFactura (Date fecha, String nombreComprador) 
    {
        log.info ("Adicionando factura [" + fecha + ", " + nombreComprador + "]");
        Factura factura = pp.adicionarFactura (fecha, nombreComprador);        
        log.info ("Adicionando factura: " + factura);
        return factura;
    }

    public long eliminarFacturaPorFecha (Date  fecha)
    {
        log.info ("Eliminando factura por fecha: " + fecha);
        long resp = pp.eliminarFacturaPorFecha (fecha);        
        log.info ("Eliminando factura por fecha: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Factura darFacturaPorFecha (Date  fecha)
    {
        log.info ("Dar información de una factura por fecha: " + fecha);
        Factura factura = pp.darFacturaPorFecha (fecha);
        log.info ("Buscando una factura por fecha: " + factura != null ? factura : "NO EXISTE");
        return factura;
    }

    public List<Factura> darFacturas ()
    {
        log.info ("Consultando facturas");
        List<Factura> factura = pp.darFacturas ();    
        log.info ("Consultando facturas: " + factura.size() + " existentes");
        return factura;
    }

      /* ****************************************************************
     *          Métodos para manejar los pedidos
     *****************************************************************/

    public Pedido adicionarPedido (String fecha_e, long cant_productos, long calificacion_productos, long calificacion_envio, String estado, String producto) 
    {
        log.info ("Adicionando pedido [" + fecha_e + ", " + cant_productos + ", " + calificacion_productos + ", " + calificacion_envio + ", " + estado + ", " + producto + "]");
        Pedido pedido = pp.adicionarPedido (fecha_e, cant_productos, calificacion_productos, calificacion_envio, estado, producto );        
        log.info ("Adicionando pedido: " + pedido);
        return pedido;
    }

    public long eliminarPedidoPorFecha (String  fecha_e)
    {
        log.info ("Eliminando pedido por fecha: " + fecha_e);
        long resp = pp.eliminarPedidoPorFecha (fecha_e);        
        log.info ("Eliminando pedido por fecha: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Pedido darPedidoPorFecha (String  fecha_e)
    {
        log.info ("Dar información de un pedido por fecha: " + fecha_e);
        Pedido pedido = pp.darPedidoPorFecha (fecha_e);
        log.info ("Buscando un pedido por fecha: " + pedido != null ? pedido : "NO EXISTE");
        return pedido;
    }

    public List<Pedido> darPedidosPorFecha (String  fecha_e)
    {
        log.info ("Dar información de los pedidos por fecha: " + fecha_e);
        List<Pedido> pedidos = pp.darPedidosPorFecha (fecha_e);
        log.info ("Buscando pedidos por nombre: " + pedidos != null ? pedidos : "NO EXISTE");
        return pedidos;
    }

    public List<Pedido> darPedidos ()
    {
        log.info ("Consultando pedidos");
        List<Pedido> pedido = pp.darPedidos ();    
        log.info ("Consultando compradores: " + pedido.size() + " existentes");
        return pedido;
    }

    public List<VOPedido> darVOPedidos ()
    {
        log.info ("Generando los VO de pedidos");        
        List<VOPedido> voPedido = new LinkedList<VOPedido> ();
        for (Pedido tb : pp.darPedidos ())
        {
            voPedido.add(tb);
        }
        log.info ("Generando los VO de pedidos: " + voPedido.size() + " existentes");
        return voPedido;
    }
}
