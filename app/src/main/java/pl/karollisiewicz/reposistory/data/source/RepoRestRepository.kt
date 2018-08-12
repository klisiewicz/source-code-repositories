package pl.karollisiewicz.reposistory.data.source

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import pl.karollisiewicz.common.extension.applySchedulers
import pl.karollisiewicz.common.reactive.Schedulers
import pl.karollisiewicz.reposistory.data.source.bitbucket.BitBucketRepos
import pl.karollisiewicz.reposistory.data.source.bitbucket.BitbucketService
import pl.karollisiewicz.reposistory.data.source.github.GithubRepo
import pl.karollisiewicz.reposistory.data.source.github.GithubService
import pl.karollisiewicz.reposistory.domain.Repo
import pl.karollisiewicz.reposistory.domain.Repo.Type.BIT_BUCKET
import pl.karollisiewicz.reposistory.domain.Repo.Type.GITHUB
import pl.karollisiewicz.reposistory.domain.RepoRepository
import java.util.concurrent.TimeUnit.SECONDS

typealias Repos = Collection<Repo>

class RepoRestRepository(
    private val githubService: GithubService,
    private val bitbucketService: BitbucketService,
    private val schedulers: Schedulers
) : RepoRepository {

    override fun fetchAll(): Observable<Repos> {
        val githubObservable = githubService
            .fetchAll()
            .map { it.toRepos() }

        val bitbucketObservable = bitbucketService
            .fetchAll()
            .map { it.toRepos() }

        return Observable.zip(
            bitbucketObservable,
            githubObservable,
            BiFunction<Repos, Repos, Repos> { github, bitbucket ->
                github.union(bitbucket)
            })
            .timeout(5, SECONDS)
            .applySchedulers(schedulers)
    }
}

private fun Collection<GithubRepo>.toRepos(): Collection<Repo> = this.map {
    Repo(
        id = it.id.toString(), name = it.name, owner = Repo.Owner(
            name = it.owner.login, avatarUrl = it.owner.avatarUrl
        ), description = it.description.orEmpty(), type = GITHUB
    )
}

private fun BitBucketRepos.toRepos(): Collection<Repo> = repos.map {
    Repo(
        id = "${it.owner.id}-${it.name}", name = it.name, owner = Repo.Owner(
            name = it.owner.name, avatarUrl = it.owner.links.avatar.href
        ), description = it.description, type = BIT_BUCKET
    )
}