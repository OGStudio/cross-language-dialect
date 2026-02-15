plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    js {
        nodejs { }
        binaries.executable()
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        val jsMain by getting {
            dependencies {
            }
        }
    }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.BIN
}
