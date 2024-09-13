package com.example.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var viewModule: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val albumAdapter = AlbumAdapter(mutableListOf())
        binding.albumList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = albumAdapter
        }
        viewModule = ViewModelProvider(this).get(AlbumViewModel::class.java)
        viewModule.album.observe(this, Observer { albumList ->
            albumAdapter.updateData(albumList)
        })
    }
}