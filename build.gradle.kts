plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testfx:testfx-core:4.0.16-alpha")
    testImplementation("org.mockito:mockito-core:3.12.4")
    implementation("org.openjfx:javafx-controls:19")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "edu.bsu.cs222.GUI"
}
