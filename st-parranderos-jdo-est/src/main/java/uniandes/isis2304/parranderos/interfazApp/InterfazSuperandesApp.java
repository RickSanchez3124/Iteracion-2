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

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.parranderos.negocio.superandes;
import uniandes.isis2304.parranderos.negocio.*;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazSuperandesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperandesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "C:/Users/ricar/OneDrive/Escritorio/UNIANDES/Sexto Semestre/SISTRANS/Iteracion-2/st-parranderos-jdo-est/src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "C:/Users/ricar/OneDrive/Escritorio/UNIANDES/Sexto Semestre/SISTRANS/Iteracion-2/st-parranderos-jdo-est/src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private superandes superandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	private String Sesion;

    private List<Long> listaId;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperandesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superandes = new superandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
    public void iniciarSesion()
    {
        try 
        {
            String documento = JOptionPane.showInputDialog (this, "Ingrese su documento", "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);
            String key_word= JOptionPane.showInputDialog (this, "Ingrese la contraseña", "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);

            if (documento != null && key_word != null )
            {
                Integer documento2 = Integer.valueOf(documento);
       
                Usuario usuario = superandes.darUsuarioPorDocumento(documento2);
                Rol rol = superandes.darRolPorDocumento(documento2);
                
                if (key_word.equals(usuario.getKeyWord()))
                {
                    Sesion = rol.getNombre();
                }
                else
                {
                    throw new Exception ("Contraseña incorrecta");
                }
                String resultado = "Se ha iniciado sesión correctamente como: " + Sesion;
                panelDatos.actualizarInterfaz(resultado);
                
                
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        }
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }

	public void añadirRol( )
    {
        try 
        {
            String nombre = JOptionPane.showInputDialog (this, "Nombre del rol que va a añadir?", "Añadir rol", JOptionPane.QUESTION_MESSAGE);
            String descripcion = JOptionPane.showInputDialog (this, "Descripcion rol?", "Adicionar descripción", JOptionPane.QUESTION_MESSAGE);
			String documento = JOptionPane.showInputDialog (this, "Numero de Documento?", "Adicionar numero de documento", JOptionPane.QUESTION_MESSAGE);
			Integer documento2 = Integer.valueOf(documento);

            if (nombre != null && descripcion != null && documento2 != 0)
            {
                VORol tb = superandes.adicionarRol (nombre, descripcion, documento2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo añadir un rol: " + nombre);
                }
                String resultado = "En añadirRol\n\n";
                resultado += "Rol añadido correctamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }
    
    private String listarRoles(List<VORol> lista) 
    {
        String resp = "Los roles existentes son:\n";
        int i = 1;
        for (VORol tb : lista)
        {
            resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
    }

    public void listarRol( )
    {
    	try 
    	{
			List <VORol> lista = superandes.darVORoles();

			String resultado = "En listarRol";
			resultado +=  "\n" + listarRoles(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void eliminarRolPorNombre( )
    {
        try 
        {
            String nombre = JOptionPane.showInputDialog (this, "Nombre del rol?", "Borrar rol de usuario por nombre", JOptionPane.QUESTION_MESSAGE);
            if (nombre != null)
            {
    
                long tbEliminados = superandes.eliminarRolPorNombre (nombre);

                String resultado = "En eliminar rol\n\n";
                resultado += tbEliminados + " Rol eliminado\n";
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }
    
    public void darRolPorNombre( )
    {
        try 
        {
            String nombre = JOptionPane.showInputDialog (this, "Nombre del rol?", "Buscar rol por nombre", JOptionPane.QUESTION_MESSAGE);
            if (nombre != null)
            {
                VORol rol = superandes.darRolPorNombre (nombre);
                String resultado = "En buscar rol por nombre\n\n";
                if (rol != null)
                {
                    resultado += "El rol es: " + rol;
                }
                else
                {
                    resultado += "Un rol con nombre: " + nombre + " NO EXISTE\n";                  
                }
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }

	public void añadirSucursal( )
    {
        try 
        {
            String nombre = JOptionPane.showInputDialog (this, "Nombre de la sucursal?", "Añadir nombre", JOptionPane.QUESTION_MESSAGE);
            String direccion = JOptionPane.showInputDialog (this, "Direccion de la sucursal?", "Añadir direccion", JOptionPane.QUESTION_MESSAGE);
            String ciudad = JOptionPane.showInputDialog (this, "Ciudad de  la sucursal?", "Añador ciudad", JOptionPane.QUESTION_MESSAGE);
			String nombre_supermercado = JOptionPane.showInputDialog (this, "nombre del supermercado al que pertenece?", "Añadir nombre del supermercado", JOptionPane.QUESTION_MESSAGE);
			String id_contenedor = JOptionPane.showInputDialog (this, "Id del contenedor al que pertenece?", "Añadir id del contenedor", JOptionPane.QUESTION_MESSAGE);
			String id_compra = JOptionPane.showInputDialog (this, "Id de la compra al que pertenece?", "Añadir id de la compra", JOptionPane.QUESTION_MESSAGE);
			String documento_usuario = JOptionPane.showInputDialog (this, "Documento del usuario al que pertenece?", "Añadir documento del usuario", JOptionPane.QUESTION_MESSAGE);
            long id_contenedor2 = Long.valueOf(id_contenedor);
			long id_compra2 = Long.valueOf(id_compra);
			long documento_usuario2 = Long.valueOf(documento_usuario);
            if (nombre != null && direccion != null && ciudad != null && nombre_supermercado != null && id_contenedor2 != 0 && id_compra2 != 0 && documento_usuario2 != 0)
            {
                VOSucursal tb = superandes.adicionarSucursal (nombre, direccion, ciudad, nombre_supermercado, id_contenedor2, id_compra2, documento_usuario2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo añadir una sucursal");
                }
                String resultado = "En adicionarSucursal\n\n";
                resultado += "Sucursal añadida exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }


	public void añadirUsuario( )
    {
        try 
        {
            String documento = JOptionPane.showInputDialog (this, "Documento?", "Adicionar documento", JOptionPane.QUESTION_MESSAGE);
            String nombre = JOptionPane.showInputDialog (this, "Nombre?", "Adicionar nombre", JOptionPane.QUESTION_MESSAGE);
            String rol = JOptionPane.showInputDialog (this, "Rol?", "Adicionar rol", JOptionPane.QUESTION_MESSAGE);
            String tipoDocumento = JOptionPane.showInputDialog (this, "Tipo documento?", "Adicionar tipo de documento", JOptionPane.QUESTION_MESSAGE);
            String correo = JOptionPane.showInputDialog (this, "Correo?", "Adicionar correo", JOptionPane.QUESTION_MESSAGE);
            String key_word = JOptionPane.showInputDialog (this, "Contraseña?", "Adicionar contraseña", JOptionPane.QUESTION_MESSAGE);
            long documento2 = Long.valueOf(documento);
            
            if (documento2 != 0 && nombre != null && rol != null && tipoDocumento != null && correo != null && key_word != null)
            {
                VOUsuario tb = superandes.adicionarUsuario (documento2, nombre, rol, tipoDocumento, correo, key_word);
                if (tb == null)
                {
                    throw new Exception ("No se pudo añadir un usuario");
                }
                String resultado = "En adicionarUsuario\n\n";
                resultado += "Usuario añadido exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }

	public void adicionarContenedor( )
    {
        try 
        {
            String id = JOptionPane.showInputDialog (this, "Id?", "Adicionar id", JOptionPane.QUESTION_MESSAGE);
            String tipo = JOptionPane.showInputDialog (this, "Tipo?", "Adicionar tipo", JOptionPane.QUESTION_MESSAGE);
            String capacidad = JOptionPane.showInputDialog (this, "Capacidad?", "Adicionar capacidad", JOptionPane.QUESTION_MESSAGE);
            String categoria = JOptionPane.showInputDialog (this, "Categoria?", "Adicionar categoria", JOptionPane.QUESTION_MESSAGE);
            long id2 = Long.valueOf(id);
			long capacidad2 = Long.valueOf(capacidad);
            
            if (id != null && tipo != null && capacidad2 != 0 && categoria != null)
            {
                VOContenedor tb = superandes.adicionarContenedor (id2, tipo, capacidad2, categoria);
                if (tb == null)
                {
                    throw new Exception ("No se pudo crear un contenedor nuevo");
                }
                String resultado = "En adicionarContenedor\n\n";
                resultado += "Contenedor añadido exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }


	public void adicionarProveedor( )
    {
        try 
        {
            String nit = JOptionPane.showInputDialog (this, "Nit?", "Adicionar nit", JOptionPane.QUESTION_MESSAGE);
            String rol = JOptionPane.showInputDialog (this, "Rol?", "Adicionar rol", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog (this, "Nombre?", "Adicionar nombre", JOptionPane.QUESTION_MESSAGE);
			String correo_e = JOptionPane.showInputDialog (this, "Correo_e?", "Adicionar correo_e", JOptionPane.QUESTION_MESSAGE);
			String producto_proveedor = JOptionPane.showInputDialog (this, "Producto del proveedor?", "Adicionar producto del proveedor", JOptionPane.QUESTION_MESSAGE);
			String fecha_entregapedido = JOptionPane.showInputDialog (this, "Fecha de entrega del pedido?", "Adicionar fecha de entrega del pedido", JOptionPane.QUESTION_MESSAGE);
			String calificacion = JOptionPane.showInputDialog (this, "Calificacion?", "Adicionar calificacion", JOptionPane.QUESTION_MESSAGE);
            long nit2 = Long.valueOf(nit);
			long calificacion2 = Long.valueOf(calificacion);
            if (nit2 != 0 && rol != null && nombre != null && correo_e != null && producto_proveedor != null && fecha_entregapedido != null && calificacion != null)
            {
                VOProveedor tb = superandes.adicionarProveedor (nit2, rol, nombre, correo_e, producto_proveedor, fecha_entregapedido, calificacion2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo añadir un proveedor");
                }
                String resultado = "En adicionarProveedor\n\n";
                resultado += "Proveedor añadido exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }

	public void adicionarProducto( )
    {
        try 
        {
            String nombre = JOptionPane.showInputDialog (this, "Nombre?", "Adicionar nombre", JOptionPane.QUESTION_MESSAGE);
            String marca = JOptionPane.showInputDialog (this, "Marca?", "Adicionar marca", JOptionPane.QUESTION_MESSAGE);
            String precioU = JOptionPane.showInputDialog (this, "Precio unidad?", "Adicionar precio por unidad", JOptionPane.QUESTION_MESSAGE);
            String presentacion = JOptionPane.showInputDialog (this, "Presentacion?", "Adicionar presentacion", JOptionPane.QUESTION_MESSAGE);
            String precioUnimed = JOptionPane.showInputDialog (this, "Presentación por unidad de medida?", "Adicionar presentacion por unidad de medida", JOptionPane.QUESTION_MESSAGE);
            String cantidadPresente = JOptionPane.showInputDialog (this, "Cantidad presente?", "Adicionar cantidad presente", JOptionPane.QUESTION_MESSAGE);
            String unimed = JOptionPane.showInputDialog (this, "Unidad de medida?", "Adicionar unidad de medida", JOptionPane.QUESTION_MESSAGE);
            String espEmpacado = JOptionPane.showInputDialog (this, "Especificacion de empacada?", "Adicionar especificacion de empacado", JOptionPane.QUESTION_MESSAGE);
            String codBarra = JOptionPane.showInputDialog (this, "Codigo de barras?", "Adicionar codigo de barras", JOptionPane.QUESTION_MESSAGE);
            String promocion = JOptionPane.showInputDialog (this, "Promocion?", "Adicionar promocion", JOptionPane.QUESTION_MESSAGE);
            long precioU2 = Long.valueOf(precioU);
            long precioUnimed2 = Long.valueOf(precioUnimed);
			long cantidadPresente2 = Long.valueOf(cantidadPresente);
			long promocion2 = Long.valueOf(promocion);

            if (nombre != null && marca != null && precioU2 != 0 && presentacion != null && precioUnimed2 != 0 && cantidadPresente2 != 0 && unimed != null && espEmpacado != null && codBarra != null && promocion2 != 0)
            {
                VOProducto tb = superandes.adicionarProducto(nombre, marca, precioU2, presentacion, precioUnimed2, cantidadPresente2, unimed, espEmpacado, codBarra, promocion2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo crear un producto nuevo");
                }
                String resultado = "En adicionarProducto\n\n";
                resultado += "Producto añadido exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }

	public void adicionarComprador( )
    {
        try 
        {
            if (Sesion.equals("Gerente sucursal"))
            {
            String tipo = JOptionPane.showInputDialog (this, "Tipo?", "Adicionar tipo", JOptionPane.QUESTION_MESSAGE);
            String nombre_comprador = JOptionPane.showInputDialog (this, "Nombre del comprador?", "Adicionar nombre del comprador", JOptionPane.QUESTION_MESSAGE);
            String id_doc = JOptionPane.showInputDialog (this, "Id del documento?", "Adicionar id del documento", JOptionPane.QUESTION_MESSAGE);
            long id_doc2 = Long.valueOf(id_doc);

            if (tipo != null && nombre_comprador != null && id_doc != null)
            {
                VOComprador tb = superandes.adicionarComprador (tipo, nombre_comprador, id_doc2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo añadir un comprador nuevo");
                }
                String resultado = "En adicionarCliente\n\n";
                resultado += "Comprador añadido exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        }
            else
            {
                JOptionPane.showMessageDialog (this, "Debe iniciar sesión como Gerente sucursal para hacer esta funcionalidad", "Error de Usuario", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }
	
	public void adicionarPromocion( )
    {
        try 
        {
            if (Sesion.equals("Gerente sucursal"))
            {
            String tipo = JOptionPane.showInputDialog (this, "Tipo?", "Adicionar tipo", JOptionPane.QUESTION_MESSAGE);
            long tipo2 = Long.valueOf(tipo);
            
            if (tipo != null )
            {
                VOPromocion tb = superandes.adicionarPromocion (tipo2);
                if (tb == null)
                {
                    throw new Exception ("No se pudo crear una promoción nuevo");
                }
                String resultado = "En adicionarPromocion\n\n";
                resultado += "Promoción añadida exitosamente: " + tb;
                resultado += "\n Operación terminada";
                panelDatos.actualizarInterfaz(resultado);
            }
            else
            {
                panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
            }
        } 
            else {
                JOptionPane.showMessageDialog (this, "Debe iniciar sesión como Gerente sucursal para acceder a esta sucursal", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

            }
        }
        catch (Exception e) 
        {
//          e.printStackTrace();
            String resultado = generarMensajeError(e);
            panelDatos.actualizarInterfaz(resultado);
        }
    }


	public void adicionarPedido()
    {
        try{
            if (Sesion.equals("Gerente sucursal"))
            {
            String fecha_e = JOptionPane.showInputDialog(this, "Fecha?", "Adicionar fecha", JOptionPane.QUESTION_MESSAGE);
            String cant_productos = JOptionPane.showInputDialog (this, "Cantidad de productos?", "Adicionar cantidad de productos", JOptionPane.QUESTION_MESSAGE);
            String calificacion_productos = JOptionPane.showInputDialog (this, "Calificacion de los productos?", "Adicionar calificacion de los productos", JOptionPane.QUESTION_MESSAGE);
            String calificacion_envio = JOptionPane.showInputDialog (this, "Calificacion del envio?", "Adicionar calificacion del envio", JOptionPane.QUESTION_MESSAGE);
            String estado = JOptionPane.showInputDialog (this, "Estado?", "Adicionar estado", JOptionPane.QUESTION_MESSAGE);
            String producto= JOptionPane.showInputDialog (this, "Producto?", "Adicionar producto", JOptionPane.QUESTION_MESSAGE);
			long cant_productos2 = Long.valueOf(cant_productos);
			long calificacion_productos2 = Long.valueOf(calificacion_productos);
			long calificacion_envio2 = Long.valueOf(calificacion_envio);

                if (fecha_e != null && cant_productos2 != 0 && calificacion_productos != null && calificacion_envio != null && estado != null && producto != null)
                {
                    VOPedido tb = superandes.adicionarPedido(fecha_e,  cant_productos2,  calificacion_productos2,  calificacion_envio2,  estado,  producto);
                    if (tb==null)
                    {
                        throw new Exception ("No se pudo crear una orden de pedido nueva");
                    }
                    String resultado = "En adicionarPedido\n\n";
                    resultado += "Pedido añadido exitosamente: " + tb;
                    resultado += "\n Operación terminada";
                    panelDatos.actualizarInterfaz(resultado);
                }
                else
                {
                    panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
                }
            }
			else
			{
				JOptionPane.showMessageDialog (this, "Debe iniciar sesión como Gerente sucursal para acceder a esta función", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

			}
        }
            catch (Exception e) 
            {
    //          e.printStackTrace();
                String resultado = generarMensajeError(e);
                panelDatos.actualizarInterfaz(resultado);
            }
        

        
    }

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superandes.limpiarParranderos();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperandesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperandesApp interfaz = new InterfazSuperandesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
