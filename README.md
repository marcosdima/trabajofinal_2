# Trabajo Final
Trabajo final de POO, Cursado el 2do cuatrimestre de 2022.

## Datos
+ Alumno: Di Matteo Brambilla, Marcos.
+ Legajo: 166298
  
## Juego: Black Jack

### Ejecución
Para iniciar el juego hay que ejecutar **'AppServidor'**, que se encuentra dentro del paquete **'ar.edu.unlu.poo.trabajofinal.rmi'**. Luego se dirige a la clase **'AppCliente'** (mismo paquete) e ingresa la información necesaria para que se cargue correctamente en el servidor. En esta misma clase puede modificar la interfaz a utilizar, para ello comente la actual y descomente la elegida. 

### Reglas
+ El objetivo es conseguir una mano con un puntaje lo más cercano a 21. Si te pasas de dicho número, perdés la mano. 
+ Al principio de cada mano tienes que apostar un monto mínimo. 
+ En todo momento estas compitiendo contra el crupier, la casa, que si no logra conseguir un número mayor que el tuyo deberá pagarte un determinado monto de dinero. 
+ Mientras tu dinero sea mayor o igual a la apuesta mínima podrás seguir jugando. 
+ El crupier esta obligado a recibir cartas hasta que llegue o supere los 17 puntos, cuando lo haga deja de recibirlas.

### Valores de cartas
+ AS: vale 1 o 11, dependiendo de lo que le convenga al jugador.
+ CABALLERO, REINA y REY: Valen 10.
+ El resto de cartas valen lo mismo que su número.

### Comandos Principales
Hay una series de comandos que se ingresan dentro de **la ventana de seteo de apuesta**, dejo adjunto
los que son necesarios para poder acceder a todas las funciones del juego:

* 'salir': Sale del juego por completo, volviendo al menú principal, dejando la opción de guardar la
partida.
* 'save': Guarda la partida.
ranking.
*  '0': Ingresar 0 como monto de apuesta indica que no apostas en esta mano.

### Comandos Secundarios
* 'esoyam': Le da 1000p al jugador que lo ingresa.
+ 'help': muestra el contenido del archivo 'help.txt'.

Pueden encontrarse variaciones a los comandos dirigiendose a la clase **'Intencion'** en el paquete **'ar.edu.unlu.tools'**.

# Créditos

  [Imágenes de cartas](https://yaomon.itch.io/playing-cards) 

