package pl.karollisiewicz.reposistory.data.source.bitbucket

import io.reactivex.Observable
import retrofit2.http.GET

interface BitbucketService {
    @GET("repositories?fields=values.name,values.owner,values.description")
    fun fetchAll(): Observable<BitBucketRepos>
}