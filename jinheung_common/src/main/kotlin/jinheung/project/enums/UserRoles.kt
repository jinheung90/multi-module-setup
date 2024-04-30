package jinheung.project.enums

enum class UserRoles(s: String) {
    ROLE_USER("ROLE_USER"),
    ROLE_COMMON("ROLE_COMMON"),
    ROLE_MARKETER("ROLE_MARKETER");

    companion object {

        fun anonymousUserRole(): List<String> {
            return listOf(ROLE_COMMON.name)
        }

        fun marketerUserRole(): List<String> {
            return listOf(ROLE_MARKETER.name, ROLE_COMMON.name, ROLE_USER.name)
        }

        fun signedUserRole(): List<String> {
            return listOf(ROLE_COMMON.name, ROLE_USER.name)
        }

        fun rootUserRole(): List<String> {
            return UserRoles.values().map { ur -> ur.name }
        }
    }
}