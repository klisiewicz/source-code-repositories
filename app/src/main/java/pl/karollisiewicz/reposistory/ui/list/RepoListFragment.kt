package pl.karollisiewicz.reposistory.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.karollisiewicz.reposistory.R

class RepoListFragment : Fragment() {
    private val repoListViewModel: RepoListViewModel by viewModel()
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
        with(repositories) {
            adapter = repoAdapter
            setHasFixedSize(true)
        }
    }

    private fun subscribeToViewModel() {
        repoListViewModel.getViewState().observe(viewLifecycleOwner, Observer {
            repoAdapter.submitList(it.repositories)
        })
    }
}
