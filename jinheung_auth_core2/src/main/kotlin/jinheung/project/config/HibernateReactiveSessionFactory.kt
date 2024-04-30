package jinheung.project.config


import jakarta.persistence.Persistence
import org.hibernate.reactive.mutiny.Mutiny


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.hibernate.reactive.persister.entity.mutation.ReactiveInsertCoordinator


@Configuration
class HibernateReactiveSessionFactory {

    @Bean
    fun sessionFactory(): Mutiny.SessionFactory {
        val props = HashMap<String, String>()
        props["jakarta.persistence.jdbc.url"] = "jdbc:mysql://localhost:3306/auth"
        props["jakarta.persistence.jdbc.user"] = "root"
        props["jakarta.persistence.jdbc.password"] = "1234"
        return Persistence.createEntityManagerFactory("auth",props)
            .unwrap(Mutiny.SessionFactory::class.java)
    }


}