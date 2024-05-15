package jinheung.project.auth.repository


import jinheung.project.auth.entity.User
import jinheung.project.auth.entity.UserSecurity
import org.springframework.stereotype.Repository

@Repository
interface UserSecurityRepository : MutinyRepository<UserSecurity, Long>{
    override suspend fun <S : UserSecurity> save(entity: S): UserSecurity {
        TODO("Not yet implemented")
        return entity
    }

    override suspend fun <S : UserSecurity> saveAll(entities: Iterable<S>): List<UserSecurity> {
        TODO("Not yet implemented")
        return entities.toList()
    }

}