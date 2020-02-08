plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("signing")
}

group = "org.hexworks.cobalt"

kotlin {

    jvm {
        jvmTarget(JavaVersion.VERSION_1_8)
        withJava()
    }

    js {
        browser()
    }

    dependencies {

        with(Projects) {
            commonMainApi(cobaltDatatypes)
            commonMainApi(cobaltCore)
        }

        with(Libs) {
            commonMainApi(kotlinxCollectionsImmutable)
            commonMainImplementation(slf4jApi)
        }

        with(TestLibs) {
            commonTestImplementation(kotlinTestCommon)
            commonTestImplementation(kotlinTestAnnotationsCommon)
            jvmTestImplementation(kotlinTestJunit)
            jsTestImplementation(kotlinTestJs)
        }
    }
}

publishing {
    publishWith(
        project = project,
        module = "cobalt.logging",
        desc = "Logging utilities for Cobalt."
    )
}

signing {
    isRequired = false
    sign(publishing.publications)
}