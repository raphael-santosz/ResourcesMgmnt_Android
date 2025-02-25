# ResourcesMgmnt_Android
Activity to practice resources management on android studio aplications.

# MiniActv-1: Buenas Prácticas en la Gestión de Recursos

## Descripción
Esta actividad se basa en el proyecto **MyApplication** generado por defecto en Android Studio (seleccionando **Empty Views Activity** en el asistente de **New Project**). Se deben aplicar buenas prácticas en la gestión de recursos para mejorar la internacionalización y la adaptabilidad de la interfaz de usuario.

## Requisitos

### 1. Verificación y Aplicación de la Primera Buena Práctica
- Revisar **MainActivity.java** y los archivos de recursos para comprobar si cumplen con la primera buena práctica.
- Si es necesario, realizar las correcciones correspondientes.

### 2. Implementación de Recursos Alternativos (Segunda Buena Práctica)
Se deben agregar recursos alternativos para mejorar la adaptabilidad de la aplicación. Algunas sugerencias incluyen:
- **Internacionalización**: Soporte para múltiples idiomas.
- **Adaptabilidad de interfaz**: Diseños específicos (layouts) para diferentes tamaños de pantalla.
  - Se recomienda probar con un emulador de **tablet** para verificar el uso de un layout específico.
  - Adaptación a distintas **orientaciones de pantalla** (portrait/landscape).
- **Recursos gráficos adaptados**: Imágenes e íconos específicos para cada país o idioma soportado.

📌 **Configuración predeterminada:**
- Idioma: **Inglés**
- Orientación: **Portrait** para **smartphone**

Se debe verificar que los cambios esperados se reflejen correctamente al modificar estos parámetros en el dispositivo/emulador.
(Para cambiar el idioma: **Settings > System > Languages & Input > Languages**).

### 3. Implementación de un Botón con Mensaje Emergente (Toast)
Además del `TextView` predeterminado, se debe agregar un **Button** con la etiqueta `"Action1"`, asegurando que su texto también sea internacionalizado.

- Al hacer clic en el botón, debe mostrarse un **Toast** con un mensaje definido en los recursos de idioma:
  ```java
  Toast.makeText(getBaseContext(), getString(R.string.textoToast), Toast.LENGTH_SHORT).show();
