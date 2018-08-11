package pl.karollisiewicz.reposistory.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.karollisiewicz.reposistory.domain.Repo

class RepoListViewModel : ViewModel() {
    private var viewState: MutableLiveData<RepoListViewState>? = null

    fun getViewState(): LiveData<RepoListViewState> {
        if (viewState == null) {
            viewState = MutableLiveData()
            fetchRepositories()
        }
        return viewState as MutableLiveData<RepoListViewState>
    }

    private fun fetchRepositories() {
        viewState?.value = RepoListViewState(
            repositories = listOf(
                Repo(
                    id = "1",
                    name = "grit",
                    owner = Repo.Owner("mojombo", "https://avatars0.githubusercontent.com/u/1?v=4"),
                    description = "**Grit is no longer maintained. Check out libgit2/rugged.** Grit gives you object oriented read/write access to Git repositories via Ruby.",
                    type = Repo.Type.GITHUB
                ),
                Repo(
                    id = "c8614bfa-831a-49eb-866b-4bdd87c8c2c2",
                    name = "tweakmsg",
                    owner = Repo.Owner("phlogistonjohn", "https://bitbucket.org/account/phlogistonjohn/avatar/"),
                    description = "Mercurial (hg) extension to allow commenting on commit messages.  Mainly written for practice reading & working with mercurial internals.",
                    type = Repo.Type.BIT_BUCKET
                )
            )
        )
    }
}