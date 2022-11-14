package uniandes.isis2304.parranderos.persistencia;
import java.sql.Date;
import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCompra {

    private final static String SQL = PersistenciaSuperandes.SQL;
    private PersistenciaSuperandes pp;

    public SQLCompra(PersistenciaSuperandes pp )
    {
        this.pp = pp;
    }

    public long adicionarCompra(PersistenceManager pm, long idCompra, Date fechaFactura, String nombreProducto)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCompra() + "(id, fecha_factura, nombre_prodcuto) values (?,?,?)");
        q.setParameters(idCompra,fechaFactura,nombreProducto);
        return (long) q.executeUnique();
    }

    public long eliminarCompraPorId(PersistenceManager pm, long idCompra)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompra() + "WHERE id = ?");
        q.setResultClass(Compra.class);
        q.setParameters(idCompra);
        return (long) q.executeUnique();
    }

    public Compra darCompraPorId (PersistenceManager pm, long idCompra)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra() + "WHERE id = ?");
        q.setResultClass(Compra.class);
        q.setParameters(idCompra);
        return (Compra) q.executeUnique();
    }

    public List<Compra> darCompras (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra());
        q.setResultClass(Compra.class);
        return (List<Compra>) q.executeList();
    }

}
