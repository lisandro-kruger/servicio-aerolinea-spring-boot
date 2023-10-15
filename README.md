## ID: S01
### Servicio: Clientes
#### Descripción:
Establece/Actualiza/Retorna los datos de cliente
- Dni
- Nombre
- Apellido
- Domicilio
- e-mail
- Fecha de nacimiento
- Número pasaporte
- Fecha vencimiento pasaporte

No se podrá actualizar el dni del cliente.

No podrá haber dos clientes con el mismo dni

#### Operaciones:
GET / POST / PUT /DELETE (Servicio Entidad)

## ID: S02
### Servicio: Vuelos
#### Descripción:
Establece/Actualiza/Retorna los datos de un vuelo 
- Nro
- Fecha / hora
- Nro Filas
- Nro asientos por fila
- Tipo de vuelo
- Destino
- Origen (por el momento será siempre aeropuerto sauce viejo ya que solo consideraremos la venta de pasajes que partan desde este lugar)
- Estado (registrado  / reprogramado / cancelado)

El estado es autocalculado por el sistema, no puede ser establecido por el usuario.

No podrá haber dos vuelos con el mismo nro

Una vez registrado, solo se permitirá cambiar la fecha y hora del mismo (lo cual pasa el vuelo al estado reprogramado) o eliminar el mismo (lo cual pasa el vuelo al estado cancelado)

Tanto la reprogramación como la cancelación de un vuelo dispararía la notificación automática del evento a todos los pasajeros aunque por simplicidad, no se pide implementar el servicio de alertas.

#### Operaciones:
GET / POST / PUT /DELETE (Servicio Entidad)

## ID: S03
### Servicio: Pasaje
#### Descripción:
Emite/Consulta un pasaje aéreo
- Dni
- Nro vuelo
- Nro asiento 

Para la emisión se deberán considerar las siguientes validaciones

- Que el cliente exista y cuente con los datos necesarios para el tipo de vuelo correspondiente (datos básicos para vuelo nacional y pasaporte para vuelo internacional)
- Que el vuelo exista, corresponda a un vuelo futuro (no pasado) y que el asiento elegido esté disponible 

Retorna un error o el pasaje emitido, con los siguientes datos:

- Nro de pasaje
- Dni del cliente + link (HATEOAS) para consultar datos del cliente
- Nro de vuelo + link (HATEOAS) para consultar datos del vuelo Para implementar este servicio hará uso del servicio S04-Costo de Pasaje para el calculo del importe a abonar  (en caso de que el grupo no implemente dicho servicio, simplemente puede implementar un método

GetCosto, el cual devolverá siempre un importe fijo, a modo de mock method) Por simplicidad, no se implementará la llamada al servicio de facturación electrónica provisto por AFIP

#### Operaciones:
POST / GET  (Servicio Tarea)

## ID: S04
### Servicio: Costo de Pasaje
#### Descripción:
Devuelve el importe correspondiente al costo total actual de un pasaje aéreo. Este servicio recibir como parámetros - Nro de vuelo - Dni de quien requiere realiza la consulta Por simplicidad consideraremos implementaremos mock method para la consulta de tasa aeroportuaria y para la cotización del dólar utilizaremos el siguiente servicio: https://www.dolarsi.com/api/api.php?type=valoresprincipales  Tomaremos como referencia la cotización correspondiente a "Dólar oficial" 

Cada consulta de costos no solo calculará y devolverá el importe del pasaje consultado, sino que deberá almacenar en la base de datos tanto los parámetros de la consulta como el importe respondido, a modo de registro de “presupuestos entregados”. La consulta devolverá :

- Importe pasaje
- Dni del cliente + link (HATEOAS) para consultar datos del cliente
- Nro de vuelo + link (HATEOAS) para consultar datos del vuelo
- Cotización del dólar + link (HATEOAS) al servicio DolarSi

#### Operaciones:
GET (Servicio Tarea)
