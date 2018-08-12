package pl.karollisiewicz.reposistory.data.source.bitbucket

import com.google.gson.annotations.SerializedName

class BitBucketRepos(
    @SerializedName("values") val repos: Collection<BitbucketRepo>
)

class BitbucketRepo(
    val name: String,
    val description: String,
    val owner: BitbucketRepo.Owner
) {
    class Owner(
        @SerializedName("uuid") val id: String,
        @SerializedName("username") val name: String,
        val links: BitbucketRepo.Owner.Links
    ) {
        class Links(val avatar: Avatar) {
            class Avatar(val href: String)
        }
    }
}