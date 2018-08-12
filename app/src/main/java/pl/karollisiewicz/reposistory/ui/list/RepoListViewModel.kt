package pl.karollisiewicz.reposistory.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.karollisiewicz.common.reactive.ReactiveViewModel
import pl.karollisiewicz.reposistory.R
import pl.karollisiewicz.reposistory.domain.Repo
import pl.karollisiewicz.reposistory.domain.RepoRepository
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class RepoListViewModel(
    private val repository: RepoRepository
) : ReactiveViewModel() {
    private var viewState: MutableLiveData<RepoListViewState>? = null

    fun getViewState(): LiveData<RepoListViewState> {
        if (viewState == null) {
            viewState = MutableLiveData()
            fetchRepositories()
        }
        return viewState as MutableLiveData<RepoListViewState>
    }

    var sortOrder: RepoSortOrder = RepoSortOrder.TYPE
        set(newValue) {
            viewState?.apply {
                field = newValue
                value = value?.copy(
                    repositories = value?.repositories?.sortedBy(newValue.selector).orEmpty()
                )
            }
        }

    fun fetchRepositories() {
        repository.fetchAll()
            .doOnSubscribe { createLoadingState() }
            .subscribe(
                { createDataState(it) },
                { createErrorState(it) }
            )
            .enqueueForDisposal()
    }

    private fun createLoadingState() {
        viewState?.value = RepoListViewState(isLoading = true)
    }

    private fun createDataState(repos: Collection<Repo>) {
        viewState?.value = RepoListViewState(repositories = repos.toList())
    }

    private fun createErrorState(throwable: Throwable) {
        viewState?.apply {
            value = RepoListViewState(
                repositories = value?.repositories.orEmpty(),
                errorMessageId = throwable.presentableMessage
            )
        }
    }
}

private val Throwable.presentableMessage: Int?
    get() = when (this) {
        is TimeoutException -> R.string.error_network
        is UnknownHostException -> R.string.error_network
        else -> R.string.error_unknown
    }

enum class RepoSortOrder(val selector: (Repo) -> String) {
    TYPE({ it.type.name }), NAME({ it.name })
}