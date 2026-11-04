# StockFácil — PA3 Programación Orientada a Objetos

Aplicación web académica de gestión de productos **en memoria** con Java 17, JSP, Jakarta Servlet, Maven y Apache Tomcat 11.

## Funciones
- Registrar productos (ID automático, nombre, precio, cantidad).
- Listar y buscar productos por nombre.
- Eliminar productos por ID.
- Ordenar productos por nombre o precio con `Collections.sort()`.

## Colecciones usadas
`Inventario` conserva los registros en `ArrayList<Producto>`. Usa `add`, `get`, `remove`, `size`, recorridos `for` y `Collections.sort`. Los métodos públicos están sincronizados para compartir el inventario entre solicitudes. Los datos se pierden al reiniciar o redesplegar la aplicación.

## Requisitos
- JDK 17 o superior
- Maven 3.9+ o Maven integrado en IntelliJ IDEA
- Apache Tomcat 11

## Ejecución
1. Abrir el proyecto como Maven en IntelliJ IDEA.
2. Ejecutar el objetivo Maven `clean package` (panel Maven > Lifecycle).
3. Desplegar `target/stockfacil.war` o el artifact `stockfacil:war exploded` en Tomcat 11.
4. Configurar el contexto `/stockfacil`.
5. Abrir `http://localhost:8080/stockfacil/` (ajustar el puerto si corresponde).

## Integrantes y responsabilidades
- Sebastian Borda: coordinación, Maven, Tomcat, integración y GitHub.
- Parte A — asignar integrante: modelo `Producto` y registro.
- Parte B — asignar integrante: búsqueda y eliminación.
- Parte C — Adrian Basurco: listado, ordenamiento e interfaz JSP.
- Parte D — asignar integrante: pruebas, documentación y evidencias.

## Pruebas manuales sugeridas
1. Registrar Teclado (85, 10), Monitor (650, 5) y Mouse (45, 20).
2. Comprobar listado de tres productos.
3. Buscar `mouse` y verificar coincidencia sin distinguir mayúsculas.
4. Ordenar por precio: Mouse, Teclado, Monitor.
5. Eliminar el producto ID 2 y verificar que queden dos.
6. Intentar precio negativo, cantidad negativa y nombre vacío.
7. Reiniciar Tomcat y comprobar que se vació el inventario.

## Video de exposición
Pendiente: agregar URL de YouTube (no listado o público según indique el docente).

## Nota académica
Proyecto de práctica. Los productos se almacenan en memoria, no en una base de datos. No incluye autenticación ni protección CSRF; no está destinado a producción.
