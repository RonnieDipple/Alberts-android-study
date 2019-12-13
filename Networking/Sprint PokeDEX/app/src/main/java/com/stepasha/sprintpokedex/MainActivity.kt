package com.stepasha.sprintpokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.stepasha.sprintpokedex.adapter.API
import com.stepasha.sprintpokedex.adapter.PokemonListAdapter
import com.stepasha.sprintpokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<Pokemon> {
    companion object {
        var pokeList: MutableList<Pokemon> = arrayListOf()
        var searchList: MutableList<Pokemon> = arrayListOf()
        var favoritesList: MutableList<Pokemon> = arrayListOf()
    }
    private lateinit var adapterList : PokemonListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycle(pokeList)

        buttonSearch.setOnClickListener {
            val input: String = textSearch.text.toString()
            if (!input.isBlank() || !input.isEmpty()){
                val retreiver = API.create()
                retreiver.getPokemonById(input.toInt()).enqueue(this)
            }
        }
        buttonViewFavorites.setOnClickListener {
            adapterList.updateRecyclerView(favoritesList)
        }
    }

    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this, "call unsuccessful", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if (response.isSuccessful){
            searchList.clear()
            searchList.add(response.body()!!)
            if (!pokeList.contains(response.body()!!)){
                pokeList.add(response.body()!!)
            }
            adapterList.updateRecyclerView(searchList)
        }else{
            Toast.makeText(this,"cant update the list", Toast.LENGTH_LONG).show()
        }

    }
    fun initRecycle(pokemonList: List<Pokemon>){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapterList = PokemonListAdapter(pokemonList)
            adapter = adapterList
        }
    }
}
