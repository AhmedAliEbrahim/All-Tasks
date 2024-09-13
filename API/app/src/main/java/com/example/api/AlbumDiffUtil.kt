package com.example.api

import androidx.recyclerview.widget.DiffUtil

class AlbumDiffUtil (
    val oldList: List<Album>,
    val newList: List<Album>): DiffUtil.Callback()
{
    override fun getOldListSize()=oldList.size
    override fun getNewListSize()=newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList [oldItemPosition].id==newList[newItemPosition].id
    }
    override fun areContentsTheSame (oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList [oldItemPosition]==newList[newItemPosition]
    }
}