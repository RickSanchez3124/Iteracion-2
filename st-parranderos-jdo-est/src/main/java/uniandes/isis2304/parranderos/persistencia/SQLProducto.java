package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLProducto {

    private final static String SQL = PersistenciaSuperandes.SQL;

    private PersistenciaSuperandes pp;

    public SQLProducto(PersistenciaSuperandes pp)
    {
        this.pp= pp;
    }

    public long adicionarProducto (PersistenceManager pm, String nombre, String marca, long precioU, String presentacion, long precioUnimed, long cantidadPresente, String unimed, String espEmpacado, String codBarra, long promocion)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto() + "(nombre,marca,precioU,presentacion, precioUnimed,cantidadPresente, unimed, espEmpacado, codBarra, promocion) value (?,?,?,?,?,?,?,?,?,?)");
        q.setParameters(nombre,marca,precioU,presentacion,precioUnimed,cantidadPresente,unimed,espEmpacado,codBarra,promocion);
        return (long) q.executeUnique();
    }
    
    public long eliminarProductoPorCodBarras (PersistenceManager pm, String codBarra)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaProducto() + "WHERE codBarra =?");
        q.setParameters(codBarra);
        return (long) q.executeUnique();
    }

    public Producto darProductoPorCodBarras (PersistenceManager pm, String codBarra)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaProducto() + "WHERE codBarra = ?");
        q.setResultClass(Producto.class);
        q.setParameters(codBarra);
        return (Producto) q.executeUnique();
    }

    public List<Producto> darProductos (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaProducto());
        q.setResultClass(Producto.class);
        return (List<Producto>) q.executeList();
    }

    public List<Contenedor> darContenedoresPorProducto(PersistenceManager pm, long idContenedor)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContenedor() + " WHERE id = ?");
        q.setResultClass(Contenedor.class);
        q.setParameters(idContenedor);
        return (List<Contenedor>) q.executeUnique();
    }

    public long actualizarPrecioUnidad (PersistenceManager pm, Integer nuevoPrecio, String codBarra)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProducto () + " SET precioU = ?  WHERE codBarra = ?");
        q.setParameters(nuevoPrecio, codBarra);
        return (long) q.executeUnique();
	}
}
