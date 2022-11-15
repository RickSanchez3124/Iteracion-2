package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
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
     *          Métodos para manejar los roles
     *****************************************************************/

    public Rol adicionarRol (String nombre, String descripcion, Integer documento) 
    {
        log.info ("Adicionando rol [" + nombre + ", " + descripcion + ", " + documento + "]");
        Rol rol = pp.adicionarRol (nombre, descripcion, documento);        
        log.info ("Adicionando rol: " + rol);
        return rol;
    }

    public long eliminarRolPorNombre (String  nombre)
    {
        log.info ("Eliminando rol por nombre: " + nombre);
        long resp = pp.eliminarRolPorNombre (nombre);        
        log.info ("Eliminando rol por nombre: " + resp + " tuplas eliminadas");
        return resp;
    }

    public Rol darRolPorNombre (String  nombre)
    {
        log.info ("Dar información de un rol por nombre: " + nombre);
        Rol rol = pp.darRolPorNombre (nombre);
        log.info ("Buscando un rol por nombre: " + rol != null ? rol : "NO EXISTE");
        return rol;
    }

    public Rol darRolPorDocumento(Integer  documento)
    {
        log.info ("Dar información de los roles por documento: " + documento);
        Rol rol = pp.darRolPorDocumento (documento);
        log.info ("Buscando categorias por tipo: " + rol != null ? rol : "NO EXISTE");
        return rol;
    }

    public List<Rol> darRoles ()
    {
        log.info ("Consultando roles");
        List<Rol> rol = pp.darRoles ();    
        log.info ("Consultando roles: " + rol.size() + " existentes");
        return rol;
    }

    public List<VORol> darVORoles ()
    {
        log.info ("Generando los VO de roles");        
        List<VORol> voRol = new LinkedList<VORol> ();
        for (Rol tb : pp.darRoles ())
        {
            voRol.add(tb);
        }
        log.info ("Generando los VO de categorias: " + voRol.size() + " existentes");
        return voRol;

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

    /* ****************************************************************
     *          Métodos para administración
     *****************************************************************/

    public long [] limpiarParranderos ()
    {
        log.info ("Limpiando la BD de Parranderos");
        long [] borrados = pp.limpiarSuperandes();    
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrados;
    }

    /** Métodos para manejar el carrito */

    public Carrito solicitarCarrito(Usuario usuario){
        return new Carrito(usuario);
    }

    public void agregarProductoACarrito(Carrito carrito, Producto producto){
        carrito.addProducto(producto);
    }

    public void eliminarProductoDeCarrito(Carrito carrito, Producto producto){
        carrito.eliminarElemento(producto);
    }

    public long precioEnElCarrito(Carrito carrito){
        return carrito.getTotalPrecio();
    }

    public List<Producto> getProductosCarrito(Carrito carrito){
        return carrito.getProductos();
    }

    public void Pagar(Carrito carrito){
        List<Producto> productos = carrito.getProductos();
        List<Compra> compras = darCompras();
        long index = compras.size();
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        String fecha = date.toString();
        for (Producto producto : productos) {
            String nombre = producto.getNombre();
            String coBrras = producto.getCodBarras();
            adicionarCompra(index,date, nombre);
            adicionarPedido(fecha , 1, 5, 5, "Por entregar", nombre);
            eliminarProductoPorCodBarras(coBrras);
        }
        adicionarFactura(date, carrito.nombreUsuario());
        carrito.vaciarCarrito();
    }

    public void vaciarCarritoCompras(Carrito carrito){
        java.util.Date creacion = carrito.getDateCreacion();
        java.util.Date actual = new java.util.Date();


        if (actual.compareTo(creacion) > 3600){
            carrito.vaciarCarrito();
        }
    }

}
