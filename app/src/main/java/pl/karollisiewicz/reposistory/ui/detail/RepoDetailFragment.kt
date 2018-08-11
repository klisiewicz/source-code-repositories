package pl.karollisiewicz.reposistory.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pl.karollisiewicz.reposistory.R

class RepoDetailFragment : Fragment() {
    private val repoDetailsViewModel: RepoDetailViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        repoDetailsViewModel.getSelected().observe(viewLifecycleOwner, Observer { repo ->
            toolbarLayout.title = repo.name
            tvOwnerName.text = repo.owner.name
            tvRepoDescription.text = repo.description
        })
    }
}
