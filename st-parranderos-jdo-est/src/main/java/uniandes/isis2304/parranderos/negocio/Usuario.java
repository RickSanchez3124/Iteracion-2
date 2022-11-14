package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import oracle.core.lmx.CoreException;


public class Usuario implements VOUsuario {
    
    private long documento;
    private String nombre;
    private String rol;
    private String tipoDocumento;
    private String correo;
    private String key_word;


    public Usuario()
    {
        this.documento=0;
        this.nombre = "";
        this.rol = "";
        this.tipoDocumento="";
        this.correo = "";
        this.key_word="";

    }

    public Usuario(long documento, String nombre, String correo, String tipoDocumento, String rol, String key_word)
    {
        this.documento = documento;
        this.nombre = nombre;
        this.correo= correo;
        this.tipoDocumento = tipoDocumento;
        this.rol = rol;
        this.key_word = key_word;
    }

    public long getDocumento()
    {
        return documento;
    }

    public void setDocumento(long documento)
    {
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

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getTipoDocumento()
    {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol)
    {
        this.rol = rol;
    }

    public String getKeyWord(){
        return key_word;
    }

    public void setKeyWord(String key)
    {
        this.key_word = key;
    }

}
