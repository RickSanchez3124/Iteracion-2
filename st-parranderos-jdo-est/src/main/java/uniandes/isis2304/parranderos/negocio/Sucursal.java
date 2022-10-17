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
public class Sucursal implements VOSucursal
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
	private String direccion;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private String ciudad;
	
	/**
	 * El presupuesto del bar (ALTO, MEDIO, BAJO)
	 */
	private String nombre_supermercado;
	
	/**
	 * El número de sedes del bar en la ciudad
	 */
	private long id_contenedor;

	/**
	 * El número de sedes del bar en la ciudad
	 */
	private long id_compra;

	/**
	 * El número de sedes del bar en la ciudad
	 */
	private long documento_usuario;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Sucursal() 
    {
    	this.nombre = "";
		this.direccion = "";
		this.ciudad = "";
		this.nombre_supermercado = "";
		this.id_contenedor = 0;
		this.id_compra = 0;
		this.documento_usuario = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Sucursal(String nombre, String direccion, String ciudad, String nombre_supermercado, long id_contenedor, long id_compra, long documento_usuario) 
    {
    	this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.nombre_supermercado = nombre_supermercado;
		this.id_contenedor = id_contenedor;
		this.id_compra = id_compra;
		this.documento_usuario = documento_usuario;
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
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public String getCiudad() 
	{
		return ciudad;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getNombre_supermercado() 
	{
		return nombre_supermercado;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setNombre_supermercado(String nombre_supermercado) 
	{
		this.nombre_supermercado = nombre_supermercado;
	}
	
	/**
	 * @return la cantSedes del bar
	 */
	public long getId_contenedor() 
	{
		return id_contenedor;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setId_contenedor(long id_contenedor) 
	{
		this.id_contenedor = id_contenedor;
	}

	/**
	 * @return la cantSedes del bar
	 */
	public long getId_compra() 
	{
		return id_compra;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setId_compra(long id_compra) 
	{
		this.id_compra = id_compra;
	}

	/**
	 * @return la cantSedes del bar
	 */
	public long getDocumento_usuario() 
	{
		return documento_usuario;
	}

	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setDocumento_usuario(long documento_usuario) 
	{
		this.documento_usuario = documento_usuario;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Sucursal [nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + ", nombre_supermercado=" + nombre_supermercado
				+ ", id_contenedor=" + id_contenedor + ", id_compra=" + id_compra + ", documento_usuario=" + documento_usuario +"]";
	}
	

}
