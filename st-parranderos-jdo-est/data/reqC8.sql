select A_FACTURA.NOMBRE, count(all NOMBRE) conteoNombre, count(month from FECHA) meses
from A_FACTURA,(select A_COMPRA.FECHA_FACTURA 
                from A_COMPRA,(select ID_COMPRA 
                                from A_SUCURSAL) SUCURSAL 
                where A_COMPRA.ID = SUCURSAL.ID_COMPRA
                group by A_FACTURA.NOMBRE) COMPRA
where A_FACTURA.FECHA = COMPRA.a_compra.fecha_factura and meses >= 2 
group by A_FACTURA.NOMBRE,conteoNombre, meses;