package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.*;
import org.apache.*;
import org.apache.log4j.Logger;

import com.google.gson.*;
import uniandes.isis2304.parranderos.persistencia.*;

public class superandes {

    private static Logger log = Logger.getLogger(superandes.class.getName());
    private PersistenciaSuperandes pp;

    public superandes(){
        pp = PersistenciaSuperandes.getInstance();
    }

    public superandes(JsonObject tableConfig){
        pp = PersistenciaSuperandes.getInstance(tableConfig);
    }

    /**Cierra la conexión con la base de datos */
    public void cerrarPersistencia(){
        pp.cerrarUnidadPersistencia();
    }

    /** Método para limpiar la base de datos */

    public long [] limpiarSuperAndes(){
        log.info ("Limpiando la base de datos de superandes");
        long[] borrados = pp.limpiarSuperAndes();
        return borrados;
    }
}
