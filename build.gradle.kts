plugins {
    java
}
repositories {
    mavenCentral()
}
dependencies {
    val springPlatform = platform("org.springframework.boot:spring-boot-dependencies:2.6.3")
    implementation(springPlatform)
    annotationProcessor(springPlatform)

    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")

    val projectLombok = "org.projectlombok:lombok:1.18.22"
    compileOnly(projectLombok)
    annotationProcessor(projectLombok)
    testCompileOnly(projectLombok)
    testAnnotationProcessor(projectLombok)
}
tasks.test {
    useJUnitPlatform()
}
tasks.processResources {
    dependsOn(tasks.compileJava)
}