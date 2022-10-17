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
public class Proveedor implements VOProveedor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long nit;
	
	/**
	 * El nombre del bar
	 */
	private String rol;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private String nombre;
	
	/**
	 * El presupuesto del bar (ALTO, MEDIO, BAJO)
	 */
	private String correo_e;
	
	/**
	 * El número de sedes del bar en la ciudad
	 */
	private String producto_proveedor;

	/**
	 * El número de sedes del bar en la ciudad
	 */
	private String fecha_entregapedido;

	/**
	 * El número de sedes del bar en la ciudad
	 */
	private long calificacion;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Proveedor() 
    {
    	this.nit = 0;
		this.rol = "";
		this.nombre = "";
		this.correo_e = "";
		this.producto_proveedor = "";
		this.fecha_entregapedido = "";
		this.calificacion = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Proveedor(long nit, String rol, String nombre, String correo_e, String producto_proveedor, String fecha_entregapedido, long calificacion) 
    {
    	this.nit = nit;
		this.rol = rol;
		this.nombre = nombre;
		this.correo_e = correo_e;
		this.producto_proveedor = producto_proveedor;
		this.fecha_entregapedido = fecha_entregapedido;
		this.calificacion = calificacion;
	}

    /**
	 * @return El id del bar
	 */
	public long getNit() 
	{
		return nit;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setNit(long nit) 
	{
		this.nit = nit;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public String getRol() 
	{
		return rol;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setRol(String rol) 
	{
		this.rol = rol;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getCorreo_e() 
	{
		return correo_e;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setCorreo_e(String correo_e) 
	{
		this.correo_e = correo_e;
	}
	
	/**
	 * @return la cantSedes del bar
	 */
	public String getProducto_proveedor() 
	{
		return producto_proveedor;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setProducto_proveedor(String producto_proveedor) 
	{
		this.producto_proveedor = producto_proveedor;
	}

	/**
	 * @return la cantSedes del bar
	 */
	public String getFecha_entregapedido() 
	{
		return fecha_entregapedido;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setFecha_entregapedido(String fecha_entregapedido) 
	{
		this.fecha_entregapedido = fecha_entregapedido;
	}

	/**
	 * @return la cantSedes del bar
	 */
	public long getCalificacion() 
	{
		return calificacion;
	}

	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setCalificacion(long calificacion) 
	{
		this.calificacion = calificacion;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Proveedor [nit=" + nit + ", rol=" + rol + ", nombre=" + nombre + ", correo_e=" + correo_e
				+ ", producto_proveedor=" + producto_proveedor + ", fecha_entregapedido=" + fecha_entregapedido + ", calificacion=" + calificacion +"]";
	}
	

}
