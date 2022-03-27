plugins {
    java
    id("org.springframework.boot") version "2.5.5" apply false
}
allprojects{
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    repositories {
        mavenCentral()
    }
    dependencies {
        val springPlatform = platform("org.springframework.boot:spring-boot-dependencies:2.6.3")
        implementation(springPlatform)
        annotationProcessor(springPlatform)

        implementation("org.springframework:spring-beans")
        implementation("org.springframework:spring-core")
        implementation("org.springframework:spring-context")
        implementation("org.springframework:spring-core")
        implementation("org.springframework.boot:spring-boot-autoconfigure")
        implementation("org.springframework.boot:spring-boot-starter-validation")

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
}