package uniandes.isis2304.parranderos.negocio;

public class Categoria {
    private String tipo;
    private String nombreProducto;

    public Categoria()
    {
        this.tipo = "";
        this.nombreProducto = "";
    }

    public Categoria(String tipo, String nombreProducto)
    {
        this.tipo = tipo;
        this.nombreProducto = nombreProducto;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
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
