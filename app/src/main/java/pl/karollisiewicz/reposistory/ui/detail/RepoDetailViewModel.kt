package pl.karollisiewicz.reposistory.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.karollisiewicz.reposistory.domain.Repo

class RepoDetailViewModel : ViewModel() {
    private val selected = MutableLiveData<Repo>()

    fun select(repo: Repo) {
        selected.value = repo
    }

    fun getSelected(): LiveData<Repo> {
        return selected
    }
}
