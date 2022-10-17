package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public class Compra {
    private long id;
    private Date fecha;
    private String nombreProducto;

    public Compra()
    {
        this.id = 0;
        this.fecha = new Date(0);
        this.nombreProducto = "";
    }

    public Compra(long id, Date fecha, String nombreProducto)
    {
        this.id = id;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getNombreProducto()
    {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto)
    {
        this.nombreProducto = nombreProducto;
    }
}
