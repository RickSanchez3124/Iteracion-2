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

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
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
	public SQLUtil (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas Factura, Categoria, Compra, Comprador,
	 * Contenedor, Pedido y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
        Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura());          
        Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria ());
        Query qCompra = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompra ());
        Query qComprador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaComprador ());
        Query qContenedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaContenedor ());
        Query qPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido ());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto ());
		Query qPromocion = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaPromocion());
		Query qProveedor = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaProveedor());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaSucursal());
		Query qSupermercado = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaSupermercado());
		Query qUsuario = pm.newQuery(SQL, "DELETE FROM" +pp.darTablaUsuario());

        long facturaEliminados = (long) qFactura.executeUnique ();
        long categoriaEliminados = (long) qCategoria.executeUnique ();
        long compraEliminadas = (long) qCompra.executeUnique ();
        long compradorsEliminadas = (long) qComprador.executeUnique ();
        long contenedorEliminados = (long) qContenedor.executeUnique ();
        long PedidoesEliminados = (long) qPedido.executeUnique ();
        long productosEliminados = (long) qProducto.executeUnique ();
		long promocionEliminados = (long) qPromocion.executeUnique();
		long proveedorEliminados = (long) qProveedor.executeUnique();
		long sucursalEliminadas = (long) qSucursal.executeUnique();
		long supermercadoEliminados = (long) qSupermercado.executeUnique();
		long usuarioEliminados = (long) qUsuario.executeUnique();

        return new long[] {facturaEliminados, categoriaEliminados, compraEliminadas, compradorsEliminadas, 
        		contenedorEliminados, PedidoesEliminados, productosEliminados, promocionEliminados, proveedorEliminados,
				sucursalEliminadas, supermercadoEliminados, usuarioEliminados};
	}

}
