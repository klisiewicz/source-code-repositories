package pl.karollisiewicz.reposistory.data.source

import io.reactivex.Observable
import pl.karollisiewicz.common.extension.applySchedulers
import pl.karollisiewicz.common.reactive.Schedulers
import pl.karollisiewicz.reposistory.data.source.github.GithubRepo
import pl.karollisiewicz.reposistory.data.source.github.GithubService
import pl.karollisiewicz.reposistory.domain.Repo
import pl.karollisiewicz.reposistory.domain.Repo.Type.GITHUB
import pl.karollisiewicz.reposistory.domain.RepoRepository

class RepoRestRepository(
    private val githubService: GithubService,
    private val schedulers: Schedulers
) : RepoRepository {

    override fun fetchAll(): Observable<Collection<Repo>> {
        return githubService
            .fetchAll()
            .map { it.toRepos() }
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

