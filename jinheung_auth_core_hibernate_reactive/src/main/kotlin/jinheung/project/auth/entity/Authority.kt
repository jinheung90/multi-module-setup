package jinheung.project.auth.entity



import io.quarkus.hibernate.reactive.panache.PanacheEntity
import org.hibernate.Hibernate
import javax.persistence.*


@Entity(name = "authorities")
class Authority : PanacheEntity() {
    @Column(name = "name")
    val name: String = ""
    @OneToMany
    val user: List<User> = listOf();
    @Id @Column @GeneratedValue
    val id: Long = 0L

}