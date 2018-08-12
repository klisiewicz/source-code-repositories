package pl.karollisiewicz.reposistory.data.source.github

import com.google.gson.annotations.SerializedName

class GithubRepo(
    val id: Long,
    val name: String,
    val owner: GithubRepo.Owner,
    val description: String?
) {
    class Owner(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
    )
}