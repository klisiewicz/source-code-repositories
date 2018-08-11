package pl.karollisiewicz.reposistory.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import pl.karollisiewicz.reposistory.ui.list.RepoListViewModel

private val uiModule = module {
    viewModel { RepoListViewModel() }
}

private val dataModule = module {

}

private val webModule = module {

}

val modules = listOf(uiModule, dataModule, webModule)