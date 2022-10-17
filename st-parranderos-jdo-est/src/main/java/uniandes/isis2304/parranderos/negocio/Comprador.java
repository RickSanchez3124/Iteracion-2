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
public class Comprador implements VOComprador
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private String tipo;
	
	/**
	 * El nombre del bar
	 */
	private String nombre_comprador;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private long id_doc;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Comprador() 
    {
    	this.tipo = "";
		this.nombre_comprador = "";
		this.id_doc = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Comprador(String tipo, String nombre_comprador, long id_doc) 
    {
    	this.tipo = tipo;
		this.nombre_comprador = nombre_comprador;
		this.id_doc = id_doc;
	}

    /**
	 * @return El id del bar
	 */
	public String getTipo() 
	{
		return tipo;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public String getNombre_comprador() 
	{
		return nombre_comprador;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setNombre_comprador(String nombre_comprador) 
	{
		this.nombre_comprador = nombre_comprador;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public long getId_doc() 
	{
		return id_doc;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setId_doc(long id_doc) 
	{
		this.id_doc = id_doc;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Comprador [tipo=" + tipo + ", nombre_comprador=" + nombre_comprador + ", id_doc=" + id_doc +"]";
	}
	

}
