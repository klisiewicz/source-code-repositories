package pl.karollisiewicz.reposistory.domain

import io.reactivex.Observable

interface RepoRepository {
    fun fetchAll(): Observable<Collection<Repo>>
}
