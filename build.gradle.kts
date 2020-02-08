plugins {
    kotlin("multiplatform") apply false
}

allprojects {

    group = "org.hexworks.cobalt"
    version = "2020.0.12-RELEASE"

    repositories {
        mavenCentral()
        jcenter()
        kotlinx()
        jitpack()
        ktor()
        mavenLocal()
    }

}
