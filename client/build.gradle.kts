plugins {
    java
    id("org.openjfx.javafxplugin") version "0.0.12"
}
tasks.test {
    useJUnitPlatform()
}
javafx {
    modules("javafx.controls", "javafx.fxml")
}

dependencies{
    implementation(project(":common"))
    implementation("net.rgielen:javafx-weaver-spring-boot-starter:1.3.0")
}