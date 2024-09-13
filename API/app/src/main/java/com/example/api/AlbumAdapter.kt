package com.example.api
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.api.databinding.ItemAlbumBinding

class AlbumAdapter(var albumList: MutableList<Album>):RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
    inner class AlbumHolder(val binding: ItemAlbumBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(album: Album){
            binding.album = album
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bindData(albumList[position])
    }

    override fun getItemCount()=albumList.size

    fun updateData(newList: List<Album>) {
        val diffCallback = AlbumDiffUtil(albumList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        albumList.clear()
        albumList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}