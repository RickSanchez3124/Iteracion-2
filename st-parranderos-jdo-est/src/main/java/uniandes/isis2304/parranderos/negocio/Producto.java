package uniandes.isis2304.parranderos.negocio;
import java.util.List;

public class Producto {

    private String nombre;
    private String marca;
    private long precioU;
    private String presentacion;
    private long precioUnimed;
    private long cantidadPresente;
    private String unimed;
    private String espEmpacado;
    private String codBarra;
    private long promocion;


    public Producto()
    {
        this.nombre = "";
        this.marca = "";
        this.precioU = 0;
        this.presentacion = "";
        this.precioUnimed = 0;
        this.cantidadPresente = 0;
        this.unimed = "";
        this.espEmpacado ="";
        this.codBarra="";
        this.promocion = 0;
    }

    public Producto (String nombre, String marca, long precioU, String presentacion, long precio_unimed, long cantidadPresente, String unimed, String espEmpacado, String codBarra, long promocion)
    {
        this.nombre = nombre;
        this.marca = marca;
        this.precioU = precioU;
        this.presentacion = presentacion;
        this.precioUnimed = precio_unimed;
        this.cantidadPresente = cantidadPresente;
        this.unimed = unimed;
        this.espEmpacado = espEmpacado;
        this.codBarra = codBarra;
        this.promocion = promocion;

    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca= marca;
    }

    public long getPrecioUnidad()
    {
        return precioU;
    }

    public void setPrecioUnidad(long precioUnidad)
    {
        this.precioU = precioUnidad;
    }

    public String getPresentacion()
    {
        return presentacion;
    }

    public void setPresentacion(String presentacion)
    {
        this.presentacion = presentacion;
    }

    public long getPrecioUniMed()
    {
        return precioUnimed;
    }

    public void setPrecioUniMed(long precioUniMed)
    {
        this.precioUnimed = precioUniMed;
    }

    public long getCantidadPresente()
    {   
        return cantidadPresente;
    }

    public void setCantidadPresente(long cantidadPresente)
    {
        this.cantidadPresente = cantidadPresente;
    }

    public String getUnimed()
    {
        return unimed;
    }

    public void setUnimed(String unimed)
    {
        this.unimed = unimed;
    }

    public String getEspEmpacado()
    {
        return espEmpacado;
    }

    public void setEspEmpacado(String espEmpacado)
    {
        this.espEmpacado = espEmpacado;
    }

    public String getCodBarras()
    {
        return codBarra;
    }

    public void setCodBarras(String codBarra)
    {
        this.codBarra = codBarra;
    }

    public long getPromocion()
    {
        return promocion;
    }

    public void setPromocion(long promocion)
    {
        this.promocion = promocion;
    }
}
