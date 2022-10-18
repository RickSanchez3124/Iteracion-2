package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.*;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUsuario {

	private final static String SQL = PersistenciaParranderos.SQL;

	private PersistenciaParranderos pp;


    public SQLUsuario (PersistenciaParranderos pp)
    {
        this.pp = pp;
    }

    public long adicionarUsuario (PersistenceManager pm, long numDocumento, String nombre, String rol, String tipoDocumento, String correo, String keyWord)
    {
        //Hace falta poner la función de persistencia
        Query q = pm.newQuery(SQL, "INSERT INTO" + pp.darTablaUsuario() + "(num_doc, nombre, correo_e, rol, tipo_doc, key_word) values (?,?,?,?,?,?)");
        q.setParameters(numDocumento,nombre,correo,rol, tipoDocumento,keyWord);
        return (long) q.executeUnique();
    }


    public long eliminarUsuarioPorDocumento(PersistenceManager pm, long documentoUsuario){
        Query q = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaUsuario() + "WHERE num_doc = ?");
        q.setParameters(documentoUsuario);
        return(long) q.executeUnique();
    }

    public Usuario darUsuarioPorDocumento(PersistenceManager pm, long documentoUsuario)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM" + pp.darTablaUsuario() +"WHERE num_doc = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(documentoUsuario);
        return (Usuario) q.executeUnique();
    }
    
    public List<Usuario> darUsuariosPorDocumento (PersistenceManager pm, long documento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario()+ "WHERE num_doc = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(documento);
        return (List<Usuario>) q.executeList();
    }

    public List<Usuario> darUsuarios (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() );
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }
}
