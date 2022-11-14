package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.*;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

public class SQLPromocion {


    private final static String SQL = PersistenciaSuperandes.SQL;
    private PersistenciaSuperandes pp;
    
    public SQLPromocion (PersistenciaSuperandes pp)
    {
        this.pp = pp;
    }

    public long adicionarPromocion (PersistenceManager pm, long tipo)
    {
        Query q  = pm.newQuery(SQL, "INSERT INTO "+ pp.darTablaPromocion() + "(tipo) values (?)");
        q.setParameters(tipo);
        return(long) q.executeUnique();
    }

    public long eliminarPromocion (PersistenceManager pm, long tipo)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaPromocion() + "WHERE tipo = ?");
        q.setParameters(tipo);
        return (long) q.executeUnique();
    }

    public Promocion darPromocion(PersistenceManager pm, long tipo)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion() + "WHERE tipo = ?");
        q.setResultClass(Promocion.class);
        q.setParameters(tipo);
        return (Promocion) q.executeUnique();
    }

    public List<Promocion> darPromociones (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaPromocion());
        q.setResultClass(Promocion.class);
        return (List<Promocion>) q.executeList();
    }
}