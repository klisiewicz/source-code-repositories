package pl.karollisiewicz.reposistory.ui.list

import pl.karollisiewicz.reposistory.domain.Repo

class RepoListViewState(
    val isLoading: Boolean = false,
    val repositories: List<Repo> = emptyList(),
    val error: String? = null
) {
    val hasData: Boolean = repositories.isNotEmpty()
}