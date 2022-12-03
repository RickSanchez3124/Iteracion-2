package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;

public class SQLCarrito {

    private final static String SQL = PersistenciaSuperandes.SQL;
    private PersistenciaSuperandes pp;

    public SQLCarrito (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}
    
    public long adicionarCarrito (PersistenceManager pm, String docUsuario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito() + "(tipo, nombreProducto) values (?, ?)");
        q.setParameters(docUsuario);
        return (long) q.executeUnique();
	}
}
