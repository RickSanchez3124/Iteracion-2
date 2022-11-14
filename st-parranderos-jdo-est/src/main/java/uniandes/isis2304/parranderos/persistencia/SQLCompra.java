package uniandes.isis2304.parranderos.persistencia;
import java.sql.Date;
import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCompra {

<<<<<<< HEAD
    private final static String SQL = PersistenciaSuperAndes.SQL;
    private PersistenciaSuperAndes pp;

    public SQLCompra(PersistenciaSuperAndes pp )
=======
    private final static String SQL = PersistenciaSuperandes.SQL;
    private PersistenciaSuperandes pp;

    public SQLCompra(PersistenciaSuperandes pp )
>>>>>>> 2ff2856857790ccd3194d748f26a43b279dc8df5
    {
        this.pp = pp;
    }

    public long adicionarCompra(PersistenceManager pm, long id, Date fecha, String nombreProducto)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCompra() + "(id, fecha, nombreProducto) values (?,?,?)");
        q.setParameters(id,fecha,nombreProducto);
        return (long) q.executeUnique();
    }

    public long eliminarCompraPorId(PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompra() + "WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Compra darCompraPorId (PersistenceManager pm, long id)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra() + "WHERE id = ?");
        q.setResultClass(Compra.class);
        q.setParameters(id);
        return (Compra) q.executeUnique();
    }

    public List<Compra> darCompras (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra());
        q.setResultClass(Compra.class);
        return (List<Compra>) q.executeList();
    }

    public List<Compra> darComprasIgual(PersistenceManager pm, Date fecha, String nombreProducto )
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra () + " WHERE fechaFactura = ? AND nombreProducto=?");
        q.setResultClass(Compra.class);
        q.setParameters(fecha, nombreProducto);
        return (List<Compra>) q.executeUnique();
    }

}
