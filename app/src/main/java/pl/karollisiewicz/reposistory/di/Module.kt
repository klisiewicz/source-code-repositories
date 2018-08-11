package pl.karollisiewicz.reposistory.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import pl.karollisiewicz.reposistory.ui.detail.RepoDetailViewModel
import pl.karollisiewicz.reposistory.ui.list.RepoListViewModel

private val uiModule = module {
    viewModel { RepoListViewModel() }
    viewModel { RepoDetailViewModel() }
}

private val dataModule = module {

}

private val webModule = module {

}

val modules = listOf(uiModule, dataModule, webModule)