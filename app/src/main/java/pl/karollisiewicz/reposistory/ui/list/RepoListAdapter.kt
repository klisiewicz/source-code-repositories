package pl.karollisiewicz.reposistory.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_repo.view.*
import pl.karollisiewicz.common.extension.circularImageFromUrl
import pl.karollisiewicz.reposistory.R
import pl.karollisiewicz.reposistory.domain.Repo
import pl.karollisiewicz.reposistory.domain.Repo.Type.BIT_BUCKET
import pl.karollisiewicz.reposistory.domain.Repo.Type.GITHUB

class RepoListAdapter(var repoSelectionListener: ((Repo) -> Unit) = {}) :
    ListAdapter<Repo, RepoListAdapter.RepoViewHolder>(RepoDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false),
            repoSelectionListener
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class RepoViewHolder(itemView: View, private val repoSelectionListener: ((Repo) -> Unit)) :
        RecyclerView.ViewHolder(itemView) {

        fun bindTo(repo: Repo) {
            with(itemView) {
                tvRepoName.text = repo.name
                tvOwnerName.text = repo.owner.name
                ivRepoType.setImageResource(repo.type.icon)
                ivOwnerAvatar.circularImageFromUrl(repo.owner.avatarUrl)
                setOnClickListener {
                    repoSelectionListener(repo)
                }
            }
        }
    }

    private class RepoDiff : DiffUtil.ItemCallback<Repo>() {

        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.id == newItem.id && oldItem.type == newItem.type

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
    }
}

private val Repo.Type.icon: Int
    get() = when (this) {
        GITHUB -> R.drawable.ic_github
        BIT_BUCKET -> R.drawable.ic_bitbucket
    }