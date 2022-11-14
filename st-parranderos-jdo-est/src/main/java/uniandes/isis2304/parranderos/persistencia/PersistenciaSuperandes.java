/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.*;

/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaSuperandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	private SQLUsuario sqlUsuario;

	private SQLPromocion sqlPromocion;

	private SQLProducto sqlProducto;

	private SQLFactura sqlFactura;

	private SQLCompra sqlCompra;

	private SQLCategoria sqlCategoria;

	private SQLPedido sqlPedido;

	private SQLProveedor sqlProveedor;

	private SQLComprador sqlComprador;

	private SQLContenedor sqlContenedor;

	private SQLSupermercado sqlSupermercado;

	private SQLSucursal sqlSucursal;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Parranderos_sequence");
		tablas.add("super_andes_it2");
		tablas.add("A_USUARIO");
		tablas.add("A_PROMOCION");
		tablas.add("A_PRODUCTO");
		tablas.add("A_PEDIDO");
		tablas.add("A_PROVEEDOR");
		tablas.add("A_COMPRADOR");
		tablas.add("A_FACTURA");
		tablas.add("A_COMPRA");
		tablas.add("A_CATEGORIA");
		tablas.add("A_CONTENEDOR");
		tablas.add("A_SUPERMERCADO");
		tablas.add("A_SUCURSAL");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{		
		sqlUtil = new SQLUtil(this);
		sqlUsuario = new SQLUsuario(this);
		sqlPromocion = new SQLPromocion(this);
		sqlProducto = new SQLProducto(this);
		sqlFactura = new SQLFactura(this);
		sqlCompra = new SQLCompra(this);
		sqlCategoria = new SQLCategoria(this);
		sqlPedido = new SQLPedido(this);
		sqlProveedor = new SQLProveedor(this);
		sqlComprador = new SQLComprador(this);
		sqlContenedor = new SQLContenedor(this);
		sqlSupermercado = new SQLSupermercado(this);
		sqlSucursal = new SQLSucursal(this);

		}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */

	public String darSeqSuperAndes()
	{
		return tablas.get(0);
	}

	public String darTablaUsuario()
	{
		return tablas.get(2);
	}

	public String darTablaPromocion()
	{
		return tablas.get(3);
	}

	public String darTablaProducto()
	{
		return tablas.get(4);
	}

	public String darTablaFactura()
	{
		return tablas.get(5);
	}

	public String darTablaCompra()
	{
		return tablas.get(6);
	}
	public String darTablaCategoria()
	{
		return tablas.get(7);
	}
	public String darTablaPedido()
	{
		return tablas.get(8);
	}
	public String darTablaProveedor()
	{
		return tablas.get(9);
	}
	public String darTablaComprador()
	{
		return tablas.get(10);
	}
	public String darTablaContenedor()
	{
		return tablas.get(11);
	}
	public String darTablaSupermercado()
	{
		return tablas.get(12);
	}
	public String darTablaSucursal()
	{
		return tablas.get(13);
	}
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/** Limpiar base de datos */
	public long [] limpiarSuperAndes(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try 
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarSuperAndes(pm);
			tx.commit();
			log.info("La base de datos ha quedado borrada");
			return resp;
		}
		catch(Exception e){
			log.error("Exception : "+ e.getMessage() + "\n"+ darDetalleException(e));
			return new long [] {-1, -1, -1, -1, -1, -1, -1};
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Usuarios
	 *****************************************************************/
	public Usuario adicionarUsuario(long documento, String nombre,String rol,String tipoDocumento, String correo, String key_word){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, documento, nombre, rol, tipoDocumento,correo, key_word);
			tx.commit();

			log.trace("Inserción de usuario: " + nombre + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Usuario(documento, nombre, rol, tipoDocumento, correo, key_word);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarUsuarioPorDocumento(long documento){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlUsuario.eliminarUsuarioPorDocumento(pm, documento);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Usuario darUsuarioPorDocumento(long documento){
		return sqlUsuario.darUsuarioPorDocumento(pmf.getPersistenceManager(), documento);
	}

	public List<Usuario> darUsuarios(){
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}

	/** Métodos para manejar Supermercado */

	public Supermercado adicionarSupermercado(String nombre, long nit_proveedor){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlSupermercado.adicionarSupermercado(pm, nombre, nit_proveedor);
			tx.commit();

			log.trace("Inserción de supermercado: " + nombre + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Supermercado(nombre, nit_proveedor);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarSupermercadoPorNombre(String nombre){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlSupermercado.eliminarSupermercadoPorNombre(pm, nombre);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Supermercado darSupermercadoPorNombre(String nombre){
		return sqlSupermercado.darSupermercadoPornombre(pmf.getPersistenceManager(), nombre);
	}

	public List<Supermercado> darSupermercados(){
		return sqlSupermercado.darSupermercados(pmf.getPersistenceManager());
	}

	/** Métodos para manejar sucursal */

	public Sucursal adicionarSucursal(String nombre, String direccion, String ciudad, String nombre_supermercado, long id_contenedor, long id_compra, long documento_usuario){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm,nombre,direccion,ciudad,nombre_supermercado,id_contenedor,id_compra,documento_usuario);
			tx.commit();

			log.trace("Inserción de sucursal: " + nombre + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Sucursal(nombre,direccion,ciudad,nombre_supermercado,id_contenedor,id_compra,documento_usuario);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarSucursalPorNombre(String nombre){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlSucursal.eliminarSucursalPorNombre(pm, nombre);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Sucursal darSucursalPorNombre(String nombre){
		return sqlSucursal.darSucursalPorNombre(pmf.getPersistenceManager(), nombre);
	}

	public List<Sucursal> darSucursales(){
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}



	/** Métodos para manejar proveedor */

	public Proveedor adicionarProveedor(long nit, String rol, String nombre, String correo_e, String producto_proveedor, String fecha_entregapedido, long calificacion){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm,nit, rol, nombre, correo_e, producto_proveedor, fecha_entregapedido, calificacion);
			tx.commit();

			log.trace("Inserción de proveedor: " + nombre + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Proveedor(nit, rol, nombre, correo_e, producto_proveedor, fecha_entregapedido, calificacion);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarProveedorPorNIT(long nit){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlProveedor.eliminarProveedorPorNit(pm, nit);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Proveedor darProveedorPorNIT(long nit){
		return sqlProveedor.darProveedorPorNit(pmf.getPersistenceManager(), nit);
	}

	public List<Proveedor> darProveedores(){
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}

	/** Métodos para manejar promociones */

	public Promocion adicionarPromocion(long tipo){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm,tipo);
			tx.commit();

			log.trace("Inserción de promociónn de tipo: " + tipo + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Promocion(tipo);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarPromocionPorTipo(long tipo){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlPromocion.eliminarPromocion(pm, tipo);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Promocion darPromocionPorTipo(long tipo){
		return sqlPromocion.darPromocion(pmf.getPersistenceManager(), tipo);
	}

	public List<Promocion> darPromociones(){
		return sqlPromocion.darPromociones(pmf.getPersistenceManager());
	}

	/** Métodos para manejar Productos */

	public Producto adicionarProducto(String nombre, String marca, long precioU, String presentacion, long precio_unimed, long cantidadPresente, String unimed, String espEmpacado, String codBarra, long promocion){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm,nombre, marca, precioU, presentacion, precio_unimed, cantidadPresente, unimed, espEmpacado, codBarra, promocion);
			tx.commit();

			log.trace("Inserción del producto: " + nombre + ": " + tuplasInsertadas+ "tuplas insertadas");
			return new Producto (nombre, marca, precioU, presentacion, precio_unimed, cantidadPresente, unimed, espEmpacado, codBarra, promocion);


		} catch (Exception e) {

			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarProductoPorCodBarras(String codBarra){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			long resp = sqlProducto.eliminarProductoPorCodBarras(pm, codBarra);
			tx.commit();

			return resp;
		}catch(Exception e){
			log.error("Exception: "+ e.getMessage() + "\n"+ darDetalleException(e));
			return -1;
		}
		finally{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Producto darProductoPorCodBarra(String codBarra){
		return sqlProducto.darProductoPorCodBarras(pmf.getPersistenceManager(), codBarra);
	}

	public List<Producto> darProductos(){
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	

 }

