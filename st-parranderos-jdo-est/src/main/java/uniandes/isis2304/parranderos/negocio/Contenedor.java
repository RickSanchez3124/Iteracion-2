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
public class Contenedor implements VOContenedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El nombre del bar
	 */
	private String tipo;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private long capacidad;
	
	/**
	 * El presupuesto del bar (ALTO, MEDIO, BAJO)
	 */
	private String categoria;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Contenedor() 
    {
    	this.id = 0;
		this.tipo = "";
		this.capacidad = 0;
		this.categoria = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Contenedor(long id, String tipo, long capacidad, String categoria) 
    {
    	this.id = id;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.categoria = categoria;
	}

    /**
	 * @return El id del bar
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public String getTipo() 
	{
		return tipo;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public long getCapacidad() 
	{
		return capacidad;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setCapacidad(long capacidad) 
	{
		this.capacidad = capacidad;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getCategoria() 
	{
		return categoria;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Contenedor [id=" + id + ", tipo=" + tipo + ", capacidad=" + capacidad + ", categoria=" + categoria+"]";
	}
	

}
