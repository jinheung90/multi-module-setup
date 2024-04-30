package jinheung.project.mall.entity

import jakarta.persistence.*


@Entity(name = "mals_has_users")
//    uniqueConstraints = [
//        UniqueConstraint(
//            name = "mall_user_unique",
//            columnNames = [ "mall_id",  "user_id" ])
//    ])
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