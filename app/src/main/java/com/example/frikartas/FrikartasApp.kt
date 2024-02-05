package com.example.frikartas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Aplicación principal de Frikartas.
 *
 * Esta clase sirve como punto de entrada para la aplicación. Está anotada con `@HiltAndroidApp`,
 * lo que la convierte en el contenedor principal de Hilt para la inyección de dependencias.
 * Hilt generará automáticamente un componente de aplicación Hilt que estará adjunto al ciclo de vida
 * de la aplicación, y que podrá proporcionar dependencias a cualquier parte de la aplicación.
 */
@HiltAndroidApp
class FrikartasApp : Application(){
    // Aquí puedo incluir cualquier configuración global o inicialización que la aplicación necesite
}