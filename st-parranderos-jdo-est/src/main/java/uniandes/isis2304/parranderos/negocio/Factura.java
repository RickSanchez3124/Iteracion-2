package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public class Factura {
    
    private Date fecha;
    private String nombreComprador;

    public Factura()
    {
        this.fecha = new Date(0);
        this.nombreComprador ="";
    }

    public Factura (Date fecha, String nombre)
    {
        this.fecha = fecha;
        this.nombreComprador = nombre;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getNombreComprador()
    {
        return nombreComprador;
    }

    public void setNombreComprador(String nombre)
    {
        this.nombreComprador = nombre;
    }
}
