package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;
import uniandes.isis2304.parranderos.negocio.*;

public class SQLCategoria {
    private final static String SQL = PersistenciaSuperandes.SQL;
    private PersistenciaSuperandes pp;

    public SQLCategoria (PersistenciaSuperandes pp)
	{
		this.pp = pp;
	}

    public String adicionarCategoria (PersistenceManager pm, String  tipo, String nombreProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCategoria() + "(tipo, nombreProducto) values (?, ?)");
        q.setParameters(tipo, nombreProducto);
        return (String) q.executeUnique();
	}

    public String eliminarCategoriaPorTipo (PersistenceManager pm, String  tipo)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria() +  "WHERE tipo = ?");
        q.setParameters(tipo);
        return (String) q.executeUnique();
    }

    public Categoria darCategoriaPorTipo (PersistenceManager pm, String  tipo)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria() +  "WHERE tipo = ?");
        q.setResultClass(Categoria.class);
        q.setParameters(tipo);
        return (Categoria) q.executeUnique();
    }

    public List<Categoria> darCategoriasPorTipo (PersistenceManager pm, String  tipo)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria() +  "WHERE tipo = ?");
        q.setResultClass(Categoria.class);
        q.setParameters(tipo);
        return (List<Categoria>) q.executeList();
    }

    public List<Categoria> darCategorias (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCategoria() +  "WHERE tipo = ?");
        q.setResultClass(Categoria.class);
        return (List<Categoria>) q.executeList();
    }

}
