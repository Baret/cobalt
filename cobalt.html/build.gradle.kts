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

        with(Libs) {
            commonMainApi(kotlinxCollectionsImmutable)
        }

        with(Projects) {
            commonMainApi(cobaltCore)
            commonMainApi(cobaltDatatypes)
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
        module = "cobalt.html",
        desc = "HTML utilities for Cobalt."
    )
}

signing {
    isRequired = false
    sign(publishing.publications)
}