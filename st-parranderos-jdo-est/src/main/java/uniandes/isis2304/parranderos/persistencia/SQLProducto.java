package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLProducto {

    private final static String SQL = PersistenciaParranderos.SQL;

    private PersistenciaParranderos pp;

    public SQLProducto(PersistenciaParranderos pp)
    {
        this.pp= pp;
    }

    public String adicionarProducto (PersistenceManager pm, String nombre, String marca, long precio, String presentacion, long precio_unimed, long cantidadP, String unimed, String esp_empacado, String codBarra, long promocion)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + "(NOMBRE,MARCA,PRECIO_U,PRESENTACION, PRECIO_UNIMED,CANTIDAD_PRESENTE, UNIMED, ESP_EMAPCADO, COD_BARRA, PROMOCION) value (?,?,?,?,?,?,?,?,?,?)");
        q.setParameters(nombre,marca,precio,presentacion,precio_unimed,cantidadP,unimed,esp_empacado,codBarra,promocion);
        return (String) q.executeUnique();
    }
    
    public String eliminarProductoPorNombre (PersistenceManager pm, String nombre)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM" + "WHERE nombre =?");
        q.setParameters(nombre);
        return (String) q.executeUnique();
    }

    public Producto darProductoPorNombre (PersistenceManager pm, String nombre)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + "WHERE NOMBRE = ?");
        q.setResultClass(Producto.class);
        q.setParameters(nombre);
        return (Producto) q.executeUnique();
    }

    public List<Producto> darProductos (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM");
        q.setResultClass(Producto.class);
        return (List<Producto>) q.executeList();
    }
}
