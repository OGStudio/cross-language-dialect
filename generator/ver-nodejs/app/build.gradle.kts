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
                // Convert file name to MIME type
                implementation(npm("mime-types", "3.0.1"))
            }
        }
    }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.BIN
}
