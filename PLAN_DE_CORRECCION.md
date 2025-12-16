# Plan de Corrección y Verificación de Despliegue

Este plan aborda los problemas de conexión entre Frontend y Backend, la configuración de despliegue en AWS y la eliminación de datos de prueba.

## 1. Análisis de Errores Encontrados

1.  **Configuración de API Incorrecta (`api.js`)**:
    -   Usa una URL absoluta (`http://44.218.26.62:8080/api`) como fallback. Esto puede fallar si el usuario accede por el puerto 80 o si la IP cambia.
    -   **Solución**: Usar una ruta relativa `/api` para que el navegador resuelva la dirección automáticamente (mismo origen).

2.  **Fallback a Datos de Muestra (`perfume.js`)**:
    -   Si la API falla, carga `sample-products.json`. Esto enmascara el error real y muestra datos falsos.
    -   **Solución**: Eliminar este fallback para ver los errores reales y forzar la carga desde la base de datos.

3.  **Configuración de Base Path en Vite (`vite.config.js`)**:
    -   Configurado para usar `/SuperfumeReact/` en producción (para GitHub Pages).
    -   Esto rompe el despliegue en Spring Boot (AWS), que sirve la app en la raíz `/`.
    -   El workflow actual usa un "hack" (`NODE_ENV=development`) para evitar esto, pero impide optimizaciones de producción.
    -   **Solución**: Configurar `base` dinámicamente usando una variable de entorno `VITE_BASE_PATH`.

4.  **Workflow de GitHub Actions (`deploy-backend.yml`)**:
    -   Usa `NODE_ENV=development` para compilar.
    -   **Solución**: Usar `NODE_ENV=production` y asegurar que la configuración de `base` sea correcta (`/`).

## 2. Pasos de Ejecución

### Paso 1: Corregir `vite.config.js`
-   Modificar la propiedad `base` para usar `process.env.VITE_BASE_PATH || '/'`.
-   Agregar configuración de `server.proxy` para que el desarrollo local (`npm run dev`) funcione conectándose al backend local (`localhost:8080`).

### Paso 2: Corregir `api.js`
-   Cambiar la `BASE` URL por defecto a `/api`.

### Paso 3: Limpiar `perfume.js`
-   Eliminar la función `fetchPublicSamples` y su uso en `list()`.

### Paso 4: Actualizar Workflows de GitHub
-   **Backend (`deploy-backend.yml`)**: Eliminar `NODE_ENV=development` y asegurar una build de producción limpia.
-   **Frontend (`deploy.yml`)**: Agregar la variable de entorno `VITE_BASE_PATH: /SuperfumeReact/` para mantener la compatibilidad con GitHub Pages.

### Paso 5: Verificación
-   El usuario deberá hacer push de los cambios.
-   GitHub Actions ejecutará el build.
-   El backend servirá los archivos estáticos correctamente en `/`.
-   El frontend hará peticiones a `/api/perfumes`.
-   El backend responderá con datos de Supabase.

Voy a proceder a ejecutar los cambios en los archivos.
