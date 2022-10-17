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
public class Pedido implements VOPedido
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private String fecha_e;
	
	/**
	 * El nombre del bar
	 */
	private long cant_productos;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private long calificacion_productos;
	
	/**
	 * El presupuesto del bar (ALTO, MEDIO, BAJO)
	 */
	private long calificacion_envio;
	
	/**
	 * El número de sedes del bar en la ciudad
	 */
	private String estado;

	/**
	 * El número de sedes del bar en la ciudad
	 */
	private String producto;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Pedido() 
    {
    	this.fecha_e = "";
		this.cant_productos = 0;
		this.calificacion_productos = 0;
		this.calificacion_envio = 0;
		this.estado = "";
		this.producto = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Pedido(String fecha_e, long cant_productos, long calificacion_productos, long calificacion_envio, String estado, String producto) 
    {
    	this.fecha_e = fecha_e;
		this.cant_productos = cant_productos;
		this.calificacion_productos = calificacion_productos;
		this.calificacion_envio = calificacion_envio;
		this.estado = estado;
		this.producto = producto;
	}

    /**
	 * @return El id del bar
	 */
	public String getFecha_e() 
	{
		return fecha_e;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setFecha_e(String fecha_e) 
	{
		this.fecha_e = fecha_e;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public long getCant_productos() 
	{
		return cant_productos;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setCant_productos(long cant_productos) 
	{
		this.cant_productos = cant_productos;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public long getCalificacion_productos() 
	{
		return calificacion_productos;
	}
	
	/**
	 * @param ciudad - La nueva ciudad del bar
	 */
	public void setCalificacion_productos(long calificacion_productos) 
	{
		this.calificacion_productos = calificacion_productos;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public long getCalificacion_envio() 
	{
		return calificacion_envio;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setCalificacion_envio(long calificacion_envio) 
	{
		this.calificacion_envio = calificacion_envio;
	}
	
	/**
	 * @return la cantSedes del bar
	 */
	public String getEstado() 
	{
		return estado;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}

	/**
	 * @return la cantSedes del bar
	 */
	public String getProducto() 
	{
		return producto;
	}
	
	/**
	 * @param cantSedes - la nueva cantidad de sedes del bar
	 */
	public void setProducto(String producto) 
	{
		this.producto = producto;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Pedido [fecha_e=" + fecha_e + ", cant_productos=" + cant_productos + ", calificacion_productos=" + calificacion_productos + ", caificacion_envio=" + calificacion_envio
				+ ", estado=" + estado + ", producto=" + producto +"]";
	}
	

}
