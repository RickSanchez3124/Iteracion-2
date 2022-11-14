package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import oracle.core.lmx.CoreException;


public class Rol implements VORol {

    private String nombre;
    private String descripcion;
    private Integer documento;

    public Rol()
    {
        this.nombre = "";
        this.descripcion = "";
        this.documento = 0;

    }

    public Rol(String nombre, String descripcion, String documento)
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

    public String getDocumento()
    {
        return documento;
    }

    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

}
