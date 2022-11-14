package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.*;

public class SQLFactura {
    private final static String SQL = PersistenciaSuperAndes.SQL;
    private PersistenciaSuperAndes pp;

    public SQLFactura(PersistenciaSuperAndes pp )
    {
        this.pp = pp;
    }

    public Date adicionarFactura(PersistenceManager pm, Date fecha, String nombreComprador)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFactura() + "(fecha, nombreComprador) values (?,?)");
        q.setParameters(fecha, nombreComprador);
        return (Date) q.executeUnique();
    }

    public Date eliminarFacturaPorFecha(PersistenceManager pm, Date fecha)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaFactura() + "WHERE fecha = ?");
        q.setParameters(fecha);
        return (Date) q.executeUnique();
    }

    public Factura darFacturaPorFecha(PersistenceManager pm, Date fecha)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaFactura() + "WHERE fecha = ?");
        q.setResultClass(Factura.class);
        q.setParameters(fecha);
        return(Factura) q.executeUnique();
    }

    public List<Factura> darFacturasPorFecha(PersistenceManager pm, Date fecha)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaFactura() + "WHERE fecha = ?");
        q.setResultClass(Factura.class);
        q.setParameters(fecha);
        return(List<Factura>) q.executeList();
    }

    public List<Factura> darFacturas(PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaFactura() );
        q.setResultClass(Factura.class);
        return(List<Factura>) q.executeList();
    }
    

}