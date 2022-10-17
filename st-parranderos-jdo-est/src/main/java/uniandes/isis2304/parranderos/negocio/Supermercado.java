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

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Supermercado implements VOSupermercado
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private String nombre;
	
	/**
	 * El nombre del bar
	 */
	private long nit_proveedor;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Supermercado() 
    {
    	this.nombre = "";
		this.nit_proveedor = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Supermercado(String nombre, long nit_proveedor) 
    {
    	this.nombre = nombre;
		this.nit_proveedor = nit_proveedor;
	}

    /**
	 * @return El id del bar
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public long getNit_proveedor() 
	{
		return nit_proveedor;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setNit_proveedor(long nit_proveedor) 
	{
		this.nit_proveedor = nit_proveedor;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Supermercado [nombre=" + nombre + ", nit_proveedor=" + nit_proveedor +"]";
	}
	

}
