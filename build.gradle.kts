import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"

    id("com.github.johnrengelman.shadow") version "8.1.1"

    id("io.papermc.paperweight.userdev") version "2.0.0-beta.16"

    id("xyz.jpenilla.run-paper") version "2.3.1"
}

val yvtilsVersion = "3.0.2"
val jdaVersion = "5.5.1"

group = "yv.tils"
version = yvtilsVersion

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    implementation("net.dv8tion", "JDA", jdaVersion)
}

tasks.register("updateVersionFiles") {
    doLast {
        val versionFile = yvtilsVersion

        val filesToUpdate = listOf("src/main/resources/plugin.yml", "src/main/resources/paper-plugin.yml")
        filesToUpdate.forEach { file ->
            val content = file(file).readText()
            val updatedContent = content.replace(Regex("(?<=^version: )\\S+", RegexOption.MULTILINE), versionFile)
            file(file).writeText(updatedContent)
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    runServer {
        minecraftVersion("1.21.4")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}

tasks.shadowJar {
    archiveBaseName.set("YVtils-DC")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")
    archiveFileName.set("YVtils-DC_${version}.jar")

    manifest {
        attributes["Main-Class"] = "yv.tils.dc.YVtils"
    }
}
