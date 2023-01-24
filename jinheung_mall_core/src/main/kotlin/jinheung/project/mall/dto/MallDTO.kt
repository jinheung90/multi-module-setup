package jinheung.project.mall.dto

import jinheung.project.mall.entity.Mall


data class MallDTO(
    val name: String = ""
) {
    companion object {
        fun toDto(mall: Mall) : MallDTO {
            return MallDTO(mall.name)
        }
    }
}