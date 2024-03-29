buildscript {
    ext {
        springBootVersion = '2.7.6'
    }

    repositories {
        mavenCentral()
        maven { url 'https://repo.spring.io/release/' }
        maven { url "https://repo.spring.io/libs-snapshot-local" }
        maven { url "https://repo.spring.io/libs-milestone-local" }
        maven { url "https://repo.spring.io/libs-release-local" }
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath "io.spring.gradle:dependency-management-plugin:1.1.0"
    }
}

ext {
    set('springCloudVersion', "2021.0.3") // 2.7.6 버젼 트레인

}

allprojects {
    group 'jinheung.project'
    version '1.0'
}



subprojects {

    apply plugin: 'java'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'

    sourceCompatibility = 11


    dependencies {

        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        implementation 'org.springframework.boot:spring-boot-starter-validation'
        testImplementation group: 'org.junit.jupiter', name: 'junit-jupiter-engine', version: '5.7.0'
        annotationProcessor 'org.projectlombok:lombok'
        implementation 'org.projectlombok:lombok:1.18.18'
    }

    dependencyManagement {
        imports {
            mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
        }
    }

    configurations {
        compileOnly {
            extendsFrom annotationProcessor
        }
    }
}



project(":jinheung_gateway") {
    dependencies {
        compile project(':jinheung_common')
        compile project(':jinheung_auth_core')
    }
}


project(":jinheung_user_api") {
    dependencies {
        compile project(':jinheung_common')
        compile project(':jinheung_user_core')
    }
}


project(":jinheung_event_handler") {
    dependencies {
        compile project(':jinheung_common')
//        compile project(':jinheung_user_core')
        compile project(':jinheung_auth_core')
    }
}

project(":jinheung_eureka") {
    dependencies {

    }
}