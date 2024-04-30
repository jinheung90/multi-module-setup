package jinheung.project.mall.dto

import jinheung.project.mall.entity.Mall
import jinheung.project.mall.entity.MallHasUser
import kotlin.streams.toList


data class MallDTO(
    val name: String,
    val mallHasUsers: List<Long>,
) {
    companion object {
        fun toDto(mall: Mall, mallHasUser: List<MallHasUser>) : MallDTO {
            return MallDTO(mall.name, mallHasUser.stream().map { t -> t.id }.toList())
        }
    }
}