package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Promocion {

    private long tipo;


    public Promocion()
    {
        this.tipo = 0;
    }

    public Promocion(long tipo)
    {
        this.tipo = tipo;
    }

    public long getTipo()
    {
        return tipo;
    }

    public void setTipo(long tipo)
    {
        this.tipo = tipo;
    }
    
}
