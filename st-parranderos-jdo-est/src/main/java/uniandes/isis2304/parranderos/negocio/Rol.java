package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import oracle.core.lmx.CoreException;


public class Rol implements VORol {

    private String nombre;
    private String descripcion;
    private long documento;

    public Rol()
    {
        this.nombre = "";
        this.descripcion = "";
        this.documento = 0;

    }

<<<<<<< HEAD
    public Rol(String nombre, String descripcion, Integer documento)
=======
    public Rol(String nombre, String descripcion, long documento)
>>>>>>> 09499b8a99999809a412b4ed28dcf1c457f53370
    {
        this.nombre = nombre;
        this.descripcion= descripcion;
        this.documento = documento;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

<<<<<<< HEAD
    public Integer getDocumento()
=======
    public long getDocumento()
>>>>>>> 09499b8a99999809a412b4ed28dcf1c457f53370
    {
        return documento;
    }

<<<<<<< HEAD
    public void setDocumento(Integer documento)
=======
    public void setDocumento(long documento)
>>>>>>> 09499b8a99999809a412b4ed28dcf1c457f53370
    {
        this.documento = documento;
    }

}
