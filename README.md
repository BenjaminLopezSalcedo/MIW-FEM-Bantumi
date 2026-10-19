# Bantumi

Práctica de Android: continuación del desarrollo del juego Bantumi.

Este documento recoge **qué se ha hecho y cómo** en cada issue, además de las decisiones y observaciones importantes.

---

## Estado general

| # | Issue |
|---|-------|
| 1 | Reiniciar partida |
| 2 | Guardar partida |
| 3 | Recuperar partida |
| 4 | Guardar puntuación |
| 5 | Mejores resultados |

---

## Issues obligatorios

### 1. Reiniciar partida

Opción de menú que, tras un diálogo de confirmación, reinicia la partida en curso.

**Observaciones, qué se ha hecho / cómo:**

-

---

### 2. Guardar partida

Guarda el estado actual del tablero (una única partida) usando el sistema de ficheros del dispositivo.

**Observaciones, qué se ha hecho / cómo:**

-

---

### 3. Recuperar partida

Carga la partida guardada, si existe, pidiendo confirmación cuando la partida actual haya sido modificada.

**Observaciones, qué se ha hecho / cómo:**

-

---

### 4. Guardar puntuación

Al terminar cada partida se guarda en base de datos (Room): nombre del jugador (de los ajustes), fecha y hora, y semillas en cada almacén.

**Observaciones, qué se ha hecho / cómo:**

-

---

### 5. Mejores resultados

Pantalla con el top 10 de resultados ordenado por mayor número de semillas, con opción (con confirmación) de borrar todos los resultados.

**Observaciones, qué se ha hecho / cómo:**

-

---

## Mejoras opcionales

_Rellenar solo las que se implementen y borrar el resto._

- #### Número inicial de semillas configurable
- #### Elegir qué jugador empieza
- #### Cronómetro / tiempo empleado
- #### Guardar más de una partida
- #### Filtrar mejores resultados (por semillas o jugador)
- #### Marcar la casilla elegida
- #### Preferencias de colores

---

## Decisiones técnicas y observaciones generales

- 

---

## Entorno

- Android Studio:
- minSdk / targetSdk:
- Dispositivo / emulador usado para probar:
