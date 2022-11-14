package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.*;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLRol {

	private final static String SQL = PersistenciaSuperandes.SQL;

	private PersistenciaSuperandes pp;


    public SQLRol (PersistenciaSuperandes pp)
    {
        this.pp = pp;
    }


    public long adicionarRol (PersistenceManager pm, String nombre, String descripcion, long documento)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO" + pp.darTablaRol() + "(nombre, descripcion, documento) values (?,?,?)");
        q.setParameters(nombre,descripcion, documento);
        return (long) q.executeUnique();
    }


    public long eliminarRolPorNombre(PersistenceManager pm, String nombre){
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaRol() + "WHERE nombre = ?");
        q.setParameters(nombre);
        return(long) q.executeUnique();
    }

    public Rol darRolPorNombre(PersistenceManager pm, String nombre)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaRol() +"WHERE nombre = ?");
        q.setResultClass(Rol.class);
        q.setParameters(nombre);
        return (Rol) q.executeUnique();
    }
    
    public List<Rol> darRoles (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol() );
        q.setResultClass(Rol.class);
        return (List<Rol>) q.executeList();
    }

    public Rol darRolPorDocumento(PersistenceManager pm, long documento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRol () + " WHERE documento = ?");
        q.setResultClass(Rol.class);
        q.setParameters(documento);
        return (Rol) q.executeUnique();
    }

}