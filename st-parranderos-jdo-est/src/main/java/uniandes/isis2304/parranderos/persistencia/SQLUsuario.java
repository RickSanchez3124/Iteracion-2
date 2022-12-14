package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.*;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUsuario {

	private final static String SQL = PersistenciaSuperandes.SQL;

	private PersistenciaSuperandes pp;


    public SQLUsuario (PersistenciaSuperandes pp)
    {
        this.pp = pp;
    }

    public long adicionarUsuario (PersistenceManager pm, long Documento, String nombre, String rol, String tipoDocumento, String correo, String key_word)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO" + pp.darTablaUsuario() + "(Documento, nombre, rol, tipoDocumento, correo, key_word) values (?,?,?,?,?,?)");
        q.setParameters(Documento,nombre,rol, tipoDocumento, correo, key_word);
        return (long) q.executeUnique();
    }


    public long eliminarUsuarioPorDocumento(PersistenceManager pm, long Documento){
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaUsuario() + "WHERE NUM_DOC = ?");
        q.setParameters(Documento);
        return(long) q.executeUnique();
    }

    public Usuario darUsuarioPorDocumento(PersistenceManager pm, long Documento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() +" WHERE NUM_DOC = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(Documento);
        return (Usuario) q.executeUnique();
    }
    
    public List<Usuario> darUsuarios (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() );
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }
}