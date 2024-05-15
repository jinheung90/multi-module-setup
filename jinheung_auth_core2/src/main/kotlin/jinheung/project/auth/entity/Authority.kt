package jinheung.project.auth.entity




import jakarta.persistence.*




@Entity
class Authority(
)  {
    val name: String = ""
    @OneToMany
    val userAuthorities: List<UserAuthority> = listOf()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}