// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("org.jetbrains.dokka") version "1.9.10"
}

//añadimos la dependencia de hilt para la construcción del proyecto
buildscript {
    dependencies {
        val hiltVersion = "2.48.1"
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

// Configuración de Dokka
tasks.dokkaHtml {
    outputDirectory.set(file("$buildDir/docs/html"))
    //la Documentación técnica externa está en ...\Frikartas\app\build\dokka\html
    dokkaSourceSets {
        configureEach {
            sourceRoots.from(file("src"))
            includeNonPublic.set(true)
            skipDeprecated.set(false)
            reportUndocumented.set(true)
            skipEmptyPackages.set(false)
            // Más configuraciones específicas aquí
        }
    }
}