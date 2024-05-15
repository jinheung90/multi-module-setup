package jinheung.project.auth.repository



import jinheung.project.auth.entity.User
import jinheung.project.auth.entity.UserAuthority
import org.springframework.stereotype.Repository

@Repository
class UserAuthorityRepository : MutinyRepository<UserAuthority, Long> {
    override suspend fun <S : UserAuthority> save(entity: S): UserAuthority {
        TODO("Not yet implemented")
        return entity
    }

    override suspend fun <S : UserAuthority> saveAll(entities: Iterable<S>): List<UserAuthority> {
        TODO("Not yet implemented")
        return entities.toList()
    }

}