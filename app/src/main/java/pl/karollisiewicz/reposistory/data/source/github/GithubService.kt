package pl.karollisiewicz.reposistory.data.source.github

import io.reactivex.Observable
import retrofit2.http.GET

interface GithubService {
    @GET("repositories")
    fun fetchAll(): Observable<Collection<GithubRepo>>
}
