package jinheung.project.mall.entity

import jakarta.persistence.*


@Entity(name = "malls_has_users")
class MallHasUser(
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    @ManyToOne
    @JoinColumn(name = "mall_id")
    val mall: Mall = Mall()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}