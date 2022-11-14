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

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Pedido;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLPedido 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPedido (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param fecha_e - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPedido (PersistenceManager pm, String fecha_e, long cant_productos, long calificacion_productos, long calificacion_envio, String estado, String producto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPedido () + "(fecha_e, cant_productos, calificacion_productos, calificacion_envio, estado, producto) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(fecha_e, cant_productos, calificacion_productos, calificacion_envio, estado, producto);
        return (long) q.executeUnique();
	}

	public long eliminarPedidoPorFecha (PersistenceManager pm, String fecha_e)
   {
       Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido () + " WHERE fecha_e = ?");
       q.setParameters( fecha_e);
       return (long) q.executeUnique();
   }

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Pedido darPedidoPorFecha (PersistenceManager pm, String fecha_e) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedido () + " WHERE fecha_e = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(fecha_e);
		return (Pedido) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<Pedido> darPedidosPorFecha (PersistenceManager pm, String fecha_e) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedido () + " WHERE fecha_e = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(fecha_e);
		return (List<Pedido>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Pedido> darPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedido ());
		q.setResultClass(Pedido.class);
		return (List<Pedido>) q.executeList();
	}
}
