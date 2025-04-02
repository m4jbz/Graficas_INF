## Compilar

Para compilar este proyecta se recomienda usar Netbeans o IntelliJ IDEA como IDE para facilitar el proceso. De igual forma
se pude compilar perfectamente usando Maven y java en la terminal (de Windows o Linux). La version de Maven utilizada en el
proyecto es la 3.9.9 y Java 11, por lo que se deben de tener instaladas dichas versiones para que funcione sin problemas,
(no tengo ni idea si se usa una version más reciente vaya a funcionar).

Tanto en Netbeans como en IntelliJ IDEA se debe de importar como proyecto Maven. Cambiar el jdk utilizado a JDK 11 (si aún
no se ha hecho) y ejecutar el archivo Main.

Para compilarlo en la terminal obviamente también se requiere de los programas anteriores mencionados.

```bash
git clone https://github.com/m4jbz/Graficas_INF
cd Graficas_INF
mvn package
mvn exec:java
```

## Usar ejecutable

Si no se quiere descargar el código fuente, y solo se quiere usar el programa, descargar primero el ejecutable .jar de la
pestaña de versiones de este repositorio. Y simplemente darle doble click al archivo descargado. Aquí si no importa tanto
la version de Java, mientras sea 11 o superior deberia funcionar sin errores. Para ejecutarlo en la terminal ya sea en Windows
o en Linux simplente se ejcuta el siguiente comando:

```bash
java -jar Graficas-1.0.jar
```
