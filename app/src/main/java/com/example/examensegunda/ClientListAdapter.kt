package com.example.examensegunda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.examensegunda.data.Client
import com.example.examensegunda.databinding.ClientListItemBinding

class ClientListAdapter(private val onClientClicked: (Client) -> Unit) :
    ListAdapter<Client, ClientListAdapter.ClientViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Client>() {
            override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
                return oldItem == oldItem
            }

            override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
                return oldItem == newItem


            }
        }
    }

    class ClientViewHolder(private var binding: ClientListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(client: Client) {
            binding.apply {
                clientName.text = client.name.toString()
                clientAddress.text = client.address.toString()
                clientPhone.text = client.phone.toString()
                clientEmail.text = client.email.toString()
                Glide.with(clientPhoto)
                    .load(client.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //Save photo in device cache
                    .into(clientPhoto)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            ClientListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ClientListAdapter.ClientViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onClientClicked(current)
        }
        holder.bind(current)
    }
}