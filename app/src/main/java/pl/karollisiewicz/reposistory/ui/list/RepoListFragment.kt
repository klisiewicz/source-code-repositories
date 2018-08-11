package pl.karollisiewicz.reposistory.ui.list

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.karollisiewicz.common.extension.isVisibleWhen
import pl.karollisiewicz.reposistory.R
import pl.karollisiewicz.reposistory.ui.detail.RepoDetailViewModel

class RepoListFragment : Fragment() {
    private val repoListViewModel: RepoListViewModel by viewModel()
    private val repoDetailsViewModel: RepoDetailViewModel by sharedViewModel()

    private val repoAdapter = RepoListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupList()
        subscribeToViewModel()
    }

    private fun setupList() {
        repoAdapter.repoSelectionListener = { repo ->
            repoDetailsViewModel.select(repo)
            findNavController(activity as Activity, R.id.navHostFragment).navigate(R.id.action_repo_list_fragment_to_repo_detail_fragment)
        }

        with(repositories) {
            adapter = repoAdapter
            setHasFixedSize(true)
        }
    }

    private fun subscribeToViewModel() {
        repoListViewModel.getViewState().observe(viewLifecycleOwner, Observer {
            progress isVisibleWhen it.isLoading
            repositories isVisibleWhen it.hasData
            repoAdapter.submitList(it.repositories)
//            contentLayout showMessage it.errorMessage
        })
    }
}
