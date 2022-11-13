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
import uniandes.isis2304.parranderos.negocio.Pedido;
import uniandes.isis2304.parranderos.negocio.Proveedor;
import uniandes.isis2304.parranderos.negocio.Comprador;
import uniandes.isis2304.parranderos.negocio.Contenedor;
import uniandes.isis2304.parranderos.negocio.Supermercado;
import uniandes.isis2304.parranderos.negocio.Sucursal;

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
public class PersistenciaParranderos 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaParranderos.class.getName());
	
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
	private static PersistenciaParranderos instance;
	
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
	private PersistenciaParranderos ()
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
	private PersistenciaParranderos (JsonObject tableConfig)
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
	public static PersistenciaParranderos getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaParranderos getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos (tableConfig);
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
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaTipoBebida ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaBebida ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaBar ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaBebedor ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaGustan ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaSirven ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaVisitan ()
	{
		return tablas.get (7);
	}
	
	public String darSeqSuperAndes()
	{
		return tablas.get(8);
	}

	public String darTablaUsuario()
	{
		return tablas.get(9);
	}

	public String darTablaPromocion()
	{
		return tablas.get(10);
	}

	public String darTablaProducto()
	{
		return tablas.get(11);
	}

	public String darTablaFactura()
	{
		return tablas.get(15);
	}

	public String darTablaCompra()
	{
		return tablas.get(16);
	}
	public String darTablaCategoria()
	{
		return tablas.get(17);
	}
	public String darTablaPedido()
	{
		return tablas.get(18);
	}
	public String darTablaProveedor()
	{
		return tablas.get(19);
	}
	public String darTablaComprador()
	{
		return tablas.get(20);
	}
	public String darTablaContenedor()
	{
		return tablas.get(21);
	}
	public String darTablaSupermercado()
	{
		return tablas.get(22);
	}
	public String darTablaSucursal()
	{
		return tablas.get(23);
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

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	

 }
