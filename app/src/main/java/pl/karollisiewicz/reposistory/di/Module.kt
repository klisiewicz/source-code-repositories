package pl.karollisiewicz.reposistory.di

import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import pl.karollisiewicz.reposistory.BuildConfig
import pl.karollisiewicz.reposistory.data.source.RepoRestRepository
import pl.karollisiewicz.reposistory.data.source.bitbucket.BitbucketService
import pl.karollisiewicz.reposistory.data.source.github.GithubService
import pl.karollisiewicz.reposistory.domain.RepoRepository
import pl.karollisiewicz.reposistory.ui.detail.RepoDetailViewModel
import pl.karollisiewicz.reposistory.ui.list.RepoListViewModel
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val dataModule = module {
    single { RepoRestRepository(get(), get()) as RepoRepository }
    single {
        pl.karollisiewicz.common.reactive.Schedulers(
            subscriber = Schedulers.newThread(),
            observer = AndroidSchedulers.mainThread()
        )
    }
}

private val uiModule = module {
    viewModel { RepoListViewModel(get()) }
    viewModel { RepoDetailViewModel() }
}

private val webModule = module {
    fun getHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .build()

    fun getRetrofit(apiUrl: String) = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(getHttpClient())
        .addCallAdapterFactory(get())
        .addConverterFactory(get())
        .build()

    fun getGitHubService(): GithubService {
        return getRetrofit(BuildConfig.GITHUB_API_URL).create(GithubService::class.java)
    }

    fun getBitBucketService(): BitbucketService {
        return getRetrofit(BuildConfig.BITBUKET_API_URL).create(BitbucketService::class.java)
    }

    single { RxJava2CallAdapterFactory.create() as CallAdapter.Factory }
    single {
        GsonConverterFactory.create(
            GsonBuilder().setLenient().serializeNulls().create()
        ) as Converter.Factory
    }
    single { }

    single { getGitHubService() }
    single { getBitBucketService() }
}

val modules = listOf(dataModule, uiModule, webModule)