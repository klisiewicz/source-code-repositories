package pl.karollisiewicz.reposistory.data.source.github

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GithubRepo(
    val id: Long,
    val name: String,
    val owner: GithubRepoOwner,
    val description: String?
) : Serializable

class GithubRepoOwner(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Serializable