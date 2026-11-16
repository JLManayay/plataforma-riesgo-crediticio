# plataforma-riesgo-crediticio

Repositorio del proyecto **Propuesta de implementación de un proceso de gestión de la configuración y versiones para la Plataforma de Evaluación y Riesgo Crediticio (FinanTech, 2026)**.

## Proceso de gestión de la configuración

El proceso se organiza en cuatro etapas: **registro** de cambios, **nombrado** de versiones (SemVer), **empaquetamiento** y **despliegue**.

- **Ramas (GitHub Flow):** `main` contiene la versión estable; los cambios se desarrollan en `develop` (o en ramas `feature/*`) y se integran a `main` mediante Pull Request con autorrevisión.
- **Tablero de control:** las actividades se gestionan como Issues en GitHub Projects (Kanban: Backlog, To Do, In Progress, Code Review, Done).

## Módulo piloto

Para validar el pipeline de CI/CD antes de aplicarlo a los módulos reales del motor de riesgo, se usa el módulo `EcuacionSegundoGradoMain` (Versión 5 del Laboratorio 02).

| Ruta | Descripción |
|---|---|
| `.github/workflows/` | Workflows de GitHub Actions: `ci.yml`, `cd-dev.yml` y `cd-prod.yml`. |
| `src/main/java/ecuacionsegundogradomain/` | Código fuente del módulo piloto (`Main.java`). |
| `test/` | Pruebas automatizadas (`EcuacionSegundoGradoTest.java`). |
| `dist/` | Artefactos `.jar` generados por los workflows de CD (excluidos del control de versiones). |

## Workflows de CI/CD

| Workflow | Disparador | Qué hace | Issue |
|---|---|---|---|
| `ci.yml` — Integración Continua | push a `develop` y Pull Request hacia `main` | Compila con JDK 17 y ejecuta las pruebas automatizadas | I5, I7 |
| `cd-dev.yml` — Despliegue a Desarrollo | push a `develop` | Compila, empaqueta `motor-calculo-<sha>.jar`, lo publica como artefacto y simula el despliegue | I8 |
| `cd-prod.yml` — Despliegue a Producción | push a `main` (tras el merge del PR) | Igual que el anterior, registrando antes un punto de respaldo (rollback) | I10 |

## Ejecutar las pruebas localmente

```bash
javac -d build/classes src/main/java/ecuacionsegundogradomain/Main.java
javac -d build/classes -cp build/classes test/EcuacionSegundoGradoTest.java
java -ea -cp build/classes ecuacionsegundogradomain.EcuacionSegundoGradoTest
```
