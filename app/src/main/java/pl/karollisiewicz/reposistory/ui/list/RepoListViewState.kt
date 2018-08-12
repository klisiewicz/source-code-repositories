package pl.karollisiewicz.reposistory.ui.list

import androidx.annotation.StringRes
import pl.karollisiewicz.reposistory.domain.Repo

data class RepoListViewState(
    val isLoading: Boolean = false,
    val repositories: List<Repo> = emptyList(),
    @StringRes val errorMessageId: Int? = null
) {
    val hasData: Boolean = repositories.isNotEmpty()
}