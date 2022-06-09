package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.LoadStateBinding

class FeedLoadStateAdapter(
    private val onErrorClicked: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onErrorClicked
        )
    }
}

class LoadStateViewHolder(
    private val binding: LoadStateBinding,
    private val onErrorClicked: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        binding.apply {
            retry.isVisible = loadState is LoadState.Error
            loading.isVisible = loadState is LoadState.Loading
            retry.setOnClickListener {
                onErrorClicked()
            }
        }
    }
}