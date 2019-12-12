package com.stepasha.retrofitgithubquery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class RepoListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repoList.items[position])
    }

    override fun getItemCount(): Int = repoList.items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindRepo(repo: Item){
            with(repo){
                itemView.username.text = repo.owner.login.orEmpty()
                itemView.repoName.text = repo.full_name.orEmpty()
                itemView.repoDescription.text = repo.description.orEmpty()
            }
        }
    }
}