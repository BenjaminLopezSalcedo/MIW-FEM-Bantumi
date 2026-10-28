# Bantumi

Práctica de Android (FEM): persistencia de datos (preferencias, ficheros y base de datos Room) sobre el juego Bantumi.

Repositorio: https://github.com/BenjaminLopezSalcedo/MIW-FEM-Bantumi

---

## Estado general

| # | Issue | Estado |
|---|-------|--------|
| 1 | Reiniciar partida | Hecho |
| 2 | Guardar partida | Hecho |
| 3 | Recuperar partida | Hecho |
| 4 | Guardar puntuación | Hecho |
| 5 | Mejores resultados | Hecho |

> **Nota:** Varios de los issues obligatorios tienen parte de su lógica modificada por las mejoras opcionales (número de semillas y jugador inicial configurables, filtro por jugador), asegurando su correcto funcionamiento en todos los casos.

---

## Issues obligatorios

### 1. Reiniciar partida

Opción "Reiniciar" del menú. Muestra un diálogo de confirmación (`RestartAlertDialog`) y, si se acepta, reinicia el tablero con el número de semillas y el jugador inicial que indiquen los ajustes en ese momento.

### 2. Guardar partida

Opción "Guardar" del menú. Guarda una única partida en un fichero de la memoria interna (`openFileOutput`). El formato es una línea de texto con el turno y las 14 casillas (`turno|n0-n1-...-n13`), generada por `JuegoBantumi.serializa()`.

Se muestra un aviso (Snackbar) de éxito o error para dar feedback al usuario.

### 3. Recuperar partida

Opción "Recuperar" del menú. Pide siempre confirmación (`LoadSaveDialog`) antes de cargar. Tras confirmar, el estado se restaura con `JuegoBantumi.deserializa()`. Si no hay partida guardada, se avisa con un mensaje.

#### Observaciones:

- Al guardarse solo el turno y el contenido de las casillas, funciona con cualquier número inicial de semillas, sin afectar a la partida actual.

### 4. Guardar puntuación

Al terminar una partida se guarda automáticamente en Room (`FinalScore`):

| Variables guardadas        | Obtención                                     |
|----------------------------|-----------------------------------------------|
| `Nombre del jugador`       | tomado de los ajustes.                        |
| `Fecha y hora`             | fecha y hora de finalización de la partida.   |
| `Semillas de cada almacén` | tomadas de las posiciones 6 y 13 del tablero. |

La fecha se persiste mediante un `TypeConverter` (`LocalDateTime` ↔ epoch en segundos). Se evita guardar dos veces la misma partida comprobando que no estaba ya terminada antes de la jugada.

#### Observaciones:

- Se modificó la base de `huecoPulsado(@NonNull View v)` para prevenir duplicaciones de los diálogos de final de partida.
- Se reubicó la lógica de guardado en `huecoPulsado` en vez de en `finJuego`, como indicaba el TODO, asegurando que se guarde una sola vez independientemente de las llamadas a `finJuego`.
- Se creó una pantalla de preferencias básica para seleccionar el nombre del jugador.

### 5. Mejores resultados

Opción "Mejores resultados" del menú. Pantalla (`BestScoresActivity`) con un `RecyclerView` que muestra el top 10: posición, nombre, semillas de cada jugador con el porcentaje del ganador, y fecha y hora. Incluye en la barra superior la opción de borrar todos los resultados, con diálogo de confirmación (`DeleteScoresAlertDialog`). Si no hay resultados, se muestra un texto informativo.

#### Observaciones:

- **Cambio de la query:** La consulta de obtención del Top 10 varía respecto a lo pedido ya que se adapta al número inicial de semillas en vez de calificar por el mayor número de semillas de cualquier jugador. Esto ordena por porcentaje de victoria en lugar de semillas absolutas.
- **Criterio de ordenación:** El ranking se ordena primero por el porcentaje de semillas del ganador sobre el total de la partida y, en caso de empate, por sus semillas absolutas. Se hace así porque el número inicial de semillas es configurable (de 1 a 10 por casilla, mejora opcional #1) y el total varía entre partidas, por lo que comparar solo semillas absolutas no sería equitativo. El porcentaje se muestra junto a cada resultado.
- **Consulta filtrada:** La pantalla obtiene los resultados con `getTop10ScoresFiltered(...)`, que aplica el mismo criterio de ordenación y añade el filtro por jugador (mejora opcional #5). La consulta original `getTop10Scores()` se mantiene solo para registrar el Top 10 en el log con fines de depuración.

---

## Mejoras opcionales

### 1. Número inicial de semillas configurable

Preferencia con un `SeekBarPreference` (de 1 a 10, por defecto 4). Se aplica a las siguientes partidas, al reiniciar o al empezar una nueva.

### 2. Elegir qué jugador empieza

Preferencia "Jugador inicial" (Jugador 1 / Jugador 2) y un interruptor de "Jugador inicial aleatorio". Si el aleatorio está activado, la opción manual queda deshabilitada.

- **Observación:** Si empieza el Jugador 2 (la app), la máquina no mueve sola al arrancar la partida: realiza su jugada cuando el usuario pulsa cualquier casilla. Es el comportamiento heredado del proyecto base.

### 3. Mejora estética del Top 10

Numeración de posiciones, colores oro/plata/bronce para los tres primeros, fondo con transparencia por posición y porcentaje del ganador en cada fila.

### 4. Pantalla de información en los ajustes

Enlace al repositorio de GitHub y versión de la aplicación leída del `PackageManager`.

### 5. Filtro de mejores resultados por jugador

Icono de filtro en la barra superior de "Mejores resultados" que abre un diálogo (`PlayerFilterDialog`) con la lista de jugadores guardados y la opción "Todos". Al elegir uno, el Top 10 muestra solo las partidas de ese jugador.

- **Observación:** El filtro se aplica con un `LIKE` sobre el nombre, usando `%` para "Todos". Al borrar los resultados, el filtro se restablece.
- **Observación:** Si hay más de 8 opciones, el diálogo limita su altura y la lista hace scroll.

---

## Entorno

- Android Studio: Rabbit 1 | 2026.2.1 (build AI-262.9437.185.2621.16467767)
- minSdk / targetSdk: 28 / 37 (compileSdk 37)
- Dispositivo / emulador usado para probar: Pixel 9 Pro (emulador)