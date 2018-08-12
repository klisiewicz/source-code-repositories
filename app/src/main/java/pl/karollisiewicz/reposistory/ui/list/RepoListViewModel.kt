package pl.karollisiewicz.reposistory.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.karollisiewicz.common.reactive.ReactiveViewModel
import pl.karollisiewicz.reposistory.domain.Repo
import pl.karollisiewicz.reposistory.domain.RepoRepository

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

    private fun fetchRepositories() {
        repository.fetchAll()
            .doOnSubscribe { createLoadingState() }
            .doOnNext { createDataState(it) }
            .doOnError { createErrorState(it) }
            .subscribe()
            .enqueueForDisposal()
    }

    private fun createLoadingState() {
        viewState?.value = RepoListViewState(isLoading = true)
    }

    private fun createDataState(repos: Collection<Repo>) {
        viewState?.value = RepoListViewState(repositories = repos.toList())
    }

    private fun createErrorState(throwable: Throwable) {
        viewState?.value = RepoListViewState(error = throwable.message)
    }
}