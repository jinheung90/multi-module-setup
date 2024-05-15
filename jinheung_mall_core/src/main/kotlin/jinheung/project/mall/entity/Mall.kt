package jinheung.project.mall.entity


import jakarta.persistence.*



@Table(name = "malls")
@Entity
class Mall (
    @Column
    val name : String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
) {
    @OneToMany
    val products : List<Product> = ArrayList()
    @OneToMany(mappedBy = "mall", cascade = [CascadeType.ALL])
    val mallHasUsers: List<MallHasUser> = listOf()
}