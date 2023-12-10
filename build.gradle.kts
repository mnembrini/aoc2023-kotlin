plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

kotlin { // Extension for easy setup
    jvmToolchain(17) // Target version of generated JVM bytecode. See 7️⃣
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
