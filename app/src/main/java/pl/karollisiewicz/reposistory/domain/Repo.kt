package pl.karollisiewicz.reposistory.domain

data class Repo(
    val id: String,
    val name: String,
    val owner: Owner,
    val description: String,
    val type: Type
) {
    data class Owner(val name: String, val avatarUrl: String)

    enum class Type {
        GITHUB, BIT_BUCKET
    }
}